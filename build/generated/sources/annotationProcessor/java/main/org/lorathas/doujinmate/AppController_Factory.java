package org.lorathas.doujinmate;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import org.hibernate.SessionFactory;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppController_Factory implements Factory<AppController> {
  private final Provider<SessionFactory> sessionFactoryProvider;

  public AppController_Factory(Provider<SessionFactory> sessionFactoryProvider) {
    this.sessionFactoryProvider = sessionFactoryProvider;
  }

  @Override
  public AppController get() {
    return newInstance(sessionFactoryProvider.get());
  }

  public static AppController_Factory create(Provider<SessionFactory> sessionFactoryProvider) {
    return new AppController_Factory(sessionFactoryProvider);
  }

  public static AppController newInstance(SessionFactory sessionFactory) {
    return new AppController(sessionFactory);
  }
}
