package org.lorathas.doujinmate;

import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerDiContainer implements DiContainer {
  private final MainModule mainModule;

  private DaggerDiContainer(MainModule mainModuleParam) {
    this.mainModule = mainModuleParam;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static DiContainer create() {
    return new Builder().build();
  }

  @Override
  public AppController appController() {
    return MainModule_ProvidesAppControllerFactory.providesAppController(mainModule, MainModule_ProvideSessionFactoryFactory.provideSessionFactory(mainModule));}

  @Override
  public SettingsController settingsController() {
    return MainModule_ProvideSettingsControllerFactory.provideSettingsController(mainModule, MainModule_ProvideSessionFactoryFactory.provideSessionFactory(mainModule));}

  @Override
  public Settings settings() {
    return new Settings();}

  public static final class Builder {
    private MainModule mainModule;

    private Builder() {
    }

    public Builder mainModule(MainModule mainModule) {
      this.mainModule = Preconditions.checkNotNull(mainModule);
      return this;
    }

    public DiContainer build() {
      if (mainModule == null) {
        this.mainModule = new MainModule();
      }
      return new DaggerDiContainer(mainModule);
    }
  }
}
