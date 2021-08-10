package id.co.core.data.model

import java.util.*

data class Order(
    var id: String ?= "",
    var listMenu: List<Menu>?,
    var quantity: String ?= "",
    var date: Date,
    var status: String ?= "",
    var name: String ?= "",
    var image: String ?= "",
    var subTotal: String ?= "",
)