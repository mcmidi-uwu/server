package xyz.tehbrian.mcmidi.serverapi;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * An event which is fired when a note request is received and accepted.
 */
public final class NoteRequestEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final Note note;
    private boolean isCancelled = false;

    public NoteRequestEvent(final Player player, final Note note) {
        this.player = player;
        this.note = note;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Gets the note which was requested.
     *
     * @return the note
     */
    public Note getNote() {
        return this.note;
    }

    /**
     * Gets player which requested the note.
     *
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
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
