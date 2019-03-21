package com.example.binish.alexa.SQLiteHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.example.binish.alexa.GetterSetter.HostDetails
import org.jetbrains.anko.db.*
import java.sql.DatabaseMetaData
import java.time.Duration

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable("data", true,
            "url" to TEXT + PRIMARY_KEY + UNIQUE,
            "globalRank" to TEXT,
            "countryRank" to TEXT,
            "countryCode" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
//        db.dropTable("User", true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)

fun insertData(context: Context, values: ContentValues){
    context.database.use {
        replace("data",null,values)
        Toast.makeText(context,"Data Added Successfully",Toast.LENGTH_LONG).show()
    }
}
fun findAll(context: Context) : ArrayList<HostDetails> = context.database.use {
    val datas = ArrayList<HostDetails>()

    select("data", "url", "globalRank", "countryRank", "countryCode")
        .parseList(object: MapRowParser<List<HostDetails>>{
            override fun parseRow(columns: Map<String, Any?>): List<HostDetails> {
                val url = columns.getValue("url")
                val globalRank = columns.getValue("globalRank")
                val countryRank = columns.getValue("countryRank")
                val countryCode = columns.getValue("countryCode")

                val host = HostDetails()
                host.HostDetails(url.toString(), globalRank.toString(), countryRank.toString(),countryCode.toString())

                datas.add(host)

                return datas
            }
        })

    return@use datas
}