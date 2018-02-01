package de.wackernagel.android.shops.utils;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;

public class RoundedBitmapDrawableGlideRequestListener implements RequestListener<Bitmap> {

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
        return false;
    }

    @Override
    public boolean onResourceReady(Bitmap image, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
        if (target instanceof BitmapImageViewTarget) {
            final BitmapImageViewTarget imageViewTarget = (BitmapImageViewTarget) target;
            final ImageView imageView = imageViewTarget.getView();
            final RoundedBitmapDrawable roundedImage = RoundedBitmapDrawableFactory.create(imageView.getResources(), image);
            roundedImage.setCircular(true);
            imageView.setImageDrawable(roundedImage);
            return true;
        }
        return false;
    }
}
