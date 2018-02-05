package de.wackernagel.android.shops;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import de.wackernagel.android.shops.room.entities.Brand;
import de.wackernagel.android.shops.ui.adapter.ArrayAdapter;
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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject( this );

        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity) getActivity() ).setSupportActionBar( toolbar );

        final ArrayAdapter adapter = new ArrayAdapter();

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
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BrandEditorBottomSheetDialogFragment.newInstance().show( getFragmentManager(), "BrandEditor" );
            }
        });
    }
}
