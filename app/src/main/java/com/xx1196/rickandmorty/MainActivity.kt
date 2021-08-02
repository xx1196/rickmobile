package com.xx1196.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.xx1196.rickandmorty.databinding.ActivityMainBinding
import com.xx1196.rickandmorty.models.RickAndMorty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterAdapter
    private val characters = mutableListOf<RickAndMorty>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        makeApiRequest()
        initRecyclerView()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun makeApiRequest() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getAllCharacters()
            val data = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val charactersCall = data?.results ?: emptyList()
                    characters.clear()
                    characters.addAll(charactersCall)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }

        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {
        adapter = CharacterAdapter(characters)
        binding.rvCharacters.layoutManager = LinearLayoutManager(this)
        binding.rvCharacters.adapter = adapter
    }
}