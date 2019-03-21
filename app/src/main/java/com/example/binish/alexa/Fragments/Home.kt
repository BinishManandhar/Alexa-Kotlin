package com.example.binish.alexa.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.binish.alexa.R
import kotlinx.android.synthetic.main.home_fragment.view.*
import com.example.binish.alexa.HttpFunctions.GetHandler

class Home: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.home_fragment, null)

        (view.searchInput).setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    GetHandler().homeDataRetrieving(activity!!.applicationContext,view,(view.searchInput.text).toString())
            }
            false
        }
        return view
    }

}