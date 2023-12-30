package com.jr.vaibhav.presentation.dog_list

import com.jr.vaibhav.domain.model.DogName

data class DogListState(
    val isLoading: Boolean = false,
    val dogBreeds: List<DogName> = emptyList(),
    val error: String? = null
)
