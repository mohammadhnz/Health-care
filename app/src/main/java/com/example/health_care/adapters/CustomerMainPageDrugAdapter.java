package com.example.health_care.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health_care.R;
import com.example.health_care.models.Drug;
import com.example.health_care.models.Pharmacy;

import java.text.ParseException;
import java.util.ArrayList;

public class CustomerMainPageDrugAdapter extends RecyclerView.Adapter<CustomerMainPageDrugAdapter.ViewHolder>{
    ArrayList<Drug> drugs;
    private LayoutInflater inflater;
    OnNoteListenerDrug onNoteListener;
    boolean showPharmacy = true;
    private int textSize;

    public CustomerMainPageDrugAdapter(Context context, ArrayList<Drug> drugs, OnNoteListenerDrug onNoteListener) {
        this.drugs = drugs;
        this.inflater = LayoutInflater.from(context);
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.drug_row_customer_main, parent, false), onNoteListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.drugName.setText(drugs.get(position).getName());
        holder.drugPrice.setText("price: " + String.valueOf(drugs.get(position).getPrice()));
        Context context = inflater.getContext();
        Resources resources = context.getResources();
        //int resourceID=resources.getIdentifier(pharmacies.get(position).getProfPhoto(),"drawable",context.getPackageName());
        holder.icon.getContext();
        //holder.icon.setImageResource(resourceID);
    }

    @Override
    public int getItemCount() {
        return drugs.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    void addItems(ArrayList<Drug> w) {
        drugs.addAll(w);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout recRowLayout;
        ImageView icon;
        TextView drugName;
        TextView drugPrice;
        OnNoteListenerDrug onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListenerDrug onNoteListener) {
            super(itemView);
            recRowLayout = itemView.findViewById(R.id.drugrecRowLayout);
            drugName = recRowLayout.findViewById(R.id.drug_name_id_row);
            drugPrice = recRowLayout.findViewById(R.id.drug_price_id_row);
            icon = itemView.findViewById(R.id.drug_img_recId);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try {
                onNoteListener.OnNoteListenerDrug(icon, drugName, drugPrice, getAdapterPosition());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnNoteListenerDrug {
        void OnNoteListenerDrug(ImageView icon, TextView drugName, TextView drugPrice, int position) throws ParseException;
    }
}
