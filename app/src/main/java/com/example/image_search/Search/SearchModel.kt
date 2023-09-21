package com.example.image_search.Search

data class SearchModel (
    var title : String,
    var date : String,
    var url : String,
    var favorite : Boolean = false
)