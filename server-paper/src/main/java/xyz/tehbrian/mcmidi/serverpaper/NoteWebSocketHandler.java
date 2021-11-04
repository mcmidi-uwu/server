package xyz.tehbrian.mcmidi.serverpaper;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import xyz.tehbrian.mcmidi.serverapi.NoteRequestEvent;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Handles WebSocket requests.
 */
@SuppressWarnings("ClassCanBeRecord")
@WebSocket
public final class NoteWebSocketHandler {

    private final JavaPlugin javaPlugin;
    private final JsonAdapter<RawNoteRequest> noteRequestAdapter;

    public NoteWebSocketHandler(
            final @NonNull JavaPlugin javaPlugin,
            final @NonNull JsonAdapter<RawNoteRequest> noteRequestAdapter
    ) {
        this.javaPlugin = javaPlugin;
        this.noteRequestAdapter = noteRequestAdapter;
    }

    @OnWebSocketConnect
    public void onConnect(final Session session) {
        this.javaPlugin.getLogger().info("Opened a new WebSocket connection with " + session.getRemoteAddress().toString() + ".");
    }

    @OnWebSocketClose
    public void onClose(final Session session, final int statusCode, final String reason) {
        this.javaPlugin.getLogger().info("Closed a WebSocket connection with " + session.getRemoteAddress().toString() + ".");
    }

    @OnWebSocketError
    public void onError(final Session session, final Throwable throwable) {
        final Logger logger = this.javaPlugin.getLogger();
        logger.warning("Error on WebSocket connection with " + session.getRemoteAddress().toString() + ": " + throwable.toString());
        logger.warning("This should not happen! Printing stack trace..");
        throwable.printStackTrace();
    }

    @OnWebSocketMessage
    public void message(final Session session, final String message) throws IOException {
        final Server server = this.javaPlugin.getServer();
        final RemoteEndpoint remote = session.getRemote();

        final RawNoteRequest noteRequest;
        try {
            noteRequest = Objects.requireNonNull(this.noteRequestAdapter.fromJson(message));
        } catch (final IOException | JsonDataException | NumberFormatException e) {
            remote.sendString("Couldn't parse the given data. Here's the exception: " + e);
            return;
        }

        final Player player = server.getPlayer(noteRequest.playerName());
        if (player == null) {
            remote.sendString("Player with the given player name not found.");
            return;
        }

        final NoteRequestEvent noteRequestEvent = new NoteRequestEvent(player, noteRequest.type(), noteRequest.note());
        server.getScheduler().runTask(this.javaPlugin, () -> server.getPluginManager().callEvent(noteRequestEvent));

        remote.sendString("Successfully received note request for player " + player.getName() + ".");
    }

}
