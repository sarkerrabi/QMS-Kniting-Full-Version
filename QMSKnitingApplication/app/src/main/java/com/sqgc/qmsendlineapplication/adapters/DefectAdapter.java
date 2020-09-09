/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/19/20 3:56 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/19/20 3:39 PM
 *
 */

package com.sqgc.qmsendlineapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sqgc.qmsendlineapplication.R;
import com.sqgc.qmsendlineapplication.models.Defect;
import com.sqgc.qmsendlineapplication.views.MainView;

import java.util.ArrayList;
import java.util.List;

public class DefectAdapter extends RecyclerView.Adapter<DefectAdapterViewHolder> {

    List<Defect> defectList = new ArrayList<>();
    MainView mainView;
    boolean isLongClicked = false;


    public DefectAdapter(List<Defect> defectList, MainView mainView) {

        this.defectList = defectList;
        this.mainView = mainView;
    }

    @NonNull
    @Override
    public DefectAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_defect_item, null);


        DefectAdapterViewHolder recyclerVHforRows = new DefectAdapterViewHolder(view);

        return recyclerVHforRows;
    }

    @Override
    public void onBindViewHolder(@NonNull DefectAdapterViewHolder holder, int position) {
        holder.tvDefectName.setText(defectList.get(position).getName());
        holder.tvErrorCount.setText(defectList.get(position).getDefectCount() + "");
        holder.tvDefectPosName.setText(defectList.get(position).getDefectPosName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defectList.get(position).incrementDefectCount();

                holder.tvErrorCount.setText(defectList.get(position).getDefectCount() + "");
                mainView.onAddQCdata(defectList.get(position));
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                defectList.get(position).setDefectCount(0);

                holder.tvErrorCount.setText(defectList.get(position).getDefectCount() + "");
                mainView.onRemoveQCData(defectList.get(position));
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return defectList.size();
    }
}
