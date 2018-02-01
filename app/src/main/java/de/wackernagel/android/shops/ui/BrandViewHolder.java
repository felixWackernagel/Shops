package de.wackernagel.android.shops.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.wackernagel.android.shops.R;
import de.wackernagel.android.shops.room.entities.Brand;

public class BrandViewHolder extends ListableViewHolder<Brand> implements View.OnClickListener {

    private Brand brand;

    private ImageView image;
    private TextView name;

    private ListableClickListener listener;

    BrandViewHolder( View itemView, ListableClickListener listener ) {
        super( itemView, Brand.class );
        itemView.setOnClickListener( this );
        this.listener = listener;
        image = itemView.findViewById( R.id.brandImage );
        name = itemView.findViewById( R.id.brandName );
    }

    @Override
    void bindItem( Brand item ) {
        brand = item;
        Glide.with( itemView.getContext() )
                .load( brand.getImageUrl() )
                .into( image );
        name.setText( brand.getName() );
    }

    @Override
    public void onClick( View view ) {
        if( getAdapterPosition() != RecyclerView.NO_POSITION && listener != null ) {
            listener.onBrandClicked( brand, view );
        }
    }
}
