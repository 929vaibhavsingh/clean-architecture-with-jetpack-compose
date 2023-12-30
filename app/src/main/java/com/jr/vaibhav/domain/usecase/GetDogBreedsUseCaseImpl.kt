package com.jr.vaibhav.domain.usecase

import com.jr.vaibhav.common.Result
import com.jr.vaibhav.data.remote.dto.toDogBreed
import com.jr.vaibhav.di.IoDispatcher
import com.jr.vaibhav.domain.model.DogBreed
import com.jr.vaibhav.domain.repository.DogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDogBreedsUseCaseImpl @Inject constructor(
    private val dogBreedRepository: DogRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GetDogBreedsUseCase {

    override fun getDogBreeds(): Flow<Result<DogBreed>> = flow {
        try {
            emit(Result.Loading<DogBreed>())
            val dogBreeds = dogBreedRepository.getDogBreeds()
            emit(Result.Success<DogBreed>(dogBreeds.toDogBreed()))
        } catch (e: HttpException) {
            emit(
                Result.Error<DogBreed>(
                    message = e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(
                Result.Error<DogBreed>(
                    message = "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }.flowOn(ioDispatcher)
}