package ru.kinesis.tmdb

sealed class Resource<T>(val data: T? = null, val message: String? = null){
    //класс возвращает data если все в порядке
    class Success<T>(data: T): Resource<T>(data)
    //класс возвращает ошибку
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    //класс для отображения процесса загрузки
    class Loadint<T>(data: T? = null): Resource<T>(data)
}
