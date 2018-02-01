package de.wackernagel.android.shops.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.wackernagel.android.shops.repository.BrandRepository;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final BrandRepository repository;

    @Inject
    public ViewModelFactory(BrandRepository repository ) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if( modelClass.isAssignableFrom( BrandViewModel.class ) ) {
            return (T) new BrandViewModel( repository );
        } else {
            throw new IllegalArgumentException( "Unsupported ViewModel class." );
        }
    }
}
