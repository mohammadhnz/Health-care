package com.example.health_care.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health_care.R;
import com.example.health_care.models.Drug;
import com.example.health_care.models.Pharmacy;

import java.text.ParseException;
import java.util.ArrayList;

public class SearchPharmacyRecyclerAdapter  extends RecyclerView.Adapter<SearchPharmacyRecyclerAdapter.ViewHolder>{
    ArrayList<Pharmacy> pharmacies;
    private LayoutInflater inflater;
    OnNoteListener onNoteListener;
    boolean showPharmacy = true;
    private int textSize;

    public SearchPharmacyRecyclerAdapter(Context context, ArrayList<Pharmacy> pharmacies, OnNoteListener onNoteListener) {
        this.pharmacies = pharmacies;
        this.inflater = LayoutInflater.from(context);
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.pharmacy_row_search_page, parent, false), onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pharmacyName.setText(pharmacies.get(position).getName());

        Context context = inflater.getContext();
        Resources resources = context.getResources();
        //int resourceID=resources.getIdentifier(pharmacies.get(position).getProfPhoto(),"drawable",context.getPackageName());
        holder.icon.getContext();
        //holder.icon.setImageResource(resourceID);
    }

    @Override
    public int getItemCount() {
        return pharmacies.size();
    }

    public void filterList(ArrayList<Pharmacy> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        pharmacies = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    void addItems(ArrayList<Pharmacy> w) {
        pharmacies.addAll(w);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout recRowLayout;
        ImageView icon;
        TextView pharmacyName;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            recRowLayout = itemView.findViewById(R.id.search_phar_row_layout);
            pharmacyName = recRowLayout.findViewById(R.id.search_pharmacy_nameId);
            icon = itemView.findViewById(R.id.search_drug_img_recId);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try {
                onNoteListener.OnNoteListener(icon, pharmacyName, getAdapterPosition());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnNoteListener {
        void OnNoteListener(ImageView icon, TextView pharmacyName, int position) throws ParseException;
    }
}
