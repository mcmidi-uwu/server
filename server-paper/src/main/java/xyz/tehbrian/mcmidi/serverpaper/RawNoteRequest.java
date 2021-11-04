package xyz.tehbrian.mcmidi.serverpaper;

import xyz.tehbrian.mcmidi.serverapi.Note;
import xyz.tehbrian.mcmidi.serverapi.RequestType;

/**
 * Represents a request for a note to be played by some player as received
 * directly from a client. There is no definite {@link org.bukkit.entity.Player}
 * attached yet.
 */
public final class RawNoteRequest {

    public final String playerName;
    public final RequestType type;
    public final Note note;

    public RawNoteRequest(final String playerName, final RequestType type, final Note note) {
        this.playerName = playerName;
        this.type = type;
        this.note = note;
    }

    /**
     * Gets the name of the player who requested the note.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return this.playerName;
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
     * Gets the note which was requested.
     *
     * @return the note
     */
    public Note getNote() {
        return this.note;
    }

}
