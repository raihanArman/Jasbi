package id.co.core.data.model.cost


import com.google.gson.annotations.SerializedName

data class Rajaongkir(
    @SerializedName("destination_details")
    val destinationDetails: DestinationDetails,
    @SerializedName("origin_details")
    val originDetails: OriginDetails,
    @SerializedName("query")
    val query: Query,
    @SerializedName("results")
    val results: List<ResultCost>,
    @SerializedName("status")
    val status: Status
)