package com.example.binish.alexa.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.binish.alexa.GetterSetter.HostDetails
import com.example.binish.alexa.R
import kotlinx.android.synthetic.main.activity_test.view.*
import kotlinx.android.synthetic.main.individual_data.view.*

class TestAdapter(val test: ArrayList<String>, val context: Context): RecyclerView.Adapter<TestAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder: TestAdapter.ViewHolder, position: Int) {
        holder.bindItems(test[position])

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapter.ViewHolder {
        println("Inside View Holder")
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_test,null))
    }

    override fun getItemCount(): Int {
        return test.size
    }

    class ViewHolder (view: View): RecyclerView.ViewHolder(view){
        val testHold = view.textView!!
        fun bindItems(tex :String){
            testHold.text = tex
        }

    }
}
