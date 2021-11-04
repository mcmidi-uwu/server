package xyz.tehbrian.mcmidi.serverpaper.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import xyz.tehbrian.mcmidi.serverpaper.ServerPlugin;

public final class PluginModule extends AbstractModule {

    private final ServerPlugin serverPlugin;

    public PluginModule(final @NonNull ServerPlugin serverPlugin) {
        this.serverPlugin = serverPlugin;
    }

    @Override
    protected void configure() {
        this.bind(ServerPlugin.class).toInstance(this.serverPlugin);
        this.bind(JavaPlugin.class).toInstance(this.serverPlugin);
    }

    @Provides
    public FileConfiguration provideConfig() {
        return this.serverPlugin.getConfig();
    }

}
