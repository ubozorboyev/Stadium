package ubr.personal.stadium.data.model

import android.text.InputType

data class OrderAreaRequest(
    val user_id: Int,
    val station_id: Int,
    val order_day: String,
    val order_time: String,
    val order_from: String,
    val order_to: String
)
