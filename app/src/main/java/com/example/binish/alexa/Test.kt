package com.example.binish.alexa

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.AsyncTask
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.binish.alexa.XMLParser.Alexa
import com.example.binish.alexa.XMLParser.readAlexa
import kotlinx.android.synthetic.main.activity_test.*
import android.R.xml
import android.util.Xml.newPullParser
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import com.example.binish.alexa.XMLParser.readData


class Test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        textView.setText("First Test");
        val queue = Volley.newRequestQueue(this)
        val url = "https://data.alexa.com/data?cli=10&dat=snbamz&url=onlinekhabar.com"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

//                textView.text = "Response is: $response"
                val factory = XmlPullParserFactory
                    .newInstance()
                factory.isNamespaceAware = true
                val parser = factory.newPullParser()

                parser.setInput(StringReader(response))

                val datas = readData(parser)
                textView.text = datas.hostName.toString()
                textView2.text = datas.countryCode
            },
            Response.ErrorListener { textView.text = "That didn't work!" })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
//        UpdateTask().execute()
    }
}

private class UpdateTask : AsyncTask<String, String, String>() {
    override fun doInBackground(vararg urls: String): String? {

        return null
    }

    /*val retrofit = Retrofit.Builder()
        .baseUrl("https://data.alexa.com/")
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    //        try {
    val serv = retrofit.create(GetInterface::class.java)
    val response = serv.getData().execute()

    val dat = response.body()
//        println("Global Rank: "+dat!!.globRank)
    println("Global Rank Value:")
//        }
    *//*catch(e: Exception){
        println("Exception: $e")
    }*//*

    println("Response done!")
    return null*/

}
