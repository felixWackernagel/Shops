package de.wackernagel.android.shops.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import de.wackernagel.android.shops.repository.BrandRepository;
import de.wackernagel.android.shops.room.entities.Brand;

public class BrandViewModel extends ViewModel {

    private final BrandRepository repository;

    BrandViewModel(BrandRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Brand>> getAllBrands() {
        return repository.loadAllBrands();
    }

    public void insertBrand(@NonNull final Brand newBrand, @NonNull final InsertBrandTask.OnInsertCallback callback) {
        new InsertBrandTask( repository,callback ).execute( newBrand );
    }

    public static class InsertBrandTask extends AsyncTask<Brand, Void, Long> {

        public interface OnInsertCallback {
            void afterInsert( long id );
        }

        private final BrandRepository repository;
        private final OnInsertCallback callback;

        InsertBrandTask(BrandRepository repository, OnInsertCallback callback ) {
            this.repository = repository;
            this.callback = callback;
        }

        @Override
        protected Long doInBackground(Brand... brands) {
            return repository.insertBrand( brands[0] );
        }

        @Override
        protected void onPostExecute(Long aLong) {
            callback.afterInsert( aLong );
        }
    }
}
