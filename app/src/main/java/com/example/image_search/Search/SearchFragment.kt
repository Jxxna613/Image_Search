package com.example.image_search.Search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.image_search.Data.Constants
import com.example.image_search.Data.ImageModel
import com.example.image_search.Data.SearchClient.apiService
import com.example.image_search.Utils
import com.example.image_search.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: SearchAdapter
    private lateinit var gridmanager: StaggeredGridLayoutManager
    private lateinit var xcontext: Context

    private var searchItems: ArrayList<SearchModel> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        xcontext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setupViews()
        setUpListeners()

        return binding.root
    }

    private fun setupViews() {
        gridmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.searchRecyclerview.layoutManager = gridmanager

        adapter = SearchAdapter(xcontext)
        binding.searchRecyclerview.adapter = adapter

        val lastSearch = Utils.getLastSearch(requireContext())
        binding.searchEditText.setText(lastSearch)
    }

    private fun setUpListeners() {
        val require =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.searchButton.setOnClickListener {
            val query = binding.searchEditText.text.toString()
            if (query.isNotEmpty()) {
                Utils.saveLastSearch(requireContext(), query)
                adapter.clearItem()
                fetchImageResults(query)
            } else {
                Toast.makeText(context, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }

            require.hideSoftInputFromWindow(binding.searchEditText.windowToken, 0)
        }
    }

    private fun fetchImageResults(query: String) {
        apiService.imageSearch(Constants.AUTH_HEADER, query, "recency", 1, 80)
            ?.enqueue(object : Callback<ImageModel?> {
                override fun onResponse(call: Call<ImageModel?>, response: Response<ImageModel?>) {
                    response.body()?.meta?.let { meta ->
                        if (meta.totalCount > 0) {
                            response.body()!!.documents.forEach { document ->
                                val title = document.displaySitename
                                val datetime = document.datetime
                                val url = document.thumbnailUrl
                                searchItems.add(SearchModel(title, datetime, url))
                            }
                        }
                    }
                    adapter.items = searchItems
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ImageModel?>, t: Throwable) {
                }
            })
    }

}