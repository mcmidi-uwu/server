package xyz.tehbrian.mcmidi.serverpaper.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import xyz.tehbrian.mcmidi.serverpaper.Config;
import xyz.tehbrian.mcmidi.serverpaper.SparkController;

/**
 * Guice module which provides bindings for {@link SparkController}.
 */
public class SparkModule extends AbstractModule {

    /**
     * Provides {@link SparkController}.
     */
    @Provides
    @Singleton
    public SparkController provideSparkController(
            final @NonNull JavaPlugin javaPlugin,
            final @NonNull Config config
    ) {
        return new SparkController(javaPlugin, config);
    }

}
