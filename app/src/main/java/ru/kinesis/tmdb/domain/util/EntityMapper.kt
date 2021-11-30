package ru.kinesis.tmdb.domain.util

interface EntityMapper <Entity, DomainModel>{

    //конвертируем данные из сетевой модели в доменную
    fun mapFromEntity(entity: Entity): DomainModel

    //конвертируем данные из доменной модели в сетевую
    fun mapToEntity(domainModel: DomainModel): Entity
}