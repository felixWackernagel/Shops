package de.wackernagel.android.shops.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.wackernagel.android.shops.R;
import de.wackernagel.android.shops.room.entities.Brand;

public class ArrayAdapter extends RecyclerView.Adapter<ArrayAdapter.BindingViewHolder<?>> {

    private Object[] items;
    private ViewHolderCreator<?, ?>[] creators;

    public ArrayAdapter() {
        creators = new ViewHolderCreator[] {
            new ViewHolderCreator<Brand, BrandViewHolder>( Brand.class, R.layout.list_item_brand ) {
                @Override
                BrandViewHolder createViewHolderFor(View itemView ) {
                    return new BrandViewHolder( itemView );
                }
            }
        };
    }

    @Override
    public int getItemViewType( int position ) {
        for( int index = 0; index < creators.length; index++ ) {
            final Object item = items[position];
            final ViewHolderCreator<?, ?> viewHolderCreator = creators[index];
            if( viewHolderCreator.supportsItem( item ) ) {
                return viewHolderCreator.getViewType();
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
    }

    static abstract class ViewHolderCreator<ITEM, VH extends BindingViewHolder<ITEM>> {

        private final Class<ITEM> supportedType;
        private final int viewId;

        ViewHolderCreator(Class<ITEM> supportedType, int viewId ) {
            this.supportedType = supportedType;
            this.viewId = viewId;
        }

        boolean supportsItem( Object item ) {
            return supportedType.isAssignableFrom( item.getClass() );
        }

        boolean supportsViewType( int viewType ) {
            return viewType == viewId;
        }

        int getViewType() {
            return viewId;
        }

        VH createViewHolder( final ViewGroup parent ) {
            return createViewHolderFor( LayoutInflater.from( parent.getContext() ).inflate( viewId, parent, false ) );
        }

        abstract VH createViewHolderFor( View itemView );
    }

    abstract static class BindingViewHolder<T> extends RecyclerView.ViewHolder {

        private final Class<T> supportedType;

        BindingViewHolder(View itemView, Class<T> supportedType) {
            super(itemView);
            this.supportedType = supportedType;
        }

        void bind( Object item ) {
            if( supportedType.isAssignableFrom( item.getClass() ) ) {
                bindItem( (T) item );
            } else {
                throw new IllegalArgumentException( "ViewHolder expected type " + supportedType.getName() + " but was " + item.getClass().getName() );
            }
        }

        abstract void bindItem( T item );
    }

}
