package com.example.mahe.health;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PatientAdapter extends FirebaseRecyclerAdapter<doctorr,PatientAdapter.PatientViewHolder> {

    public PatientAdapter(@NonNull FirebaseRecyclerOptions<doctorr> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PatientViewHolder holder, int position, @NonNull doctorr model) {
        holder.Pname.setText(model.getPName());
        holder.Name.setText(model.getName());
        holder.Age.setText(model.getAge());
        holder.Diagnosis.setText(model.getDiagnosis());
        holder.Prescription.setText(model.getPrescription());



    }

    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template, parent, false);

        return new PatientViewHolder(view);
    }

    class PatientViewHolder extends RecyclerView.ViewHolder{
        TextView Pname,Name,Age,Diagnosis,Prescription;

        public PatientViewHolder(View itemView) {
            super(itemView);
            Pname=itemView.findViewById(R.id.PName);
            Name=itemView.findViewById(R.id.DName);
            Age=itemView.findViewById(R.id.PAge);
            Diagnosis=itemView.findViewById(R.id.Diagnosis);
            Prescription=itemView.findViewById(R.id.Prescription);
        }
    }
}
