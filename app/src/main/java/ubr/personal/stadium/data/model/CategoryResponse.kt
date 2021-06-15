package ubr.personal.stadium.data.model

data class CategoryResponse(
    val _links: Links,
    val _meta: Meta,
    val `data`: List<CategoryData>
)


data class CategoryData(
    val id: Int,
    val name: String,
    val status: Int,
    val icon: String
)

