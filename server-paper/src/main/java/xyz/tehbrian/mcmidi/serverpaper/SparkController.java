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
 * Controls the Spark webserver.
 */
public final class SparkController {

    /**
     * JavaPlugin reference.
     */
    private final JavaPlugin javaPlugin;
    /**
     * Config reference.
     */
    private final Config config;

    /**
     * Constructs {@link SparkController}.
     *
     * @param javaPlugin JavaPlugin reference
     * @param config     Config reference
     */
    @Inject
    public SparkController(
            final @NonNull JavaPlugin javaPlugin,
            final @NonNull Config config) {
        this.javaPlugin = javaPlugin;
        this.config = config;
    }

    /**
     * Starts the Spark web server and sets up the appropriate endpoints and routes.
     */
    public void start() {
        Server server = this.javaPlugin.getServer();

        port(this.config.getPort());

        if (this.config.isSecureEnabled()) {
            secure(this.config.getSecureKeystoreFile(), this.config.getSecureKeystorePassword(), null, null);
        }

        Moshi moshi = new Moshi.Builder()
                .add(new PitchAdapter())
                .build();
        JsonAdapter<RawNoteRequest> noteRequestAdapter = moshi.adapter(RawNoteRequest.class);

        options("/mcmidi/note-on", (req, res) -> {
            res.header("Access-Control-Allow-Origin", req.headers("Origin"));
            res.header("Access-Control-Allow-Methods", "POST");
            res.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
            res.status(200);
            return "Please send things. I'm lonely.";
        });

        post("/mcmidi/note-on", (req, res) -> {
            res.header("Access-Control-Allow-Origin", req.headers("Origin"));
            res.header("Access-Control-Allow-Methods", "POST");
            res.header("Access-Control-Allow-Headers", "Content-Type, Authorization");

            RawNoteRequest noteRequest;
            try {
                noteRequest = Objects.requireNonNull(noteRequestAdapter.fromJson(req.body()));
            } catch (IOException | JsonDataException | NumberFormatException e) {
                res.status(400);
                return "Couldn't parse the given data. Here's the exception: " + e.toString();
            }

            Player player = server.getPlayer(noteRequest.getPlayerName());
            if (player == null) {
                res.status(406);
                return "Player with the given player name not found.";
            }

            NoteRequestEvent noteRequestEvent = new NoteRequestEvent(player, noteRequest.getNote());
            server.getScheduler().runTask(this.javaPlugin, () -> server.getPluginManager().callEvent(noteRequestEvent));

            res.status(200);
            return "Successfully received note request for player " + noteRequest.getPlayerName() + ".";
        });

        post("/mcmidi/note-off", (req, res) -> "Nothing currently happening for note-off.");
    }

    /**
     * Stops the Spark web server.
     */
    public void stop() {
        Spark.stop();
    }
}
