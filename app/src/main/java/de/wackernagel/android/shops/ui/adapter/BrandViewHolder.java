package de.wackernagel.android.shops.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.wackernagel.android.shops.R;
import de.wackernagel.android.shops.room.entities.Brand;

public class BrandViewHolder extends ArrayAdapter.BindingViewHolder<Brand> implements View.OnClickListener {

    private ImageView image;
    private TextView name;

    BrandViewHolder(View itemView ) {
        super( itemView, Brand.class );
        itemView.setOnClickListener( this );
        image = itemView.findViewById( R.id.brandImage );
        name = itemView.findViewById( R.id.brandName );
    }

    @Override
    void bindItem( Brand brand ) {
        Glide.with( itemView.getContext() )
                .load( brand.getImageUrl() )
                .into( image );
        name.setText( brand.getName() );
    }

    @Override
    public void onClick( View view ) {
        if( getAdapterPosition() != RecyclerView.NO_POSITION ) {
            Toast.makeText( view.getContext(), "Brand at position " + getAdapterPosition(), Toast.LENGTH_SHORT ).show();
        }
    }
}
