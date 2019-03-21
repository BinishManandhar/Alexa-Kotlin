package com.example.binish.alexa.XMLParser

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException


data class Alexa(var countryRank: String?, var countryCode: String?, var hostName: String?, var globalRank:String?)
private val ns: String? = null
// Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
// to their respective "read" methods for processing. Otherwise, skips the tag.
@Throws(XmlPullParserException::class, IOException::class)
fun readAlexa(parser: XmlPullParser): Alexa {
//    parser.require(XmlPullParser.START_TAG, ns, "SD")
    val eventType = parser.eventType
    var reach: String? = null
    var countryCode: String? = null
//    var link: String? = null
    while (eventType != XmlPullParser.END_DOCUMENT) {
        if (eventType == XmlPullParser.START_DOCUMENT) {
            continue
        }
        when (parser.name) {
            "REACH" -> reach = readReach(parser)
            "COUNTRY" -> countryCode = readCountryCode(parser)
//            "link" -> link = readLink(parser)
            else -> skip(parser)
        }
    }
    return Alexa(reach, countryCode, null, null)
}

// Processes title tags in the feed.
@Throws(IOException::class, XmlPullParserException::class)
private fun readReach(parser: XmlPullParser): String {

    parser.require(XmlPullParser.START_TAG, ns,"REACH")
//    val reach = readText(parser)
    val reach = parser.getAttributeValue(null, "RANK")
    parser.require(XmlPullParser.END_TAG, ns, "REACH")
    return reach
}

// Processes link tags in the feed.
@Throws(IOException::class, XmlPullParserException::class)
private fun readLink(parser: XmlPullParser): String {
    var link = ""
    parser.require(XmlPullParser.START_TAG, ns, "link")
    val tag = parser.name
    val relType = parser.getAttributeValue(null, "rel")
    if (tag == "link") {
        if (relType == "alternate") {
            link = parser.getAttributeValue(null, "href")
            parser.nextTag()
        }
    }
    parser.require(XmlPullParser.END_TAG, ns, "link")
    return link
}

// Processes summary tags in the feed.
@Throws(IOException::class, XmlPullParserException::class)
private fun readCountryCode(parser: XmlPullParser): String {
    parser.require(XmlPullParser.START_TAG, ns, "summary")
//    val summary = readText(parser)
    val countryCode = parser.getAttributeValue(null, "CODE")
    parser.require(XmlPullParser.END_TAG, ns, "summary")
    return countryCode
}

// For the tags title and summary, extracts their text values.
@Throws(IOException::class, XmlPullParserException::class)
private fun readText(parser: XmlPullParser): String {
    var result = ""
    if (parser.next() == XmlPullParser.TEXT) {
        result = parser.text
        parser.nextTag()
    }
    return result
}

@Throws(XmlPullParserException::class, IOException::class)
private fun skip(parser: XmlPullParser) {
    if (parser.eventType != XmlPullParser.START_TAG) {
        throw IllegalStateException()
    }
    var depth = 1
    while (depth != 0) {
        when (parser.next()) {
            XmlPullParser.END_TAG -> depth--
            XmlPullParser.START_TAG -> depth++
        }
    }
}

fun readData(parser: XmlPullParser): Alexa{
    var countryCode:String? = null
    var globalRank:String? = null
    var countryRank:String? = null
    var hostName:String? = null
    var eventType = parser.eventType
    while (eventType != XmlPullParser.END_DOCUMENT) {
        if (eventType == XmlPullParser.START_DOCUMENT) {
        } else if (eventType == XmlPullParser.START_TAG) {

            when {
                parser.name == "COUNTRY" -> {
                    countryCode = parser.getAttributeValue(null, "CODE")
                    countryRank = parser.getAttributeValue(null, "RANK")
                }
                parser.name == "REACH" -> globalRank = parser.getAttributeValue(null, "RANK")
                parser.name == "SD" -> {
                    if(hostName==null) {
                        hostName = parser.getAttributeValue(null, "HOST")
                    }
                }
            }
        }
        eventType = parser.next()
    }
    return Alexa(countryRank,countryCode,hostName,globalRank)
}