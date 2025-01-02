package com.example.assignment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookingAdapter(): RecyclerView.Adapter<BookingViewHolder>() {
    lateinit var context: Context
    private lateinit var book:List<BookingModel>
    private lateinit var bookingAction: BookingAction
    private lateinit var conT: DataBaseHelper.Companion.ConsultantTable

    constructor(context: Context,book:List<BookingModel>, bookingAction: BookingAction):this(){
        this.context=context
        this.book= book
        this.bookingAction = bookingAction
        this.conT = DataBaseHelper.Companion.ConsultantTable(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.appointment_view,parent,false)

        return BookingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return book.size
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val currBook = book[position]
        Log.d("BA", "${currBook.consultantId}")
        holder.name.text = conT.getName(currBook.consultantId)
        holder.date.text = currBook.dateSelected
        holder.time.text = currBook.timeSelected
        holder.edit.setOnClickListener {
            bookingAction.onEdit(currBook.bookingId)
        }

        holder.delete.setOnClickListener {
            bookingAction.onRemove(currBook.bookingId)
        }
    }
}

class BookingViewHolder (BookingView: View): RecyclerView.ViewHolder(BookingView){
    val name: TextView =BookingView.findViewById(R.id.apptName)
    val date: TextView = BookingView.findViewById(R.id.apptDate)
    val time: TextView = BookingView.findViewById(R.id.apptTime)
    val edit: ImageView = BookingView.findViewById(R.id.apptEdit)
    val delete: ImageView = BookingView.findViewById(R.id.apptDelete)
}

interface BookingAction{
    fun onRemove(bookingId: Int) {}
    fun onEdit(bookingId: Int) {}
}