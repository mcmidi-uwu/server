package xyz.tehbrian.mcmidi.serverapi;

/**
 * Represents a pitch that can be played as a note in Minecraft and
 * provides helper methods for interacting with and choosing pitches.
 * <p>
 * Technically, Minecraft is able to produce pitches from F#1 to F#7,
 * depending on which instrument is used. However, for the current
 * oversimplified implementation, only the range for the Minecraft
 * note block default instrument, the harp, is used.
 */
public enum Pitch {
    Fs3(0),
    G3(1),
    Gs3(2),
    A3(3),
    As3(4),
    B3(5),
    C4(6),
    Cs4(7),
    D4(8),
    Ds4(9),
    E4(10),
    F4(11),
    Fs4(12),
    G4(13),
    Gs4(14),
    A4(15),
    As4(16),
    B4(17),
    C5(18),
    Cs5(19),
    D5(20),
    Ds5(21),
    E5(22),
    F5(23),
    Fs5(24);

    private final int numOfClicks;

    Pitch(final int numOfClicks) {
        this.numOfClicks = numOfClicks;
    }

    /**
     * Gets the {@link Pitch} that best corresponds to the
     * MIDI note number and is also playable on Minecraft.
     *
     * @param number the MIDI note number
     * @return the pitch
     */
    // I hate myself.
    // Surely there must be a better implementation of this?
    public static Pitch fromMidiNoteNumber(final byte number) {
        return switch (number) {
            case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54 -> Pitch.Fs3;
            case 55 -> Pitch.G3;
            case 56 -> Pitch.Gs3;
            case 57 -> Pitch.A3;
            case 58 -> Pitch.As3;
            case 59 -> Pitch.B3;
            case 60 -> Pitch.C4;
            case 61 -> Pitch.Cs4;
            case 62 -> Pitch.D4;
            case 63 -> Pitch.Ds4;
            case 64 -> Pitch.E4;
            case 65 -> Pitch.F4;
            case 66 -> Pitch.Fs4;
            case 67 -> Pitch.G4;
            case 68 -> Pitch.Gs4;
            case 69 -> Pitch.A4;
            case 70 -> Pitch.As4;
            case 71 -> Pitch.B4;
            case 72 -> Pitch.C5;
            case 73 -> Pitch.Cs5;
            case 74 -> Pitch.D5;
            case 75 -> Pitch.Ds5;
            case 76 -> Pitch.E5;
            case 77 -> Pitch.F5;
            case 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127 -> Pitch.Fs5;
            default -> throw new IllegalArgumentException("MIDI Number must be between 0 and 127 inclusive.");
        };
    }

    /**
     * Gets this pitch represented as a float from 0.5 to 2.
     * Useful for playing as a Minecraft sound.
     *
     * @return a float representing this pitch
     */
    public float asFloat() {
        // For some information on how this works, see:
        // https://minecraft.gamepedia.com/Note_Block
        return (float) Math.pow(2, ((this.numOfClicks - 12F) / 12));
    }

    /**
     * Gets the number of clicks required to select this pitch
     * on a note block.
     *
     * @return the number of clicks
     */
    public int getNumOfClicks() {
        return this.numOfClicks;
    }

    /**
     * Gets a double which, if fed into a colored particle as
     * the dx offset, will correspond to the color emitted
     * by a note block playing the same pitch.
     *
     * @return a double representing the hue of this pitch
     */
    public double getParticleColor() {
        return (this.numOfClicks / 24D);
    }
}
