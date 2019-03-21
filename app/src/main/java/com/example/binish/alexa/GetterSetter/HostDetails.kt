package com.example.binish.alexa.GetterSetter

class HostDetails {
    var host: String? = null
    var gRank: String? = null
    var cRank: String? = null
    var cCode: String? = null

    fun HostDetails(host: String,gRank: String, cRank: String, cCode: String){
        this.host = host
        this.gRank = gRank
        this.cRank = cRank
        this.cCode = cCode
    }
}