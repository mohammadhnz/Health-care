package com.example.health_care.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.health_care.R;
import com.example.health_care.models.Drug;

public class SearchDrugFragPage extends Fragment {
    View rootView;
    EditText drugName;
    Button searchButton;
    RecyclerView drugSearchResultRecycler;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Drug drug4 = new Drug("id4", "n4", 4.0, "des 4");
        Drug drug5 = new Drug("id5", "n5", 5.0, "des 5");
        Drug drug6 = new Drug("id6", "n6", 6.0, "des 6");

        rootView = inflater.inflate(R.layout.search_drug_frag__page, container, false);
        drugName = rootView.findViewById(R.id.search_drug_bar_id);
        searchButton = rootView.findViewById(R.id.search_drug_button_id);
        drugSearchResultRecycler = rootView.findViewById(R.id.search_result_id);



        return inflater.inflate(R.layout.search_drug_frag__page, container, false);
    }
}