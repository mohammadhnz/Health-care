package com.example.health_care;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health_care.Exceptions.PharmacyGetDrugsExceptions;
import com.example.health_care.models.Drug;
import com.example.health_care.models.PharmacyAdmin;

import java.text.ParseException;
import java.util.ArrayList;

public class PharmacyAddPageDrugAdaptor extends RecyclerView.Adapter<PharmacyAddPageDrugAdaptor.ViewHolder> {
    ArrayList<Drug> drugs;
    PharmacyAdmin user;
    private LayoutInflater inflater;
    PharmacyAddPageDrugAdaptor.OnNoteListenerDrug onNoteListener;
    private int textSize;

    public PharmacyAddPageDrugAdaptor(Context context, ArrayList<Drug> drugs, PharmacyAddPageDrugAdaptor.OnNoteListenerDrug onNoteListener, PharmacyAdmin user) {
        this.drugs = drugs;
        this.inflater = LayoutInflater.from(context);
        this.onNoteListener = onNoteListener;
        this.user = user;
    }

    @NonNull
    @Override
    public PharmacyAddPageDrugAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PharmacyAddPageDrugAdaptor.ViewHolder(inflater.inflate(R.layout.drug_add_pharmacy_row, parent, false), onNoteListener);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PharmacyAddPageDrugAdaptor.ViewHolder holder, int position) {
        holder.drugName.setText(drugs.get(position).getName());
        holder.drugPrice.setText("price: " + String.valueOf(drugs.get(position).getPrice()));
        try {
            holder.used.setChecked(user.getPharmacy().getDrugs().contains(drugs.get(position)));
            holder.used.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Log.d("kal", "bal");
                    if (b) {
                        if (!user.getPharmacy().getDrugs().contains(drugs.get(position))) {
                            try {
                                user.getPharmacy().addDrug(drugs.get(position).getId());
                            } catch (PharmacyGetDrugsExceptions pharmacyGetDrugsExceptions) {
                                pharmacyGetDrugsExceptions.printStackTrace();
                            }
                        }
                    } else {
                        if (user.getPharmacy().getDrugs().contains(drugs.get(position))) {
                            user.getPharmacy().getDrugs().remove(drugs.get(position));
                        }
                    }
                }
            });
        } catch (NullPointerException e) {
            holder.used.setChecked(false);
        }
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
        CheckBox used;
        PharmacyAddPageDrugAdaptor.OnNoteListenerDrug onNoteListener;

        public ViewHolder(@NonNull View itemView, PharmacyAddPageDrugAdaptor.OnNoteListenerDrug onNoteListener) {
            super(itemView);
            recRowLayout = itemView.findViewById(R.id.drugrecRowLayout);
            drugName = recRowLayout.findViewById(R.id.drug_name_id_row);
            drugPrice = recRowLayout.findViewById(R.id.drug_price_id_row);
            icon = itemView.findViewById(R.id.drug_img_recId);
            used = recRowLayout.findViewById(R.id.checkBox);
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
