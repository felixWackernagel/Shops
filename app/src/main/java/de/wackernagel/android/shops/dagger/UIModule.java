package de.wackernagel.android.shops.dagger;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import de.wackernagel.android.shops.MainActivity;

@Module
public abstract class UIModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivityInjector();

}
