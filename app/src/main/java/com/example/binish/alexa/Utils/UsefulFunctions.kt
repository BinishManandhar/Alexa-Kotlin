package com.example.binish.alexa.Utils

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.binish.alexa.R
import com.example.binish.alexa.SQLiteHelper.insertData
import com.example.binish.alexa.XMLParser.Alexa
import kotlinx.android.synthetic.main.home_fragment.view.*
import kotlinx.android.synthetic.main.singledata_details.view.*

fun hideKeyboardFrom(context: Context, view: View) {

    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun loadImages(view: View){
    Glide.with(view)
        .load("https://traffic.alexa.com/graph?u=${view.searchInput.text}&y=q")
        .apply(RequestOptions()
            .placeholder(R.drawable.loading_icon).fitCenter())
        .into(view.rankGraph)


    Glide.with(view)
        .load("https://traffic.alexa.com/graph?u=${view.searchInput.text}")
        .apply(RequestOptions()
            .placeholder(R.drawable.loading_icon)
            .fitCenter())
        .into(view.searchGraph)

    view.loadingImage.visibility = View.GONE
    view.rankLayout.visibility = View.VISIBLE
    view.imageLayout.visibility = View.VISIBLE
    view.saveList.visibility = View.VISIBLE
}

fun loadingIcon(view: View){
    view.loadingImage.visibility = View.VISIBLE
    view.rankLayout.visibility = View.GONE
    view.imageLayout.visibility = View.GONE
    view.saveList.visibility = View.GONE
    Glide.with(view)
        .load(R.drawable.loading_circle)
        .into(view.loadingImage)
}
fun loadingIconSingle(view: View){
    view.loadingImageSingle.visibility = View.VISIBLE
    Glide.with(view)
        .load(R.drawable.loading_circle)
        .into(view.loadingImageSingle)
}
fun loadImagesSingle(view: View,input: String){
    Glide.with(view)
        .load("https://traffic.alexa.com/graph?u=$input&y=q")
        .apply(RequestOptions()
            .placeholder(R.drawable.loading_icon).fitCenter())
        .into(view.rankGraphSingle)


    Glide.with(view)
        .load("https://traffic.alexa.com/graph?u=$input")
        .apply(RequestOptions()
            .placeholder(R.drawable.loading_icon)
            .fitCenter())
        .into(view.searchGraphSingle)

    view.loadingImageSingle.visibility = View.GONE
    view.rankLayoutSingle.visibility = View.VISIBLE
    view.imageLayoutSingle.visibility = View.VISIBLE
}

fun inserting(datas: Alexa, context: Context){
    val hosts = ContentValues()

    hosts.put("url",datas.hostName)
    hosts.put("globalRank",datas.globalRank)
    hosts.put("countryRank",datas.countryRank)
    hosts.put("countryCode",datas.countryCode)

    insertData(context,hosts)
}