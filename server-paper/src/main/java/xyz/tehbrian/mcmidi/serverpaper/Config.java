package xyz.tehbrian.mcmidi.serverpaper;

import org.bukkit.configuration.file.FileConfiguration;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.logging.Logger;

/**
 * Grabs and holds values from a {@link org.bukkit.configuration.file.FileConfiguration}
 * for easy access.
 */
public class Config {

    private static final int PORT_MIN = 0;
    private static final int PORT_MAX = 65535;

    /**
     * FileConfiguration reference.
     */
    private final FileConfiguration config;
    /**
     * Logger reference.
     */
    private final Logger logger;

    private int port;
    private boolean isWebSocketEnabled;
    private boolean isHttpEnabled;
    private boolean isSecureEnabled;
    private String secureKeystoreFile;
    private String secureKeystorePassword;

    /**
     * Constructs {@link Config}.
     *
     * @param config the {@link FileConfiguration} to use for values
     * @param logger the {@link Logger} to yell at when values are absent
     */
    public Config(
            final @NonNull FileConfiguration config,
            final @NonNull Logger logger) {
        this.config = config;
        this.logger = logger;
    }

    /**
     * Loads and validates values from {@link #config}.
     * Whines to {@link #logger} if they're invalid.
     */
    public void loadValues() {
        this.port = this.config.getInt("port", 61672);
        this.isWebSocketEnabled = this.config.getBoolean("web_socket", true);
        this.isHttpEnabled = this.config.getBoolean("http", true);
        this.isSecureEnabled = this.config.getBoolean("secure.enabled", false);
        this.secureKeystoreFile = this.config.getString("secure.keystore_file");
        this.secureKeystorePassword = this.config.getString("secure.keystore_password");

        if (this.port < PORT_MIN || this.port > PORT_MAX) {
            this.logger.warning("Port must be between 65535. Defaulting to 61672.");
            this.port = 61672;
        }

        if (this.isSecureEnabled) {
            if (this.secureKeystoreFile == null) {
                this.logger.warning("Keystore file is null. Disabling HTTPS/SSL.");
                this.isSecureEnabled = false;
            }
            if (this.secureKeystorePassword == null) {
                this.logger.warning("Keystore password is null. Disabling HTTPS/SSL.");
                this.isSecureEnabled = false;
            }
        }
    }

    /**
     * What port the web server should use.
     *
     * @return an integer between 0-65535 inclusive
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Whether the web server should accept WebSocket requests.
     *
     * @return a boolean
     */
    public boolean isWebSocketEnabled() {
        return this.isWebSocketEnabled;
    }

    /**
     * Whether the web server should accept POST HTTP requests.
     *
     * @return a boolean
     */
    public boolean isHttpEnabled() {
        return this.isHttpEnabled;
    }

    /**
     * Whether or not the web server should attempt to use SSL/HTTPS.
     *
     * @return a boolean
     */
    public boolean isSecureEnabled() {
        return this.isSecureEnabled;
    }

    /**
     * What keystore file to use, relative to the server's root directory.
     *
     * @return the filepath
     */
    public String getSecureKeystoreFile() {
        return this.secureKeystoreFile;
    }

    /**
     * What keystore password to use.
     *
     * @return the password
     */
    public String getSecureKeystorePassword() {
        return this.secureKeystorePassword;
    }
}
