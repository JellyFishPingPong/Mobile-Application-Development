package com.example.assignment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class GuideViewHolder (GuideView: View): RecyclerView.ViewHolder(GuideView){
    val vegetableName:TextView=GuideView.findViewById(R.id.title_tv)
    val guideRow: MaterialCardView = GuideView.findViewById(R.id.guide_row)
}