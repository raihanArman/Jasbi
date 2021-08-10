package id.co.core.data.model

import java.util.*

data class Notif(
    var id: String ?= "",
    var title: String ?= "",
    var desc: String ?= "",
    var date: Date,
    var image: String ?= ""
)