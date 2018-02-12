package de.wackernagel.android.shops.room.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import de.wackernagel.android.shops.room.entities.Brand;
import de.wackernagel.android.shops.room.entities.Store;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface StoreDao {

    @Query( "SELECT * " +
            "FROM stores" )
    LiveData<List<Store>> loadAllStores();

    @Query( "SELECT * " +
            "FROM stores " +
            "WHERE id = :storeId" )
    LiveData<Brand> loadStoreById(long storeId );

    @Query( "SELECT * " +
            "FROM stores " +
            "WHERE brandId = :brandId" )
    LiveData<Brand> loadStoreByBrandId(long brandId );

    @Insert( onConflict = REPLACE)
    long insertStore( Store store );

    @Update( onConflict = REPLACE)
    int updateStore( Store store );

    @Delete
    int deleteStore(Store store );

}
