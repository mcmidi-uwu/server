package xyz.tehbrian.mcmidi.serverpaper;

import xyz.tehbrian.mcmidi.serverapi.Note;
import xyz.tehbrian.mcmidi.serverapi.RequestType;

/**
 * A request for a note to be played by some player as received directly from
 * a client.
 * <p>
 * There is no definite {@link org.bukkit.entity.Player} attached yet.
 *
 * @param playerName the player requesting the note
 * @param type       the request type
 * @param note       the note
 */
public record RawNoteRequest(String playerName,
                             RequestType type,
                             Note note) {

}
