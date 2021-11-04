package xyz.tehbrian.mcmidi.serverpaper;

import xyz.tehbrian.mcmidi.serverapi.Note;
import xyz.tehbrian.mcmidi.serverapi.RequestType;

/**
 * A request for a note to be played by some player as received directly from
 * a client.
 * <p>
 * There is no definite {@link org.bukkit.entity.Player} attached yet.
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
     * @return the player requesting the note
     */
    public String playerName() {
        return this.playerName;
    }

    /**
     * @return the request type
     */
    public RequestType type() {
        return this.type;
    }

    /**
     * @return the note
     */
    public Note note() {
        return this.note;
    }

}
