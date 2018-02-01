package de.wackernagel.android.shops.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.wackernagel.android.shops.R;

public class ListableViewHolderFactory implements ViewHolderFactory<ListableViewHolder, ListableClickListener> {

    private LayoutInflater layoutInflater = null;

    public ListableViewHolder createViewHolder(final ViewGroup parent, final int viewType, final ListableClickListener listener ) {
        final View view = getOrFindLayoutInflater( parent ).inflate( viewType, parent, false );
        switch( viewType ) {
            case R.layout.list_item_brand:
                return new BrandViewHolder( view, listener );

            default:
                throw new IllegalArgumentException( "Can't create a ViewHolder of unknown viewType (" + viewType + ")." );
        }
    }

    private LayoutInflater getOrFindLayoutInflater( final ViewGroup parent ) {
        if( layoutInflater == null ) {
            layoutInflater = LayoutInflater.from( parent.getContext() );
        }
        return layoutInflater;
    }
}
