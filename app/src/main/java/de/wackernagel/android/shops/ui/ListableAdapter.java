package de.wackernagel.android.shops.ui;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class ListableAdapter<L> extends RecyclerView.Adapter<ListableViewHolder> {

    private final ViewTypeResolver viewTypeResolver;
    private final ViewHolderFactory<ListableViewHolder, L> viewHolderFactory;
    private final L listener;

    private List<Listable> items;

    public ListableAdapter(ViewTypeResolver viewTypeResolver, ViewHolderFactory<ListableViewHolder, L> viewHolderFactory, L listener ) {
        this.viewTypeResolver = viewTypeResolver;
        this.viewHolderFactory = viewHolderFactory;
        this.listener = listener;
    }

    @Override
    public int getItemViewType( int position ) {
        return items.get( position ).getViewType( viewTypeResolver );
    }

    @Override
    public ListableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewHolderFactory.createViewHolder( parent, viewType, listener );
    }

    @Override
    public void onBindViewHolder(ListableViewHolder holder, int position) {
        holder.setItem( items.get( position ) );
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setItems( List<Listable> items ) {
        this.items = items;
        notifyDataSetChanged();
    }
}
