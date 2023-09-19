package com.example.image_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.image_search.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun setImage(image: String): HashMap<String,String> {
        val key = BuildConfig.KAKAO_REST_API
        return hashMapOf(
            "serviceKey" to key,
            "returnType" to "json",
            "numOfRows" to "80",
            "pageNo" to "1",
            "ver" to "1.0"
        )
    }
}