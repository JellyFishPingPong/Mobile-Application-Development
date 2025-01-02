package com.example.assignment

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Appointments : Fragment(), BookingAction {
    private var memID = -1

    private lateinit var bookT: DataBaseHelper.Companion.BookRecordTable
    private lateinit var conT: DataBaseHelper.Companion.ConsultantTable

    private lateinit var adapter: BookingAdapter
    private lateinit var bookList: ArrayList<BookingModel>
    private lateinit var empty: TextView
    private lateinit var rv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_appoinments, container, false)
        val preferencesFileName = "Farmanac"
        val sharedPreferences: SharedPreferences =
            activity?.getSharedPreferences(preferencesFileName, 0)!!
        memID = sharedPreferences.getInt("mem_id", -1)
        bookT = DataBaseHelper.Companion.BookRecordTable(requireContext())
        conT = DataBaseHelper.Companion.ConsultantTable(requireContext())


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backImage: ImageView = view.findViewById(R.id.backImage)
        backImage.setOnClickListener{
            activity?.onBackPressedDispatcher!!.onBackPressed()
        }

        rv = view.findViewById(R.id.apptRv)
        empty = view.findViewById(R.id.apptEmpty)

        bookList = bookT.getAllBooking(memID)
        setContent(bookList)

    }

    private fun setContent(bookList: ArrayList<BookingModel>) {
        if(bookList.size > 0) {
            empty.visibility = View.GONE
        } else {
            empty.visibility = View.VISIBLE
        }

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = BookingAdapter(requireContext(), bookList, this)
        rv.adapter = adapter
    }

    override fun onEdit(bookingId: Int) {
        val sharedPref = requireActivity().getSharedPreferences("Farmanac", 0)
        val editor = sharedPref.edit()
        editor.putInt("book_id", bookingId)
        editor.apply()

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, ConsultationBookingFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onRemove(bookingId: Int) {
        showDeleteConfirmationDialog(bookingId)
    }

    private fun showDeleteConfirmationDialog(bookingId: Int) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.forum_delete_confirmation, null)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()
        val currBook = bookT.getBook(bookingId)

        dialog.show()

        dialog.findViewById<TextView>(R.id.delete_message).text =
            "Do you want to cancel your appointment with ${conT.getName(currBook.consultantId)} on " +
                    "${currBook.dateSelected} from ${currBook.timeSelected}"

        dialogView.findViewById<Button>(R.id.delete_button).text = "Yes"
        dialogView.findViewById<Button>(R.id.cancel_button).text = "No"

        dialogView.findViewById<Button>(R.id.delete_button).setOnClickListener {
            bookT.deleteBook(bookingId)
            setContent(bookT.getAllBooking(memID))
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.cancel_button).setOnClickListener {
            dialog.dismiss()
        }

    }

    override fun onResume() {
        super.onResume()
        setContent(bookT.getAllBooking(memID))
    }
}