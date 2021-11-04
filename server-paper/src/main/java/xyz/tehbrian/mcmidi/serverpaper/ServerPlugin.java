package xyz.tehbrian.mcmidi.serverpaper;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import xyz.tehbrian.mcmidi.serverpaper.inject.ConfigModule;
import xyz.tehbrian.mcmidi.serverpaper.inject.PluginModule;
import xyz.tehbrian.mcmidi.serverpaper.inject.SparkModule;

public final class ServerPlugin extends JavaPlugin {

    // thanks brocc <3
    private @MonotonicNonNull Injector injector;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        try {
            this.injector = Guice.createInjector(
                    new PluginModule(this),
                    new ConfigModule(),
                    new SparkModule()
            );
        } catch (final Exception e) {
            this.getLog4JLogger().error("Something went wrong while creating the Guice injector.");
            this.getLog4JLogger().error("Disabling plugin.");
            this.setEnabled(false);
            this.getLog4JLogger().error("Printing stack trace, please send this to the developers:", e);
            return;
        }

        this.injector.getInstance(SparkController.class).start();
        this.injector.getInstance(Config.class).loadValues();
    }

    @Override
    public void onDisable() {
        this.injector.getInstance(SparkController.class).stop();
    }

}
