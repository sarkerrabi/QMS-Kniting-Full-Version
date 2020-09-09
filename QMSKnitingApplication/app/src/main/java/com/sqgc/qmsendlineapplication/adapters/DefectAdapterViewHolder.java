/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 8/12/20 1:58 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/12/20 1:13 PM
 *
 */

package com.sqgc.qmsendlineapplication.adapters;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sqgc.qmsendlineapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class DefectAdapterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_defect_name)
    TextView tvDefectName;
    @BindView(R.id.tv_error_count)
    TextView tvErrorCount;
    @BindView(R.id.tv_defect_pos_name)
    TextView tvDefectPosName;

    public DefectAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
