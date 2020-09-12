/*
 * *
 *  * Created by Md. Rabiul Ali Sarker, Software Developer, IT, SQ Group
 *  * Created on on 9/12/20 11:16 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 9/12/20 11:16 AM
 *
 */

package com.sqgc.qmsendlineapplication.preknit.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sqgc.qmsendlineapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManualDefectAdapterViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_defect_name)
    TextView tvDefectName;
    @BindView(R.id.tv_error_count)
    TextView tvErrorCount;

    public ManualDefectAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
