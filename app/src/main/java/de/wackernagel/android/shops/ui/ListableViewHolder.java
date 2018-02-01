package de.wackernagel.android.shops.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

abstract class ListableViewHolder<T extends Listable> extends RecyclerView.ViewHolder {

    private final Class<T> listableType;

    ListableViewHolder(View itemView, Class<T> listableType) {
        super( itemView );
        this.listableType = listableType;
    }

    final void setItem( Listable item ) {
        if( listableType.isAssignableFrom( item.getClass() ) ) {
            bindItem( listableType.cast( item ) );
        } else {
            throw new IllegalArgumentException( "The ViewHolder type should be '" + listableType.getName() + "' but is '" + item.getClass().getName() + "'. " +
                    "Check your listableType of the ViewHolder or the Adapter implementation." );
        }
    }

    abstract void bindItem( T item );
}
