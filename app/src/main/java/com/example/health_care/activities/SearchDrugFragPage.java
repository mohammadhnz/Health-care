package com.example.health_care.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.health_care.controllers.UserController;
import com.example.health_care.models.Customer;
import com.example.health_care.models.Drug;
import com.example.health_care.models.Pharmacy;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

import androidx.appcompat.widget.SearchView;

public class SearchDrugFragPage extends Fragment implements SearchDugRecyclerAdapter.OnNoteListenerDrug{
    View rootView;
    RecyclerView drugSearchResultRecycler;
    SearchDugRecyclerAdapter drugAdapter;
    SearchView searchView;

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
        searchView = rootView.findViewById(R.id.search_drug_bar_id);
        drugSearchResultRecycler = rootView.findViewById(R.id.search_result_id);

        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);

        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.purple_200));
        searchAutoComplete.setTextColor(getResources().getColor(R.color.purple_700));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

        drugSearchResultRecycler.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        drugAdapter = new SearchDugRecyclerAdapter(rootView.getContext(), Drug.getDrugs(), this);
        drugSearchResultRecycler.setAdapter(drugAdapter);
        drugAdapter.notifyDataSetChanged();

        return rootView;
    }


    private void filter(String text) {

        ArrayList<Drug> filteredlist = new ArrayList<>();

        for (Drug item : Drug.getDrugs()) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(rootView.getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            drugAdapter.filterList(filteredlist);
        }
    }

    @Override
    public void OnNoteListenerDrug(ImageView icon, TextView drugName, TextView drugPrice, int position) throws ParseException {
        Toast toast = Toast.makeText(getActivity(), "CLICKED", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(
                this.getActivity(),
                DrugPage.class
        );
        Drug drug = drugAdapter.getDrugs().get(position);
        Customer customer = (Customer) UserController.getInstance().getCurrentUser();
        customer.addBookmarkDrug(drug);
        intent.putExtra("drugId", String.valueOf(drug.getId()));
        startActivity(intent);
    }
}