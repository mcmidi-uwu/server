package xyz.tehbrian.mcmidi.serverpaper.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import xyz.tehbrian.mcmidi.serverpaper.ServerPlugin;

/**
 * Guice module which provides bindings for instances originating from the plugin's main class.
 */
public final class PluginModule extends AbstractModule {

    /**
     * ServerPlugin reference.
     */
    private final ServerPlugin serverPlugin;

    /**
     * Constructs {@link PluginModule}.
     *
     * @param serverPlugin ServerPlugin reference
     */
    public PluginModule(final @NonNull ServerPlugin serverPlugin) {
        this.serverPlugin = serverPlugin;
    }

    /**
     * Provides the plugin's {@link FileConfiguration}.
     *
     * @return the {@link FileConfiguration}
     */
    @Provides
    public FileConfiguration provideFileConfiguration() {
        return this.serverPlugin.getConfig();
    }

    @Override
    protected void configure() {
        this.bind(ServerPlugin.class).toInstance(this.serverPlugin);
        this.bind(JavaPlugin.class).toInstance(this.serverPlugin);
    }
}
