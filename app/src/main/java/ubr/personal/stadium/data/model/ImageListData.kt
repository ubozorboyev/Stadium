package ubr.personal.stadium.data.model

class ImageListData : ArrayList<ImageListDataItem>()

data class ImageListDataItem(
    val created_at: Int,
    val files: String,
    val id: Int,
    val name: Int,
    val stadion_id: Int,
    val updated_at: Int
)