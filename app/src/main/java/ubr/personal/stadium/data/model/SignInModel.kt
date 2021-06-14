package ubr.personal.stadium.data.model

data class SignInModelResponse(
    val auth_key: String,
    val created_at: Int,
    val email: String,
    val id: Int,
    val password_hash: String,
    val phone_number: String,
    val role: Int,
    val status: Int,
    val token: Token,
    val updated_at: Int,
    val username: String
)

data class Token(
    val expires: Int,
    val id: Int,
    val status: Int,
    val token: String,
    val user_id: Int
)