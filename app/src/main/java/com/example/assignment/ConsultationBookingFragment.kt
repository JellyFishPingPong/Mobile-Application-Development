package com.example.assignment

import android.app.AlertDialog
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.assignment.databinding.BookingViewBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class ConsultationBookingFragment : Fragment() {

    private var _binding: BookingViewBinding? = null
    private val binding get() = _binding!!

    private var timeslot = ""
    private var bookId = -1
    private lateinit var dateSelected: LocalDateTime
    private lateinit var formatter: DateTimeFormatter

    private lateinit var bookT: DataBaseHelper.Companion.BookRecordTable
    private lateinit var conT: DataBaseHelper.Companion.ConsultantTable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BookingViewBinding.inflate(inflater, container, false)
        val consultantSpinner = binding.consultantSelection
        val timeSlotSpinner = binding.timeslotSelection
        val preferencesFileName = "Farmanac"
        val sharedPreferences: SharedPreferences =
            activity?.getSharedPreferences(preferencesFileName, 0)!!
        val id = sharedPreferences.getInt("mem_id", -1)
        bookId = sharedPreferences.getInt("book_id", -1)
        val editor = sharedPreferences.edit()
        editor.remove("book_id")
        editor.apply()
        bookT = DataBaseHelper.Companion.BookRecordTable(requireContext())
        conT = DataBaseHelper.Companion.ConsultantTable(requireContext())

        dateSelected = LocalDateTime.now().plus(1, ChronoUnit.DAYS)
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        // Consultant Spinner
        var consultant = -1
        ArrayAdapter( requireContext(),
            R.layout.selected_item,
            conT.getAllNames()
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.selector_item)
            consultantSpinner.adapter = adapter
            consultantSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    consultant = p2
                    setTimeSlotSpinner(consultant)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(activity, "You are select nothing", Toast.LENGTH_SHORT).show()
                }
            }
        }

        timeSlotSpinner.visibility = View.INVISIBLE

        //Current Date used for prevent user book the date before the current date
        val minDate = Calendar.getInstance()
        minDate.add(Calendar.DAY_OF_MONTH, 1)
        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.DAY_OF_MONTH, 8)

        // date selected allow user to choose which date they wanted to book
        val calendarView = binding.calendarView

        calendarView.setOnDateChangeListener { _, year, month, day ->
            dateSelected = LocalDateTime.of(year, month + 1, day,0 ,0)
            val chosenDate = Calendar.getInstance()
            chosenDate.set(year, month, day)
            if (minDate > chosenDate || maxDate < chosenDate) {
                val dialogView =
                    LayoutInflater.from(requireContext()).inflate(R.layout.consultation_alert, null)

                val dialog = AlertDialog.Builder(requireContext())
                    .setView(dialogView)
                    .create()

                dialogView.findViewById<Button>(R.id.consultation_dialog_positive_button)
                    .setOnClickListener {
                        dialog.dismiss()
                    }

                timeSlotSpinner.visibility = View.INVISIBLE
                dialog.show()
            } else {
                timeSlotSpinner.visibility = View.VISIBLE

                val timeSlots = resources.getStringArray(R.array.consultant_time).toMutableList()
                val datePicked = dateSelected.format(formatter)
                val conId = conT.getId(consultant)
                val sharedPref = requireActivity().getSharedPreferences("Farmanac", 0)
                val memId = sharedPref.getInt("mem_id", -1)
                val selectedSlot = bookT.getAllTime(conId, memId, datePicked)

                for(x in timeSlots.size - 1 downTo 0) {
                    if(selectedSlot.contains(timeSlots[x])) {
                        timeSlots.removeAt(x)
                    }
                }

                // Consultant timeslot selection
                setTimeSlotSpinner(consultant)
            }
        }

// Set the desired date
        val calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 1)

        val dateInMillis: Long = calendar.timeInMillis
// Set the date in the CalendarView
        calendarView.date = dateInMillis

        binding.submitResultBooking.setOnClickListener {
            if (consultant == -1 || timeslot.isBlank()) {
                Toast.makeText(requireContext(), "Please enter required field", Toast.LENGTH_SHORT)
                    .show()
            } else {

                if(bookId == -1) {
                    val book = BookingModel(id, conT.getId(consultant), timeslot, dateSelected.format(formatter))

                    val status = bookT.insertBooking(book).toInt()
                    if (status > -1) {
                        Toast.makeText(requireContext(), "Booking added...", Toast.LENGTH_SHORT).show()
                        setTimeSlotSpinner(consultant)
                    } else {
                        Toast.makeText(requireContext(), "Record not saved...", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                else {
                    val book = BookingModel(bookId, id, conT.getId(consultant), timeslot, dateSelected.format(formatter))

                    val status = bookT.updateBooking(book)
                    if (status > -1) {
                        Toast.makeText(requireContext(), "Booking updated...", Toast.LENGTH_SHORT).show()
                        setTimeSlotSpinner(consultant)
                    } else {
                        Toast.makeText(requireContext(), "Record not saved...", Toast.LENGTH_SHORT)
                            .show()
                    }
                    bookId = -1
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }

            }
        }

        return binding.root

        // Inflate the layout for this fragment
    }

    private fun setTimeSlotSpinner(consultant: Int) {
        val timeSlots = resources.getStringArray(R.array.consultant_time).toMutableList()
        val datePicked = dateSelected.format(formatter)
        val conId = conT.getId(consultant)
        val sharedPref = requireActivity().getSharedPreferences("Farmanac", 0)
        val memId = sharedPref.getInt("mem_id", -1)
        val selectedSlot = bookT.getAllTime(conId, memId, datePicked)

        for(x in timeSlots.size - 1 downTo 0) {
            if(selectedSlot.contains(timeSlots[x])) {
                timeSlots.removeAt(x)
            }
        }

        // Consultant timeslot selection
        ArrayAdapter( requireContext(),
            R.layout.selected_item,
            timeSlots
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.selector_item)
            binding.timeslotSelection.adapter = adapter
            binding.timeslotSelection.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long,
                ) {
                    timeslot = if (p2 != 0) {
                        p0?.getItemAtPosition(p2).toString()
                    } else {
                        ""
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(activity, "You selected nothing", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


