package com.jr.vaibhav.data.remote.dto

import android.os.Parcelable
import com.jr.vaibhav.domain.model.DogDetails
import kotlinx.parcelize.Parcelize

@Parcelize
data class DogDetailsDto(
    val message: String,
    val status: String
) : Parcelable

fun DogDetailsDto.toDogDetails(): DogDetails {
    return DogDetails(message)
}