package de.wackernagel.android.shops.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import de.wackernagel.android.shops.room.daos.BrandDao;
import de.wackernagel.android.shops.room.entities.Brand;

@Database( entities = { Brand.class }, version = 1 )
@TypeConverters( { Converters.class } )
public abstract class AppDatabase extends RoomDatabase {
    public abstract BrandDao brandDao();
}
