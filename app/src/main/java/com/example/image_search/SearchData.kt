package com.example.image_search

import com.google.gson.annotations.SerializedName
import java.util.Date

data class SearchData(
    @SerializedName("collection") val collection: String,
    @SerializedName("thumbnail_url") val thumbnail_url: String,
    @SerializedName("display_sitename") val display_sitename: String,
    @SerializedName("datetime") val datetime: Date
    //날짜는 date로 적기
)