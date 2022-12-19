package com.example.padingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.padingapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  var adapter =  ImageAdapter(mutableListOf())
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvImages.adapter = adapter
        binding.btnNext.setOnClickListener {
            page++
            getImagesPages()
        }
        binding.btnRequest.setOnClickListener {
            getImages()
        }
    }
    private fun getImages(){
        App.api.getImages(q = binding.etRequest.text.toString(), page = page, per_page = 10).enqueue(
            object : Callback<PixModel>{
                override fun onResponse(call: Call<PixModel>, response: Response<PixModel>) {
                    Log.d("request","onResponse:${response.body()}")
                    adapter = ImageAdapter(response.body()!!.hits as MutableList<ImageModel>)
                    binding.rvImages.adapter = adapter
                }
                override fun onFailure(call: Call<PixModel>, t: Throwable) {
                    Log.d("request","onFailure:${t.stackTrace}")
                }
            }) }

    private fun getImagesPages() {
        App.api.getImages(q = "pokemon", page = page, per_page = 10).enqueue(
            object : Callback<PixModel> {
                override fun onResponse(call: Call<PixModel>, response: Response<PixModel>) {
                    Log.d("request", "onResponse:${response.body()!!.hits}")

                    adapter.getNewElements(response.body()!!.hits as MutableList<ImageModel>)
                    binding.rvImages.adapter = adapter
                }

                override fun onFailure(call: Call<PixModel>, t: Throwable) {
                    Log.d("request", "onFailure:${t.stackTrace}")
                }
            })
    }}