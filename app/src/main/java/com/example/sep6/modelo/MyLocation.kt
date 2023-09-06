package com.example.sep6.modelo

import org.json.JSONObject
import java.util.Date

class MyLocation(val date: Date, val latitudw : Double, val longitud: Double) {

    fun toJSON(): JSONObject{
        val obj = JSONObject()
        obj.put("latitude",latitudw)
        obj.put("longitude",longitud)
        obj.put("date", date)
        return obj

    }
}