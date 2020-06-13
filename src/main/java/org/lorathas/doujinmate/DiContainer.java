package org.lorathas.doujinmate;

import dagger.Component;

import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
@Component(modules = MainModule.class)
public interface DiContainer {
    /*@Named("Controllers")
    Map<Class<?>, Provider<Object>> getControllers();*/

    AppController appController();
    SettingsController settingsController();
    @Singleton
    Settings settings();
}
