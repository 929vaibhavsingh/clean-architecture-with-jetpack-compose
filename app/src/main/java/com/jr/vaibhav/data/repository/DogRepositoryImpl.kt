package com.jr.vaibhav.data.repository

import com.jr.vaibhav.data.remote.DogApi
import com.jr.vaibhav.data.remote.dto.DogBreedDto
import com.jr.vaibhav.data.remote.dto.DogDetailsDto
import com.jr.vaibhav.domain.repository.DogRepository
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val dogApi: DogApi
) : DogRepository {

    override suspend fun getDogBreeds(): DogBreedDto {
        return dogApi.getAllBreeds()
    }

    override suspend fun getDogDetailsByBreedName(dogBreedName: String): DogDetailsDto {
        return dogApi.getDogDetailsByBreedName(dogBreedName)
    }
}