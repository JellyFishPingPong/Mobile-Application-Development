package com.example.assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class BorrowAdapter():RecyclerView.Adapter<BorrowViewHolder>() {

    lateinit var context:Context
    private lateinit var borrow:List<BorrowData>
    private lateinit var borrowAction: BorrowAction

    constructor(context: Context,borrow:List<BorrowData>, borrowAction: BorrowAction):this(){
        this.context=context
        this.borrow=borrow
        this.borrowAction = borrowAction
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BorrowViewHolder {

        val view= LayoutInflater.from(parent.context).inflate(R.layout.borrow_view,parent,false)

        return BorrowViewHolder(view)    }

    override fun getItemCount(): Int {
        return borrow.size
    }

    override fun onBindViewHolder(holder: BorrowViewHolder, position: Int) {
        holder.orgName.text=borrow[position].organizationName
        holder.row.setOnClickListener {
            borrowAction.onClick(borrow[position].url)
        }
    }

}

class BorrowViewHolder (BorrowView: View): RecyclerView.ViewHolder(BorrowView){
    val orgName:TextView=BorrowView.findViewById(R.id.website)
    val row: MaterialCardView = BorrowView.findViewById(R.id.website_row)
}

interface BorrowAction{

    fun onClick(url:String) {}
}

