package com.example.image_search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SearchFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
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
