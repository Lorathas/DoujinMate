package org.lorathas.doujinmate;

import dagger.Component;
import org.lorathas.doujinmate.controller.AppController;
import org.lorathas.doujinmate.controller.SettingsController;
import org.lorathas.doujinmate.task.ImportTask;
import org.lorathas.doujinmate.task.ImportTaskConsumer;

import javax.inject.Singleton;
import java.util.concurrent.BlockingQueue;

@Singleton
@Component(modules = MainModule.class)
public interface DiContainer {
    /*@Named("Controllers")
    Map<Class<?>, Provider<Object>> getControllers();*/

    AppController appController();
    SettingsController settingsController();
    @Singleton
    Settings settings();

    @Singleton
    BlockingQueue<ImportTask> importTaskQueue();
    @Singleton
    ImportTaskConsumer importTaskConsumer();
}
