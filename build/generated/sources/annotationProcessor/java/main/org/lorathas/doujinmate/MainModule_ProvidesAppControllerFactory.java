package org.lorathas.doujinmate;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class MainModule_ProvidesAppControllerFactory implements Factory<AppController> {
  private final MainModule module;

  private final Provider<SessionFactory> sessionFactoryProvider;

  public MainModule_ProvidesAppControllerFactory(MainModule module,
      Provider<SessionFactory> sessionFactoryProvider) {
    this.module = module;
    this.sessionFactoryProvider = sessionFactoryProvider;
  }

  @Override
  public AppController get() {
    return providesAppController(module, sessionFactoryProvider.get());
  }

  public static MainModule_ProvidesAppControllerFactory create(MainModule module,
      Provider<SessionFactory> sessionFactoryProvider) {
    return new MainModule_ProvidesAppControllerFactory(module, sessionFactoryProvider);
  }

  public static AppController providesAppController(MainModule instance,
      SessionFactory sessionFactory) {
    return Preconditions.checkNotNull(instance.providesAppController(sessionFactory), "Cannot return null from a non-@Nullable @Provides method");
  }
}
