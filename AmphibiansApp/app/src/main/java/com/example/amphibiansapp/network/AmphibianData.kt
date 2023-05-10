package com.example.amphibiansapp.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
The data layer is responsible for retrieving the amphibian
data from the API.

You probably want to include a data class for the amphibian
data, a repository to manage the data, and a data source
class to retrieve the data from the network.
 */

@Serializable
data class AmphibianData(
    val name: String,
    val type: String,
    val description: String,
    @SerialName(value = "img_src")
    val imgSrc: String,
)
