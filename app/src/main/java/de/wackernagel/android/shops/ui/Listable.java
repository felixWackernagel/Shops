package de.wackernagel.android.shops.ui;

import android.support.annotation.LayoutRes;

/**
 * A unique interface for adapter items to resolve a view holder and view type.
 */
public interface Listable {

    @LayoutRes
    int getViewType( ViewTypeResolver resolver );

}
