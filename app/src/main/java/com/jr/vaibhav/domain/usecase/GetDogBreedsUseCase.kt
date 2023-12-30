package com.jr.vaibhav.domain.usecase

import com.jr.vaibhav.common.Result
import com.jr.vaibhav.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow

interface GetDogBreedsUseCase {
    fun getDogBreeds(): Flow<Result<DogBreed>>
}