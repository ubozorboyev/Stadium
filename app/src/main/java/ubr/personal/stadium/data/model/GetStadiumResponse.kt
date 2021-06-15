package ubr.personal.stadium.data.model

data class GetStadiumResponse(
    val _links: Links,
    val _meta: Meta,
    val data: List<StadiumData>
)

data class Links(
    val first: First,
    val last: Last,
    val self: Self
)

data class Meta(
    val currentPage: Int,
    val pageCount: Int,
    val perPage: Int,
    val totalCount: Int
)

data class StadiumData(
    val address: String,
    val category_id: Int,
    val created_at: Any,
    val id: Int,
    val latitude: Any,
    val longitude: Any,
    val name: String,
    val price: Any,
    val status: Int,
    val files: List<ImageData>,
    val updated_at: Any
)


data class ImageData(
    val created_at: Int,
    val files: String,
    val id: Int,
    val name: Int,
    val stadion_id: Int,
    val updated_at: Int
)

data class First(
    val href: String
)

data class Last(
    val href: String
)

data class Self(
    val href: String
)