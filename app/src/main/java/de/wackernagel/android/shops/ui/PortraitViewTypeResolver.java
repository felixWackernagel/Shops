package de.wackernagel.android.shops.ui;

import de.wackernagel.android.shops.R;
import de.wackernagel.android.shops.room.entities.Brand;

public class PortraitViewTypeResolver implements ViewTypeResolver {

    @Override
    public int getViewType( Brand brand ) {
        return R.layout.list_item_brand;
    }

}
