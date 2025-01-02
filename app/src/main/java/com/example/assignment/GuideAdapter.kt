package com.example.assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GuideAdapter(): RecyclerView.Adapter<GuideViewHolder>() {

    lateinit var context:Context
    private lateinit var guide:List<GuideData>
    private lateinit var guideActions: GuideActions

    constructor(context: Context, guide: List<GuideData>, guideActions: GuideActions):this(){
        this.context=context
        this.guide=guide
        this.guideActions = guideActions
    }

    fun setFilteredList(guide: List<GuideData>){
        this.guide=guide
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.guide_view,parent,false)

        return GuideViewHolder(view)
    }

    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        holder.vegetableName.text=guide[position].vegetableName
        holder.guideRow.setOnClickListener{
            guideActions.onClick(guide[position].id)
        }
    }

    override fun getItemCount(): Int {
        return guide.size
    }

}