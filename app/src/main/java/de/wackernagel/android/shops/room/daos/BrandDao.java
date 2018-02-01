package de.wackernagel.android.shops.room.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import de.wackernagel.android.shops.room.entities.Brand;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface BrandDao {

    @Query( "SELECT * " +
            "FROM brands" )
    LiveData<List<Brand>> loadAllBrands();

    @Query( "SELECT * " +
            "FROM brands " +
            "WHERE id = :brandId" )
    LiveData<Brand> loadBrandById(int brandId );

    @Insert( onConflict = REPLACE)
    long insertBrand( Brand brand );

    @Update( onConflict = REPLACE)
    int updateBrand( Brand brand );

    @Delete
    int deleteBrand(Brand brand );

}