package ubr.personal.stadium.util

sealed class DataState<out T> {

    object Loading : DataState<Nothing>()
    class SuccessData<T>(val data: T) : DataState<T>()
    class Error(message: String) : DataState<String>()

}
