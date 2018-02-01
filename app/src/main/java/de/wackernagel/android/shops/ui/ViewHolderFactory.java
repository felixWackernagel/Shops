package de.wackernagel.android.shops.ui;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * A single factory to map a unique viewType to a ViewHolder
 */
interface ViewHolderFactory<VH extends RecyclerView.ViewHolder, L> {

    VH createViewHolder(ViewGroup parent, int viewType, L listener );

}
