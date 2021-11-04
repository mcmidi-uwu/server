package xyz.tehbrian.mcmidi.serverapi;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * An event which is fired when a note request is received.
 */
public final class NoteRequestEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final RequestType type;
    private final Note note;
    private boolean isCancelled = false;

    public NoteRequestEvent(final Player player, final RequestType type, final Note note) {
        this.player = player;
        this.type = type;
        this.note = note;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Gets the player which requested the note.
     *
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Gets the type of request.
     *
     * @return the request type
     */
    public RequestType getType() {
        return this.type;
    }

    /**
     * Gets the note.
     *
     * @return the note
     */
    public Note getNote() {
        return this.note;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.isCancelled = cancel;
    }

}
