package org.lorathas.doujinmate;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import org.hibernate.SessionFactory;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MainModule_ProvideSessionFactoryFactory implements Factory<SessionFactory> {
  private final MainModule module;

  public MainModule_ProvideSessionFactoryFactory(MainModule module) {
    this.module = module;
  }

  @Override
  public SessionFactory get() {
    return provideSessionFactory(module);
  }

  public static MainModule_ProvideSessionFactoryFactory create(MainModule module) {
    return new MainModule_ProvideSessionFactoryFactory(module);
  }

  public static SessionFactory provideSessionFactory(MainModule instance) {
    return Preconditions.checkNotNull(instance.provideSessionFactory(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
