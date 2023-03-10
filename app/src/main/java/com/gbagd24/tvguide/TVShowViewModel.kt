package com.gbagd24.tvguide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbagd24.tvguide.model.TVShow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TVShowViewModel(
    private val tvShowRepository: TVShowRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _tvShows = MutableStateFlow(emptyList<TVShow>())
    val tvShows: StateFlow<List<TVShow>> = _tvShows


    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    init {
        fetchTVShows()
    }


    private fun fetchTVShows() {
        viewModelScope.launch(dispatcher) {
            tvShowRepository.fetchTVShows()
                .catch {
                    _error.value = "An exception occurred: ${it.message}"
                }.collect {
                    _tvShows.value = it
                }
        }
    }


}
