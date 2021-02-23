package xyz.tehbrian.mcmidi.serverpaper;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import xyz.tehbrian.mcmidi.serverpaper.inject.ConfigModule;
import xyz.tehbrian.mcmidi.serverpaper.inject.PluginModule;
import xyz.tehbrian.mcmidi.serverpaper.inject.SparkModule;

import java.util.logging.Logger;

/**
 * The main plugin class for server.
 */
public final class ServerPlugin extends JavaPlugin {

    /**
     * SparkController reference.
     */
    // thanks brocc <3
    private @MonotonicNonNull SparkController sparkController = null;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        try {
            Injector injector = Guice.createInjector(
                    new PluginModule(this),
                    new ConfigModule(),
                    new SparkModule()
            );

            this.sparkController = injector.getInstance(SparkController.class);
            this.sparkController.start();
        } catch (Exception e) {
            Logger logger = this.getLogger();
            logger.severe("Something went horribly wrong when bootstrapping the plugin.");
            logger.severe(e.toString());
            logger.severe("Disabling the plugin..");
            this.getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        Objects.requireNonNull(this.sparkController);
        this.sparkController.stop();
    }
}
