package org.lorathas.doujinmate;

import dagger.Module;
import dagger.Provides;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.lorathas.doujinmate.controller.AppController;
import org.lorathas.doujinmate.controller.SettingsController;
import org.lorathas.doujinmate.task.ImportTask;
import org.lorathas.doujinmate.task.ImportTaskConsumer;

import javax.inject.Singleton;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Module
public class MainModule {

    @Provides
    /*@IntoMap
    @Named("Controllers")
    @ClassKey(AppController.class)*/
    AppController providesAppController(SessionFactory sessionFactory, BlockingQueue<ImportTask> importTaskQueue) {
        return new AppController(sessionFactory, importTaskQueue);
    }

    @Provides
    /*@IntoMap
    @Named("Controllers")
    @ClassKey(SettingsController.class)*/
    SettingsController provideSettingsController(SessionFactory sessionFactory) {
        return new SettingsController(sessionFactory);
    }

    @Provides
    @Singleton
    BlockingQueue<ImportTask> provideImportTaskQueue() {
        return new LinkedBlockingQueue<>();
    }

    @Provides
    @Singleton
    ImportTaskConsumer provideImportTaskConsumer(BlockingQueue<ImportTask> importTaskQueue, SessionFactory sessionFactory, Settings settings) {
        return new ImportTaskConsumer(importTaskQueue, sessionFactory, settings);
    }

    @Provides
    public SessionFactory provideSessionFactory() {
        var registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = null;

        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }

        return sessionFactory;
    }

}
