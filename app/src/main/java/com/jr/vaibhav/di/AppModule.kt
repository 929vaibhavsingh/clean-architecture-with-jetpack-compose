package com.jr.vaibhav.di

import com.jr.vaibhav.common.Constants
import com.jr.vaibhav.data.remote.DogApi
import com.jr.vaibhav.data.repository.DogRepositoryImpl
import com.jr.vaibhav.domain.repository.DogRepository
import com.jr.vaibhav.domain.usecase.GetDogBreedsUseCase
import com.jr.vaibhav.domain.usecase.GetDogBreedsUseCaseImpl
import com.jr.vaibhav.domain.usecase.GetDogDetailsUseCase
import com.jr.vaibhav.domain.usecase.GetDogDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDogApi(): DogApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class DataLayerModule {
        @Binds
        abstract fun bindDogRepository(
            dogRepositoryImpl: DogRepositoryImpl
        ): DogRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class DomainLayerModule {
        @Binds
        abstract fun bindGetDogBreedsUseCase(
            getDogBreedsUseCaseImpl: GetDogBreedsUseCaseImpl
        ): GetDogBreedsUseCase

        @Binds
        abstract fun bindGetDogDetailsUseCase(
            getDogDetailsUseCaseImpl: GetDogDetailsUseCaseImpl
        ): GetDogDetailsUseCase
    }
}