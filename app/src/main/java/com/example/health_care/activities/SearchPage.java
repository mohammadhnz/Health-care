package com.example.health_care.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.health_care.activities.ui.main.SectionsPagerAdapter;
import com.example.health_care.controllers.UserController;
import com.example.health_care.databinding.ActivitySearchPageBinding;
import com.google.android.material.tabs.TabLayout;

public class SearchPage extends AppCompatActivity {

    private ActivitySearchPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        binding = ActivitySearchPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

    }
    public boolean onOptionsItemSelected(MenuItem item){
        this.finish();
        return true;
    }

}