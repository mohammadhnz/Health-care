package com.example.health_care.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

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

import com.example.health_care.R;
import com.example.health_care.adapters.SearchDugRecyclerAdapter;
import com.example.health_care.adapters.SearchPharmacyRecyclerAdapter;
import com.example.health_care.models.Drug;
import com.example.health_care.models.Pharmacy;

import java.text.ParseException;


public class SearchPharmacyFragPage extends Fragment implements SearchPharmacyRecyclerAdapter.OnNoteListener{

    View rootView;
    EditText pharmacyName;
    Button searchButton;
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
        Pharmacy pharmacy5 = new Pharmacy("ph5", "loc5", "pho5", "op5");
        Pharmacy pharmacy6 = new Pharmacy("ph6", "loc6", "pho6", "op6");

        rootView = inflater.inflate(R.layout.fragment_search_pharmacy_frag_page, container, false);
        pharmacyName = rootView.findViewById(R.id.search_phar_bar_id);
        searchButton = rootView.findViewById(R.id.search_phar_button_id);
        pharmacySearchResultRecycler = rootView.findViewById(R.id.phar_search_result_id);

        pharmacySearchResultRecycler.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        pharmacyAdapter = new SearchPharmacyRecyclerAdapter(rootView.getContext(), Pharmacy.getPharmacies(), this);
        pharmacySearchResultRecycler.setAdapter(pharmacyAdapter);
        pharmacyAdapter.notifyDataSetChanged();


        return rootView;
    }

    @Override
    public void OnNoteListener(ImageView icon, TextView pharmacyName, int position) throws ParseException {

    }
}