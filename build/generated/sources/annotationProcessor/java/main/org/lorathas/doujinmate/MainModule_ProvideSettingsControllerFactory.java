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
public final class MainModule_ProvideSettingsControllerFactory implements Factory<SettingsController> {
  private final MainModule module;

  private final Provider<SessionFactory> sessionFactoryProvider;

  public MainModule_ProvideSettingsControllerFactory(MainModule module,
      Provider<SessionFactory> sessionFactoryProvider) {
    this.module = module;
    this.sessionFactoryProvider = sessionFactoryProvider;
  }

  @Override
  public SettingsController get() {
    return provideSettingsController(module, sessionFactoryProvider.get());
  }

  public static MainModule_ProvideSettingsControllerFactory create(MainModule module,
      Provider<SessionFactory> sessionFactoryProvider) {
    return new MainModule_ProvideSettingsControllerFactory(module, sessionFactoryProvider);
  }

  public static SettingsController provideSettingsController(MainModule instance,
      SessionFactory sessionFactory) {
    return Preconditions.checkNotNull(instance.provideSettingsController(sessionFactory), "Cannot return null from a non-@Nullable @Provides method");
  }
}
