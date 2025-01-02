package com.example.assignment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class GuideFragment : Fragment(), GuideActions{

    private lateinit var guideRecyclerView:RecyclerView
    private lateinit var guideSearchView: SearchView
    private lateinit var guideList: List<GuideData>
    private lateinit var adapter: GuideAdapter
    private lateinit var guideT: DataBaseHelper.Companion.GuideTable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_guide, container, false)

        guideRecyclerView = view.findViewById(R.id.guide_recycle_view)
        guideSearchView = view.findViewById(R.id.guide_search_view)
        guideT = DataBaseHelper.Companion.GuideTable(requireContext())

        guideRecyclerView.setHasFixedSize(true)
        guideRecyclerView.layoutManager=LinearLayoutManager(activity)

        guideList = guideT.getAllGuides()

//        addDataList()
        adapter= GuideAdapter(requireContext(),guideList, this)
        guideRecyclerView.adapter=adapter


        guideSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })



        // Inflate the layout for this fragment
        return view
    }

    override fun onClick(vegId: Int) {
        //set veg_id
        val preferencesFileName = "Farmanac"
        val sharedPreferences: SharedPreferences =
            activity?.getSharedPreferences(preferencesFileName, 0)!!
        val editor: SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putInt("veg_id", vegId)
        editor.apply()
        editor.commit()
        val targetFragment = GuideDetails() //创建目标Fragment对象
        val transaction = activity?.supportFragmentManager!!.beginTransaction() //获取FragmentManager并开始Fragment事务
        transaction.replace(R.id.container, targetFragment) //使用replace方法替换当前Fragment容器中的Fragment为目标Fragment
        transaction.addToBackStack(null) //将当前Fragment添加到返回堆栈中
        transaction.commit() //提交事务
    }

    private fun filterList(query :String?){

        if(query != null){
            val filteredList = ArrayList<GuideData>()
            for ( i in guideList){
                if (i.vegetableName.lowercase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }
            if(filteredList.isEmpty()){
                Toast.makeText(activity, "No Data Found", Toast.LENGTH_SHORT).show()
                adapter.setFilteredList(listOf<GuideData>())
            }else{
                adapter.setFilteredList(filteredList)
            }
        }
    }
}