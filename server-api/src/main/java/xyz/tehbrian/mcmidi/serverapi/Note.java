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
     * Gets the {@link Instrument}.
     *
     * @return the instrument
     */
    public Instrument getInstrument() {
        return this.instrument;
    }

    /**
     * Gets the {@link Pitch}.
     *
     * @return the pitch
     */
    public Pitch getPitch() {
        return this.pitch;
    }

    /**
     * Gets the velocity represented by a float from 0 to 1.
     *
     * @return the velocity
     */
    public float getVelocity() {
        return this.velocity;
    }
}
