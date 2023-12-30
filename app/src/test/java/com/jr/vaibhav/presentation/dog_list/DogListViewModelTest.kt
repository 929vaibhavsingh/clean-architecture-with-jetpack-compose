package com.jr.vaibhav.presentation.dog_list


import com.jr.vaibhav.MainCoroutinesRule
import com.jr.vaibhav.common.Result
import com.jr.vaibhav.data.remote.dto.toDogBreed
import com.jr.vaibhav.domain.model.DogBreed
import com.jr.vaibhav.domain.usecase.GetDogBreedsUseCase
import com.jr.vaibhav.mockdata.fetchDogBreedsMockData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DogListViewModelTest {

    // Set the main coroutine dispatcher for testing
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutinesRule()

    private val getDogBreedsUseCase: GetDogBreedsUseCase = mockk(relaxed = true)
    private lateinit var dogListViewModel: DogListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dogListViewModel = DogListViewModel(getDogBreedsUseCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getDogBreeds success`() = runTest {
        // Arrange
        val fakeDogList = fetchDogBreedsMockData().toDogBreed()
        coEvery { getDogBreedsUseCase.getDogBreeds() } returns (flowOf(Result.Success(fakeDogList)))

        // Act
        dogListViewModel.getDogBreeds()

        // Assert
        val dogListState = dogListViewModel.dogListState.value
        assertEquals(fakeDogList.dogs, dogListState.dogBreeds)
        assertEquals(false, dogListState.isLoading)
        assertNull(dogListState.error)
    }

    @Test
    fun `getDogBreeds error`() = runTest {
        // Arrange
        val errorMessage = "An unexpected error"
        coEvery { getDogBreedsUseCase.getDogBreeds() } returns (flowOf(Result.Error(errorMessage)))

        // Act
        dogListViewModel.getDogBreeds()

        // Assert
        val dogListState = dogListViewModel.dogListState.value
        assert(dogListState.dogBreeds.isEmpty())
        assertEquals(false, dogListState.isLoading)
        assertEquals(errorMessage, dogListState.error)
    }

    @Test
    fun `getDogBreeds loading`() = runTest {
        // Arrange
        coEvery {getDogBreedsUseCase.getDogBreeds() } returns (flowOf(Result.Loading<DogBreed>()))

        // Act
        dogListViewModel.getDogBreeds()

        // Assert
        val dogListState = dogListViewModel.dogListState.value
        assert(dogListState.dogBreeds.isEmpty())
        assertEquals(true, dogListState.isLoading)
        assertNull(dogListState.error)
    }
}