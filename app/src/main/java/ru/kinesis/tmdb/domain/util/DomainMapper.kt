package ru.kinesis.tmdb.domain.util

interface DomainMapper <T, DomainModel>{

    //конвертируем данные из сетевой модели в доменную
    fun mapToDomainModel(model: T): DomainModel

    //конвертируем данные из доменной модели в сетевую
    fun mapFromDomainModel(domainModel: DomainModel): T
}