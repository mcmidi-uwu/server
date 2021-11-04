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
     * Gets the {@link Pitch} which best corresponds to the
     * MIDI note number and is also playable on Minecraft.
     *
     * @param number the MIDI note number
     * @return the {@link Pitch}
     */
    // I hate myself.
    // Surely there must be a better implementation of this?
    public static Pitch fromMidiNoteNumber(final byte number) {
        switch (number) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
                return Pitch.Fs3;
            case 55:
                return Pitch.G3;
            case 56:
                return Pitch.Gs3;
            case 57:
                return Pitch.A3;
            case 58:
                return Pitch.As3;
            case 59:
                return Pitch.B3;
            case 60:
                return Pitch.C4;
            case 61:
                return Pitch.Cs4;
            case 62:
                return Pitch.D4;
            case 63:
                return Pitch.Ds4;
            case 64:
                return Pitch.E4;
            case 65:
                return Pitch.F4;
            case 66:
                return Pitch.Fs4;
            case 67:
                return Pitch.G4;
            case 68:
                return Pitch.Gs4;
            case 69:
                return Pitch.A4;
            case 70:
                return Pitch.As4;
            case 71:
                return Pitch.B4;
            case 72:
                return Pitch.C5;
            case 73:
                return Pitch.Cs5;
            case 74:
                return Pitch.D5;
            case 75:
                return Pitch.Ds5;
            case 76:
                return Pitch.E5;
            case 77:
                return Pitch.F5;
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
                return Pitch.Fs5;
            default:
                throw new IllegalArgumentException("MIDI Number must be between 0 and 127 inclusive.");
        }
    }

    /**
     * Gets the pitch represented as a float from 0.5 to 2.
     * Useful for playing as a Minecraft sound.
     *
     * @return a float
     */
    public float asFloat() {
        // For some information on how this works, see:
        // https://minecraft.gamepedia.com/Note_Block
        return (float) Math.pow(2, ((this.numOfClicks - 12F) / 12));
    }

    /**
     * Gets the number of clicks required to select the pitch
     * on a note block.
     *
     * @return an int
     */
    public int getNumOfClicks() {
        return this.numOfClicks;
    }

    /**
     * Gets a double which, if fed into a colored particle as
     * the dx offset, will correspond to the color emitted
     * by a note block playing the same pitch.
     *
     * @return a double representing the hue of the pitch
     */
    public double getParticleColor() {
        return (this.numOfClicks / 24D);
    }
}
