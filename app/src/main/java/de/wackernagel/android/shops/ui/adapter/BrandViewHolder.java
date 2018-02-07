package de.wackernagel.android.shops.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.wackernagel.android.shops.R;
import de.wackernagel.android.shops.room.entities.Brand;
import de.wackernagel.android.shops.ui.adapter.MultiViewAdapter.ViewHolderCreator;
import de.wackernagel.android.shops.ui.adapter.MultiViewAdapter.BindingViewHolder;

public class BrandViewHolder extends BindingViewHolder<Brand> implements View.OnClickListener {

    static ViewHolderCreator<Brand, BrandViewHolder> LIST_CREATOR = new ViewHolderCreator<Brand, BrandViewHolder>( Brand.class, R.layout.list_item_brand ) {
        @Override
        BrandViewHolder createViewHolderFor( View itemView ) {
            return new BrandViewHolder( itemView );
        }
    };

    private ImageView image;
    private TextView name;
    private Brand brand;

    private BrandViewHolder(View itemView ) {
        super( itemView, Brand.class );
        itemView.setOnClickListener( this );
        image = itemView.findViewById( R.id.brandImage );
        name = itemView.findViewById( R.id.brandName );
    }

    @Override
    void bindItem( final Brand brand ) {
        this.brand = brand;

        Glide.with( itemView.getContext() )
                .load( brand.getImageUrl() )
                .into( image );
        name.setText( brand.getName() );
    }

    @Override
    public void onClick( View view ) {
        if( getAdapterPosition() != RecyclerView.NO_POSITION ) {
            Toast.makeText( view.getContext(), brand.toString(), Toast.LENGTH_SHORT ).show();
        }
    }
}
