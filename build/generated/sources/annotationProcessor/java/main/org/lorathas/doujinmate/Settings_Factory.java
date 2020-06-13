package org.lorathas.doujinmate;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class Settings_Factory implements Factory<Settings> {
  @Override
  public Settings get() {
    return newInstance();
  }

  public static Settings_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Settings newInstance() {
    return new Settings();
  }

  private static final class InstanceHolder {
    private static final Settings_Factory INSTANCE = new Settings_Factory();
  }
}
