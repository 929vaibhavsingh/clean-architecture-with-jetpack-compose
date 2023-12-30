package com.jr.vaibhav.data.remote

import com.jr.vaibhav.data.remote.dto.DogBreedDto
import com.jr.vaibhav.data.remote.dto.DogDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {

    @GET("breeds/list/all")
    suspend fun getAllBreeds(): DogBreedDto

    @GET("breed/{breed_name}/images/random")
    suspend fun getDogDetailsByBreedName(@Path("breed_name") dogBreedName : String): DogDetailsDto
}