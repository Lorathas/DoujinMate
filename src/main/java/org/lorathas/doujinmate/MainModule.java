package org.lorathas.doujinmate;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.inject.Named;

@Module
public class MainModule {

    @Provides
    /*@IntoMap
    @Named("Controllers")
    @ClassKey(AppController.class)*/
    AppController providesAppController(SessionFactory sessionFactory) {
        return new AppController(sessionFactory);
    }

    @Provides
    /*@IntoMap
    @Named("Controllers")
    @ClassKey(SettingsController.class)*/
    SettingsController provideSettingsController(SessionFactory sessionFactory) {
        return new SettingsController(sessionFactory);
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
