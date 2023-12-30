package com.jr.vaibhav.domain.usecase

import com.jr.vaibhav.common.Result
import com.jr.vaibhav.data.remote.dto.toDogDetails
import com.jr.vaibhav.di.IoDispatcher
import com.jr.vaibhav.domain.model.DogDetails
import com.jr.vaibhav.domain.repository.DogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDogDetailsUseCaseImpl @Inject constructor(
    private val dogBreedRepository: DogRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GetDogDetailsUseCase {

    override fun getDogDetailsByBreedName(dogBreedName: String): Flow<Result<DogDetails>> = flow {
        try {
            emit(Result.Loading<DogDetails>())
            val dogDetails = dogBreedRepository.getDogDetailsByBreedName(dogBreedName)
            emit(Result.Success<DogDetails>(dogDetails.toDogDetails()))
        } catch (e: HttpException) {
            emit(
                Result.Error<DogDetails>(
                    message = e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(
                Result.Error<DogDetails>(
                    message = "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }.flowOn(ioDispatcher)
}