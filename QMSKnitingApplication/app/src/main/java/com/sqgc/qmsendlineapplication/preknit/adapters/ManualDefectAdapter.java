/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 9/12/20 11:15 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 9/12/20 11:15 AM
 *
 */

package com.sqgc.qmsendlineapplication.preknit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sqgc.qmsendlineapplication.R;
import com.sqgc.qmsendlineapplication.models.Defect;
import com.sqgc.qmsendlineapplication.preknit.views.ManualMainView;

import java.util.ArrayList;
import java.util.List;

public class ManualDefectAdapter extends RecyclerView.Adapter<ManualDefectAdapterViewHolder> {

    List<Defect> defectList = new ArrayList<>();
    ManualMainView mainView;
    int posID;

    boolean isLongClicked = false;


    public ManualDefectAdapter(int posID, List<Defect> defectList, ManualMainView mainView) {
        this.posID = posID;
        this.defectList = defectList;
        this.mainView = mainView;
    }

    @NonNull
    @Override
    public ManualDefectAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_defect_item, null);


        ManualDefectAdapterViewHolder recyclerVHforRows = new ManualDefectAdapterViewHolder(view);

        return recyclerVHforRows;
    }

    @Override
    public void onBindViewHolder(@NonNull ManualDefectAdapterViewHolder holder, int position) {
        holder.tvDefectName.setText(defectList.get(position).getName());
        holder.tvErrorCount.setText(defectList.get(position).getDefectCount() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defectList.get(position).incrementDefectCount();

                holder.tvErrorCount.setText(defectList.get(position).getDefectCount() + "");
                mainView.onAddQCdata(posID, defectList.get(position));
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                defectList.get(position).setDefectCount(0);

                holder.tvErrorCount.setText(defectList.get(position).getDefectCount() + "");
                mainView.onRemoveQCData(posID, defectList.get(position));
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return defectList.size();
    }
}
