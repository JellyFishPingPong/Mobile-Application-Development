package com.example.assignment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.databinding.FragmentBorrowBinding

class BorrowFragment : Fragment(), BorrowAction {

    private var _binding : FragmentBorrowBinding?=null
    private val binding get() =_binding!!
    private var  websiteList=ArrayList<BorrowData>()
    private lateinit var adapter: BorrowAdapter
    private lateinit var borrowT:DataBaseHelper.Companion.BorrowTable

    override  fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        borrowT = DataBaseHelper.Companion.BorrowTable(requireContext())

        _binding = FragmentBorrowBinding.inflate(inflater,container,false)
        val countrySpinner=binding.countrySelection

        var countrySelected: String
        ArrayAdapter.createFromResource(requireContext(), R.array.country, R.layout.selected_item)
            .also {adapter ->
               adapter.setDropDownViewResource((R.layout.selector_item))
               countrySpinner.adapter=adapter
                countrySpinner.onItemSelectedListener= object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) =
                        if(p2!=0){
                            countrySelected=p0?.getItemAtPosition(p2).toString()
                            onSelected(countrySelected)
                        }else{
                            countrySelected=""
                            onSelected(countrySelected)
                        }

                    override fun onNothingSelected(p0:AdapterView<*>?){
                        Toast.makeText(requireContext(), "You are select nothing", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        adapter= BorrowAdapter(requireContext(),websiteList, this)
        return binding.root
    }

    fun onSelected(countrySelected: String) {
        Log.d("BF", "called")

        if(countrySelected == "") {
            binding.borrowRecyclerView.adapter = null
            return
        }

        val borrowList = borrowT.getAllBorrowDetail(countrySelected)

        binding.borrowRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = BorrowAdapter(requireContext(), borrowList, this)
        binding.borrowRecyclerView.adapter = adapter
    }

    override fun onClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}