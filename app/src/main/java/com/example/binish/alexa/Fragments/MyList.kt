package com.example.binish.alexa.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.binish.alexa.Adapter.HostsAdapter
import com.example.binish.alexa.GetterSetter.HostDetails
import com.example.binish.alexa.R
import com.example.binish.alexa.SQLiteHelper.findAll

class MyList: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.mylist_fragment, container, false)

        val datas = addItems()
        val rec = view.findViewById<RecyclerView>(R.id.recyclerView)
        rec.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        rec.adapter = HostsAdapter(datas)

        return view
    }


    private fun addItems(): ArrayList<HostDetails>{
        return findAll(activity!!.applicationContext)
    }
}