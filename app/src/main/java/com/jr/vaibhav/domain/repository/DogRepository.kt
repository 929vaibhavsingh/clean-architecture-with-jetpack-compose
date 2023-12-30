package com.jr.vaibhav.domain.repository

import com.jr.vaibhav.data.remote.dto.DogBreedDto
import com.jr.vaibhav.data.remote.dto.DogDetailsDto

interface DogRepository {
    suspend fun getDogBreeds(): DogBreedDto
    suspend fun getDogDetailsByBreedName(dogBreedName: String): DogDetailsDto
}