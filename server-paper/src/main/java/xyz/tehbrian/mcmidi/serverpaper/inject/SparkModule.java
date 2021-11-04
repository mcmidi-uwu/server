package xyz.tehbrian.mcmidi.serverpaper.inject;

import com.google.inject.AbstractModule;
import xyz.tehbrian.mcmidi.serverpaper.SparkController;

public final class SparkModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(SparkController.class).asEagerSingleton();
    }

}
