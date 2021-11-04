package xyz.tehbrian.mcmidi.serverpaper;

import com.google.inject.Inject;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.Moshi;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import spark.Spark;
import xyz.tehbrian.mcmidi.serverapi.NoteRequestEvent;
import xyz.tehbrian.mcmidi.serverpaper.adapters.PitchAdapter;

import java.io.IOException;
import java.util.Objects;

import static spark.Spark.options;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.secure;

/**
 * Controls a web server via {@link Spark} which accepts HTTP POST
 * and/or WebSocket requests.
 */
@SuppressWarnings("ClassCanBeRecord")
public final class SparkController {

    private final JavaPlugin javaPlugin;
    private final Config config;

    @Inject
    public SparkController(
            final @NonNull JavaPlugin javaPlugin,
            final @NonNull Config config
    ) {
        this.javaPlugin = javaPlugin;
        this.config = config;
    }

    /**
     * Starts the web server and sets up the appropriate endpoints and routes.
     */
    public void start() {
        final Server server = this.javaPlugin.getServer();

        port(this.config.port());

        if (this.config.isSecureEnabled()) {
            secure(this.config.secureKeystoreFile(), this.config.secureKeystorePassword(), null, null);
        }

        final Moshi moshi = new Moshi.Builder()
                .add(new PitchAdapter())
                .build();
        final JsonAdapter<RawNoteRequest> noteRequestAdapter = moshi.adapter(RawNoteRequest.class);

        if (this.config.isWebSocketEnabled()) {
            Spark.webSocket("/mcmidi/note", new NoteWebSocketHandler(this.javaPlugin, noteRequestAdapter));
        }

        if (this.config.isHttpEnabled()) {
            options("/mcmidi/note", (req, res) -> {
                res.header("Access-Control-Allow-Origin", req.headers("Origin"));
                res.header("Access-Control-Allow-Methods", "POST");
                res.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
                res.status(200);
                return "Please send things. I'm lonely.";
            });

            post("/mcmidi/note", (req, res) -> {
                res.header("Access-Control-Allow-Origin", req.headers("Origin"));
                res.header("Access-Control-Allow-Methods", "POST");
                res.header("Access-Control-Allow-Headers", "Content-Type, Authorization");

                final RawNoteRequest noteRequest;
                try {
                    noteRequest = Objects.requireNonNull(noteRequestAdapter.fromJson(req.body()));
                } catch (final IOException | JsonDataException | NumberFormatException e) {
                    res.status(400);
                    return "Couldn't parse the given data. Here's the exception: " + e;
                }

                final Player player = server.getPlayer(noteRequest.playerName());
                if (player == null) {
                    res.status(406);
                    return "Player with the given player name not found.";
                }

                final NoteRequestEvent noteRequestEvent = new NoteRequestEvent(player, noteRequest.type(), noteRequest.note());
                server.getScheduler().runTask(this.javaPlugin, () -> server.getPluginManager().callEvent(noteRequestEvent));

                res.status(200);
                return "Successfully received note request for player " + player.getName() + ".";
            });
        }
    }

    /**
     * Stops the web server.
     */
    public void stop() {
        Spark.stop();
    }

}
