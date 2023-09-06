package com.example.sep6

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sep6.adapters.CancionAdapter
import com.example.sep6.adapters.CancionArrayAdapter
import com.example.sep6.databinding.ActivityContactsBinding
import com.example.sep6.modelo.Cancion
import org.json.JSONObject

class ContactsActivity : AppCompatActivity(){

    private lateinit var binding: ActivityContactsBinding
    private lateinit var ListaCanciones : List<Cancion>
    lateinit var adapter: CancionArrayAdapter
    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)

        ListaCanciones = loadCancionesAct()
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = CancionArrayAdapter(this, ListaCanciones)
        binding.Canciones.adapter = adapter

        binding.irA.setOnClickListener{

            startActivity(Intent(baseContext,gps::class.java))
        }

    }

    fun loadCancionesAct(): List<Cancion>{
        val opcionCancion = mutableListOf<Cancion>()

        var json_string = baseContext.assets.open("canciones.json").bufferedReader().use { it.readText() }
        var json = JSONObject(json_string)
        var cancionesJson = json.getJSONArray("canciones")
        for(i in 0 until cancionesJson.length()){

            val jsonObject = cancionesJson.getJSONObject(i)
            var artist = jsonObject.getString("Artist")
            var category = jsonObject.getString("Category")
            var name = jsonObject.getString("Name")
            var url = jsonObject.getString("URL")
            var date = jsonObject.getString("Date")
            var year = jsonObject.getString("year")
            var avarage_rating = jsonObject.getString("Avarage Rating").toFloat()
            var rating = jsonObject.getString("Ratings").toInt()
            var reviews = jsonObject.getString("Reviews").toInt()
            var cancion = Cancion(artist,category,name,url,date,year,avarage_rating,rating,reviews)
            opcionCancion.add(cancion)
        }

        return opcionCancion
    }
}