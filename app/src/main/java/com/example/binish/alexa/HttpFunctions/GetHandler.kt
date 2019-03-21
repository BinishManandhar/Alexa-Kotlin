package com.example.binish.alexa.HttpFunctions

import android.content.Context
import android.view.KeyEvent
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.binish.alexa.R.id.*
import com.example.binish.alexa.Utils.*
import com.example.binish.alexa.XMLParser.Alexa
import com.example.binish.alexa.XMLParser.readData
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.view.*
import kotlinx.android.synthetic.main.singledata_details.view.*
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader

class GetHandler {
    /*fun homeDatas(context: Context, view: View, input: String){
        loadingIcon(view)
        val datas = dataRetrieving(context,view,input)
            view.globalRankNum.text = datas.globalRank
            view.countryRankNum.text = datas.countryRank
        loadImages(view)
        (view.saveList).setOnClickListener {
                inserting(datas,context)
        }
    }*/


    fun homeDataRetrieving(context: Context,view: View, input: String) {
        println("Input: $input")
        loadingIcon(view)
                val queue = Volley.newRequestQueue(context)
                val url = "https://data.alexa.com/data?cli=10&dat=snbamz&url=$input"
                val stringRequest = StringRequest(
                    Request.Method.GET, url,
                    Response.Listener<String> { response ->
                        val factory = XmlPullParserFactory
                            .newInstance()
                        factory.isNamespaceAware = true
                        val parser = factory.newPullParser()

                        parser.setInput(StringReader(response))

                        val datas = readData(parser)

                        view.globalRankNum.text = datas.globalRank
                        view.countryRankNum.text = datas.countryRank
                        loadImages(view)
                        (view.saveList).setOnClickListener {
                            inserting(datas,context)
                        }
                    },
                    Response.ErrorListener { view.globalRankNum.text = "That didn't work!" })

                // Add the request to the RequestQueue.
                queue.add(stringRequest)
                hideKeyboardFrom(context, view)
    }

    fun singleDataRetrieving(context: Context,view: View, input: String) {
        println("Input: $input")
        loadingIconSingle(view)
        val queue = Volley.newRequestQueue(context)
        val url = "https://data.alexa.com/data?cli=10&dat=snbamz&url=$input"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val factory = XmlPullParserFactory
                    .newInstance()
                factory.isNamespaceAware = true
                val parser = factory.newPullParser()

                parser.setInput(StringReader(response))

                val datas = readData(parser)

                view.globalRankNumSingle.text = datas.globalRank
                view.countryRankNumSingle.text = datas.countryRank
                loadImagesSingle(view, datas.hostName.toString())
            },
            Response.ErrorListener { view.globalRankNumSingle.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        hideKeyboardFrom(context, view)
    }
}