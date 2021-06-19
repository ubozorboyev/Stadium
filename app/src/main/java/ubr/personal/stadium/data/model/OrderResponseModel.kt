package ubr.personal.stadium.data.model

data class OrderResponseModel(
    val created_at: Int,
    val id: Int,
    val order_day: String,
    val order_from: String,
    val order_time: Any,
    val order_to: String,
    val stadion_id: String,
    val status: Int,
    val updated_at: Int,
    val user_id: String
)