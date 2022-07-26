package com.example.health_care.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.health_care.R;

public class SearchDrugFragPage extends Fragment {



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
        return inflater.inflate(R.layout.search_drug_frag__page, container, false);
    }
}