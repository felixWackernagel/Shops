package de.wackernagel.android.shops;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import de.wackernagel.android.shops.room.entities.Brand;
import de.wackernagel.android.shops.ui.adapter.MultiViewAdapter;
import de.wackernagel.android.shops.viewmodels.BrandViewModel;

public class BrandsListFragment extends Fragment {

    public static BrandsListFragment newInstance() {
        final BrandsListFragment fragment = new BrandsListFragment();
        fragment.setArguments(Bundle.EMPTY);
        return fragment;
    }

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private TextView ribbon;
    private Button resultFilter;
    private NestedScrollView bottomSheet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_list_brands, container, false );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = view.findViewById( R.id.toolbar );
        recyclerView = view.findViewById( R.id.recyclerView );
        fab = view.findViewById( R.id.fab );
        ribbon = view.findViewById( R.id.ribbon );
        resultFilter = view.findViewById( R.id.resultFilter );
        bottomSheet = view.findViewById(R.id.nestedScrollView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject( this );

        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity) getActivity() ).setSupportActionBar( toolbar );

        final MultiViewAdapter adapter = new MultiViewAdapter();

        recyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
        recyclerView.setHasFixedSize( true );
        recyclerView.setAdapter( adapter );

        final BrandViewModel viewModel = ViewModelProviders.of( this, viewModelFactory ).get(BrandViewModel.class);
        viewModel.getAllBrands().observe(this, new Observer<List<Brand>>() {
            @Override
            public void onChanged(@Nullable List<Brand> brands) {
                if( brands != null ) {
                    adapter.setItems(brands.toArray());
                }

                final int count = brands != null ? brands.size() : 0;
                ribbon.setText( getResources().getQuantityString( R.plurals.item_count, count, count ) );
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BrandEditorBottomSheetDialogFragment.newInstance().show( getFragmentManager(), "BrandEditor" );
            }
        });

        resultFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetBehavior bsb = BottomSheetBehavior.from(bottomSheet);
                bsb.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    }

                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                        Log.i("FAB", "SlideOffset=" + slideOffset);
                        fab.setScaleX( 1.0f - slideOffset );
                        fab.setScaleY( 1.0f - slideOffset );
                    }
                });
                bsb.setState( bsb.getState() == BottomSheetBehavior.STATE_EXPANDED ? BottomSheetBehavior.STATE_COLLAPSED : BottomSheetBehavior.STATE_EXPANDED );
            }
        });
    }
}
