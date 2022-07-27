package com.example.health_care.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health_care.R;
import com.example.health_care.adapters.CustomerMainPagePharmacyAdapter;
import com.example.health_care.adapters.SearchDugRecyclerAdapter;
import com.example.health_care.models.Drug;
import com.example.health_care.models.Pharmacy;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

import androidx.appcompat.widget.SearchView;

public class SearchDrugFragPage extends Fragment implements SearchDugRecyclerAdapter.OnNoteListenerDrug{
    View rootView;
    EditText drugName;
    Button searchButton;
    RecyclerView drugSearchResultRecycler;
    SearchDugRecyclerAdapter drugAdapter;

    public SearchDrugFragPage() {
    }

    public static SearchDrugFragPage newInstance(String param1, String param2) {
        SearchDrugFragPage fragment = new SearchDrugFragPage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: Delete later
        setHasOptionsMenu(true);
        Drug drug4 = new Drug("id4", "n4", 4.0, "des 4");
        Drug drug5 = new Drug("id5", "n5", 5.0, "des 5");
        Drug drug6 = new Drug("id6", "n6", 6.0, "des 6");
        Drug drug7 = new Drug("id7", "n7", 7.0, "des 7");
        Drug drug8 = new Drug("id8", "n8", 8.0, "des 8");


        rootView = inflater.inflate(R.layout.search_drug_frag__page, container, false);
        drugName = rootView.findViewById(R.id.search_drug_bar_id);
        searchButton = rootView.findViewById(R.id.search_drug_button_id);
        drugSearchResultRecycler = rootView.findViewById(R.id.search_result_id);

        buildRecyclerView();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drugName.getText().toString().isEmpty()){

                }
            }
        });


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // below line is to get our inflater
        inflater.inflate(R.menu.search_drug_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.search_drug_row_layout);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Drug> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (Drug item : Drug.getDrugs()) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(rootView.getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            drugAdapter.filterList(filteredlist);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void buildRecyclerView() {
        drugSearchResultRecycler.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        drugAdapter = new SearchDugRecyclerAdapter(rootView.getContext(), Drug.getDrugs(), this);
        drugSearchResultRecycler.setAdapter(drugAdapter);
        drugAdapter.notifyDataSetChanged();

    }

    @Override
    public void OnNoteListenerDrug(ImageView icon, TextView drugName, TextView drugPrice, int position) throws ParseException {

    }
}