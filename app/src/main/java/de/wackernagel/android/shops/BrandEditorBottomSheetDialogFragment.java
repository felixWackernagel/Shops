package de.wackernagel.android.shops;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import de.wackernagel.android.shops.room.entities.Brand;
import de.wackernagel.android.shops.viewmodels.BrandViewModel;
import de.wackernagel.android.sidekick.medias.Medias;
import de.wackernagel.android.sidekick.medias.ResultInfo;

public class BrandEditorBottomSheetDialogFragment extends EditorBottomSheetDialogFragment implements View.OnClickListener, Medias.Callback {

    private static final int OPEN_CAMERA_REQUEST = 1;
    private static final int OPEN_DEVICE_BROWSER_REQUEST = 2;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private TextInputEditText name, websiteUrl;
    private ImageButton camera, device, clear;
    private ImageView image;

    public static BrandEditorBottomSheetDialogFragment newInstance() {
        final Bundle arguments = new Bundle();
        final BrandEditorBottomSheetDialogFragment fragment = new BrandEditorBottomSheetDialogFragment();
        fragment.setArguments( arguments );
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    int getDialogLayoutResId() {
        return R.layout.editor_brand;
    }

    @Override
    void onCreateDialogLayout( @NonNull final View sheetLayout ) {
        name = sheetLayout.findViewById(R.id.name);
        websiteUrl = sheetLayout.findViewById(R.id.websiteUrl);
        camera = sheetLayout.findViewById(R.id.cameraButton);
        device = sheetLayout.findViewById(R.id.deviceButton);
        clear = sheetLayout.findViewById(R.id.clearButton);
        image = sheetLayout.findViewById(R.id.image);
        sheetLayout.findViewById(R.id.saveButton).setOnClickListener(this);

        camera.setEnabled(Medias.of( getContext() ).fromCamera().supported() );
        device.setEnabled(Medias.of( getContext() ).fromDocument().supported() );
        clear.setEnabled( image.getDrawable() != null );
        camera.setOnClickListener( this );
        device.setOnClickListener( this );
        clear.setOnClickListener( this );

        final TextInputLayout parent = sheetLayout.findViewById(R.id.nameLayout);
        parent.setError( "Name is required" );
    }


    private void save() {
        final BrandViewModel brandViewModel = ViewModelProviders.of( BrandEditorBottomSheetDialogFragment.this, viewModelFactory ).get(BrandViewModel.class);
        if( TextUtils.isEmpty( name.getText().toString() ) ) {
            return;
        }

        final Brand brand = new Brand( name.getText().toString() );
        if( !TextUtils.isEmpty( websiteUrl.getText().toString() ) ) {
            brand.setWebsiteUrl(websiteUrl.getText().toString());
        }
        brandViewModel.insertBrand(brand, new BrandViewModel.InsertBrandTask.OnInsertCallback() {
            @Override
            public void afterInsert(long id) {
                Log.i("Shops", "Brand id " + id);
                dismiss();
            }
        });
    };

    @Override
    public void onClick(View view) {
        switch ( view.getId() ) {
            case R.id.saveButton:
                save();
                break;
            case R.id.cameraButton:
                openCamera();
                break;
            case R.id.deviceButton:
                openDeviceBrowser();
                break;
            case R.id.clearButton:
                image.setImageDrawable(null);
                clear.setEnabled( false );
                break;
        }
    }

    private void openDeviceBrowser() {
        startActivityForResult( Medias.of( getContext() ).fromDocument().createIntent(), OPEN_DEVICE_BROWSER_REQUEST);
    }

    private void openCamera() {
        startActivityForResult( Medias.of(getContext()).fromCamera().createIntent(), OPEN_CAMERA_REQUEST );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final ResultInfo resultInfo = new ResultInfo.Builder( resultCode, data )
                .callback( this )
                .build();

        switch( requestCode ) {
            case OPEN_CAMERA_REQUEST:
                Medias.of( getContext() ).fromCamera().result( resultInfo );
                break;

            case OPEN_DEVICE_BROWSER_REQUEST:
                Medias.of( getContext() ).fromDocument().result( resultInfo );
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onMediaResult( @NonNull File media ) {
        clear.setEnabled( true );
        Glide.with( this ).load( media ).into( image );
    }

    @Override
    public void onMediaCanceled() {

    }

    @Override
    public void onMediaError( @NonNull Exception exception ) {
        Toast.makeText( getContext(), "We could not load the image.", Toast.LENGTH_SHORT ).show();
        Log.e("Medias", "Error during media processing.", exception );
    }
}
