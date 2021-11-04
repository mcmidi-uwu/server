package xyz.tehbrian.mcmidi.serverapi;

/**
 * Represents a note that can be played.
 *
 * @param instrument the instrument
 * @param pitch      the pitch
 * @param velocity   the velocity represented as a float from 0 to 1
 */
public record Note(Instrument instrument,
                   Pitch pitch,
                   float velocity) {

}
