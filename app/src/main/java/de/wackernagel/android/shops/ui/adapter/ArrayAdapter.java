package de.wackernagel.android.shops.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ArrayAdapter extends RecyclerView.Adapter<ArrayAdapter.BindingViewHolder<?>> {

    private Object[] items;
    private ViewHolderCreator<?, ?>[] creators;

    public ArrayAdapter() {
        creators = new ViewHolderCreator[] {
                BrandViewHolder.LIST_CREATOR
        };
    }

    @Override
    public int getItemViewType( int position ) {
        // Search for a ViewHolderCreator which supports the item type and get his layout resource id.
        for( int index = 0; index < creators.length; index++ ) {
            final ViewHolderCreator<?, ?> viewHolderCreator = creators[index];
            if( viewHolderCreator.supportsItem( items[position] ) ) {
                return viewHolderCreator.getLayoutResId();
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Search for a ViewHolderCreator which supports the viewType and create a concrete ViewHolder
        for( int index = 0; index < creators.length; index++ ) {
            final ViewHolderCreator<?, ?> viewHolderCreator = creators[index];
            if( viewHolderCreator.supportsViewType( viewType ) ) {
                return viewHolderCreator.createViewHolder( parent );
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<?> holder, int position) {
        holder.bind( items[ position ] );
    }

    @Override
    public int getItemCount() {
        return items != null ? items.length : 0;
    }

    public void setItems(Object[] items) {
        this.items = items;
        notifyDataSetChanged();
    }

    /**
     * A creator of ViewHolders based on a concrete item class and layout resource.
     *
     * @param <ITEM> type of the supported item
     * @param <VH> type of the ViewHolder which is created
     */
    static abstract class ViewHolderCreator<ITEM, VH extends BindingViewHolder<ITEM>> {

        private final Class<ITEM> supportedType;
        private final @LayoutRes int layoutResId;

        private LayoutInflater layoutInflater;

        ViewHolderCreator( @NonNull final Class<ITEM> supportedType, @LayoutRes final int layoutResId ) {
            this.supportedType = supportedType;
            this.layoutResId = layoutResId;
        }

        boolean supportsItem( Object item ) {
            return supportedType.isAssignableFrom( item.getClass() );
        }

        boolean supportsViewType( final int viewType ) {
            return viewType == layoutResId;
        }

        @LayoutRes
        int getLayoutResId() {
            return layoutResId;
        }

        private VH createViewHolder( final ViewGroup parent ) {
            return createViewHolderFor( getOrCreateLayoutInflater( parent.getContext() ).inflate( layoutResId, parent, false ) );
        }

        private LayoutInflater getOrCreateLayoutInflater( @NonNull final Context context ) {
            if( layoutInflater == null ) {
                layoutInflater = LayoutInflater.from( context );
            }
            return  layoutInflater;
        }

        abstract VH createViewHolderFor( View itemView );
    }

    /**
     * A typed ViewHolder which casts a Object to the given type.
     *
     * @param <ITEM> type of the bind item
     */
    abstract static class BindingViewHolder<ITEM> extends RecyclerView.ViewHolder {

        private final Class<ITEM> supportedType;

        BindingViewHolder(View itemView, Class<ITEM> supportedType) {
            super(itemView);
            this.supportedType = supportedType;
        }

        void bind( Object item ) {
            if( supportedType.isAssignableFrom( item.getClass() ) ) {
                bindItem( (ITEM) item );
            } else {
                throw new IllegalArgumentException( "ViewHolder expected type '" + supportedType.getName() + "' but was '" + item.getClass().getName() + "'!" );
            }
        }

        abstract void bindItem( ITEM item );
    }

}
