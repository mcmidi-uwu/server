package xyz.tehbrian.mcmidi.serverapi;

/**
 * Represents a note that can be played.
 */
public final class Note {

    private final Instrument instrument;
    private final Pitch pitch;
    private final float velocity;

    public Note(final Instrument instrument, final Pitch pitch, final float velocity) {
        this.instrument = instrument;
        this.pitch = pitch;
        this.velocity = velocity;
    }

    /**
     * @return the instrument
     */
    public Instrument instrument() {
        return this.instrument;
    }

    /**
     * @return the pitch
     */
    public Pitch pitch() {
        return this.pitch;
    }

    /**
     * @return the velocity represented as a float from 0 to 1
     */
    public float velocity() {
        return this.velocity;
    }

}
