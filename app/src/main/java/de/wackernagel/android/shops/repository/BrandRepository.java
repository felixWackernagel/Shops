package de.wackernagel.android.shops.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import de.wackernagel.android.shops.room.daos.BrandDao;
import de.wackernagel.android.shops.room.entities.Brand;

public class BrandRepository {

    private final BrandDao brandDao;

    @Inject
    public BrandRepository( final BrandDao brandDao ) {
        this.brandDao = brandDao;
    }

    public LiveData<List<Brand>> loadAllBrands() {
        return brandDao.loadAllBrands();
    }

    public LiveData<Brand> loadBrandById( @IntRange(from = 1) int brandId ) {
        return brandDao.loadBrandById( brandId );
    }

    public long insertBrand( @NonNull final Brand brand ) {
        return brandDao.insertBrand( brand );
    }

    public int updateBrand( @NonNull final Brand brand ) {
        return brandDao.updateBrand( brand );
    }

    public int deleteBrand( @NonNull final Brand brand ) {
        return brandDao.deleteBrand( brand );
    }

}
