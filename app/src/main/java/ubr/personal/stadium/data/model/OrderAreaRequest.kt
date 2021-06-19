package ubr.personal.stadium.data.model

data class OrderAreaRequest(
    val user_id: Int,
    val stadion_id: Int,
    val order_from: String,
    val order_to: String
)
