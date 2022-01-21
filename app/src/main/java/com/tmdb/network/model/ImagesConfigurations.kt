package com.tmdb.network.model

import com.google.gson.annotations.SerializedName

data class ImagesConfigurations(
    @SerializedName("secure_base_url")
    val baseUrl: String
)
