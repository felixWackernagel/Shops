package de.wackernagel.android.shops.dagger;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.wackernagel.android.shops.repository.BrandRepository;
import de.wackernagel.android.shops.room.AppDatabase;
import de.wackernagel.android.shops.room.RoomSampleCreator;
import de.wackernagel.android.shops.room.daos.BrandDao;
import de.wackernagel.android.shops.viewmodels.ViewModelFactory;

@Module
public class RoomModule {

    private final AppDatabase database;

    public RoomModule(Application application) {
        this.database = Room
            .databaseBuilder( application, AppDatabase.class, "shops.db" )
            .addCallback(new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            RoomSampleCreator.createSamples( database );
                        }
                    });
                }
            })
            .build();
    }

    @Provides
    @Singleton
    BrandRepository provideBrandRepository( BrandDao brandDao ) {
        return new BrandRepository( brandDao );
    }

    @Provides
    @Singleton
    BrandDao provideBrandDao( AppDatabase database ) {
        return database.brandDao();
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase() {
        return database;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory providerViewModelFactory( BrandRepository repository ) {
        return new ViewModelFactory( repository );
    }
}
