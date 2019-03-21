package com.example.binish.alexa.Adapter

import android.app.Activity
import com.example.binish.alexa.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import com.example.binish.alexa.Fragments.HostDetail
import com.example.binish.alexa.GetterSetter.HostDetails
import com.example.binish.alexa.NavigationDrawer
import kotlinx.android.synthetic.main.individual_data.view.*

class HostsAdapter(val hosts: ArrayList<HostDetails>): RecyclerView.Adapter<com.example.binish.alexa.Adapter.ViewHolder>(){
    lateinit var context: Context

    override fun onBindViewHolder(holder: com.example.binish.alexa.Adapter.ViewHolder, position: Int) {
        val hos = hosts[position]
        holder.snNumber.text = (position+1).toString()
        holder.hostName.text = hos.host
        holder.globalRank.text = ("Global Rank: "+hos.gRank)
        holder.countryRank.text = ("Local Rank: "+hos.cRank)
        holder.singleData.setOnClickListener {
//            NavigationDrawer().singleDataFragment(hos.host.toString(),context)
            val intent = Intent(context, HostDetail::class.java)
            intent.putExtra("url",hos.host.toString())
            startActivity(context,intent, Bundle.EMPTY)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.example.binish.alexa.Adapter.ViewHolder {
        val vi = LayoutInflater.from(parent.context).inflate(R.layout.individual_data,parent, false)
        context = parent.context
        return com.example.binish.alexa.Adapter.ViewHolder(vi)
    }

    override fun getItemCount(): Int {
        return hosts.size
    }

}
class ViewHolder (view:View): RecyclerView.ViewHolder(view){

    val snNumber = view.snNumber!!
    val hostName = view.hostName!!
    val globalRank = view.globalRank!!
    val countryRank = view.findViewById<TextView>(R.id.countryRank)!! //one of two ways to define id
    val singleData = view.singleData
}

