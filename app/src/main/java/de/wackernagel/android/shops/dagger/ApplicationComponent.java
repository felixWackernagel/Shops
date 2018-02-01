package de.wackernagel.android.shops.dagger;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import de.wackernagel.android.shops.ShopsApplication;

@Singleton
@Component(modules = {AndroidInjectionModule.class, RoomModule.class, UIModule.class})
public interface ApplicationComponent extends AndroidInjector<ShopsApplication> {
}