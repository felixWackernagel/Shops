package de.wackernagel.android.shops.ui;

import android.view.View;

import de.wackernagel.android.shops.room.entities.Brand;

public interface ListableClickListener {

    void onBrandClicked( Brand brand, View view );

}
