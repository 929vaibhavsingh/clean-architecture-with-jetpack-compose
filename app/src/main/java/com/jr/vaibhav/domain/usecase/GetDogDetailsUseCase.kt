package com.jr.vaibhav.domain.usecase

import com.jr.vaibhav.common.Result
import com.jr.vaibhav.domain.model.DogDetails
import kotlinx.coroutines.flow.Flow

interface GetDogDetailsUseCase {
    fun getDogDetailsByBreedName(dogBreedName : String): Flow<Result<DogDetails>>
}