package xyz.tehbrian.mcmidi.serverpaper;

import xyz.tehbrian.mcmidi.serverapi.Note;

/**
 * Represents a request for a note to be played by some player as received
 * directly from an API request. Named "raw" because there is no definite {@link org.bukkit.entity.Player}
 * attached to this request yet.
 */
public final class RawNoteRequest {

    public final String playerName;
    public final Note note;

    public RawNoteRequest(final String playerName, final Note note) {
        this.playerName = playerName;
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
     * Gets the note which was requested.
     *
     * @return the note
     */
    public Note getNote() {
        return this.note;
    }
}
