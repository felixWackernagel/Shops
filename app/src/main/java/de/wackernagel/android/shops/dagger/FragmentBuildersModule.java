package de.wackernagel.android.shops.dagger;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import de.wackernagel.android.shops.BrandEditorBottomSheetDialogFragment;
import de.wackernagel.android.shops.BrandsListFragment;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract BrandEditorBottomSheetDialogFragment contributeBrandEditorBottomSheetDialogFragment();

    @ContributesAndroidInjector
    abstract BrandsListFragment contributeBrandsListFragment();
}
