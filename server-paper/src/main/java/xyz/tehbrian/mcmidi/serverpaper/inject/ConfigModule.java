package xyz.tehbrian.mcmidi.serverpaper.inject;

import com.google.inject.AbstractModule;
import xyz.tehbrian.mcmidi.serverpaper.Config;

public final class ConfigModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(Config.class).asEagerSingleton();
    }

}
