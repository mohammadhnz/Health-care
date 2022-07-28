package com.example.health_care.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health_care.R;
import com.example.health_care.adapters.SearchDugRecyclerAdapter;
import com.example.health_care.adapters.SearchPharmacyRecyclerAdapter;
import com.example.health_care.models.Drug;
import com.example.health_care.models.Pharmacy;

import java.text.ParseException;
import java.util.ArrayList;


public class SearchPharmacyFragPage extends Fragment implements SearchPharmacyRecyclerAdapter.OnNoteListener{

    View rootView;
    SearchView searchView;
    RecyclerView pharmacySearchResultRecycler;
    SearchPharmacyRecyclerAdapter pharmacyAdapter;

    public SearchPharmacyFragPage() {
        // Required empty public constructor
    }


    public static SearchPharmacyFragPage newInstance(String param1, String param2) {
        SearchPharmacyFragPage fragment = new SearchPharmacyFragPage();
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
        // TODO: Delete Later
        Pharmacy pharmacy4 = new Pharmacy("ph4", "loc4", "pho4", "op4");
        Pharmacy pharmacy4_1 = new Pharmacy("ph4_1", "loc5", "pho5", "op5");
        Pharmacy pharmacy4_2 = new Pharmacy("ph4_2", "loc6", "pho6", "op6");

        Pharmacy pharmacy5 = new Pharmacy("ph5", "loc4", "pho4", "op4");
        Pharmacy pharmacy53 = new Pharmacy("ph53", "loc4", "pho4", "op4");
        Pharmacy pharmacy54 = new Pharmacy("ph54", "loc4", "pho4", "op4");

        rootView = inflater.inflate(R.layout.fragment_search_pharmacy_frag_page, container, false);
        searchView = rootView.findViewById(R.id.search_phar_bar_id);
        pharmacySearchResultRecycler = rootView.findViewById(R.id.phar_search_result_id);

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

        pharmacySearchResultRecycler.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        pharmacyAdapter = new SearchPharmacyRecyclerAdapter(rootView.getContext(), Pharmacy.getPharmacies(), this);
        pharmacySearchResultRecycler.setAdapter(pharmacyAdapter);
        pharmacyAdapter.notifyDataSetChanged();


        return rootView;
    }

    private void filter(String text) {

        ArrayList<Pharmacy> filteredlist = new ArrayList<>();

        for (Pharmacy item : Pharmacy.getPharmacies()) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(rootView.getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            pharmacyAdapter.filterList(filteredlist);
        }
    }

    @Override
    public void OnNoteListener(ImageView icon, TextView pharmacyName, int position) throws ParseException {
        // TODO: go to pharmacy page
    }
}