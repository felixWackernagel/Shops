package de.wackernagel.android.shops.ui;

import android.support.annotation.LayoutRes;

import de.wackernagel.android.shops.room.entities.Brand;

/**
 * A resolver which returns a layout resource id by a room entity model.
 */
public interface ViewTypeResolver {

    @LayoutRes
    int getViewType( Brand brand );

}
