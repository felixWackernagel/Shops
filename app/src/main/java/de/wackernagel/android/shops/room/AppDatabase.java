package de.wackernagel.android.shops.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import de.wackernagel.android.shops.room.daos.BrandDao;
import de.wackernagel.android.shops.room.daos.StoreDao;
import de.wackernagel.android.shops.room.entities.Brand;
import de.wackernagel.android.shops.room.entities.Store;

@Database( entities = { Brand.class, Store.class }, version = 1 )
@TypeConverters( { Converters.class } )
public abstract class AppDatabase extends RoomDatabase {
    public abstract BrandDao brandDao();
    public abstract StoreDao storeDao();
}
