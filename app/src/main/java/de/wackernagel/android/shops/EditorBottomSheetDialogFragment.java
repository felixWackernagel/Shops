package de.wackernagel.android.shops;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

/*
 * Issue: http://code.google.com/p/android/issues/detail?id=202691
 * Black statusbar with BottomSheetDialogFragment
 */
public abstract class EditorBottomSheetDialogFragment extends BottomSheetDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);

        final View sheetLayout = LayoutInflater.from( dialog.getContext() ).inflate( getDialogLayoutResId(), null, false );
        if( sheetLayout == null ) {
            throw new NullPointerException( "The layout of a bottom sheet can not be null." );
        }
        dialog.setContentView( sheetLayout );
        onCreateDialogLayout( sheetLayout );
        return dialog;
    }

    abstract void onCreateDialogLayout( View layout );

    @LayoutRes
    abstract int getDialogLayoutResId();

}