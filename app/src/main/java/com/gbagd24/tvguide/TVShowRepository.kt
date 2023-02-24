package com.gbagd24.tvguide

import com.gbagd24.tvguide.api.TelevisionService
import com.gbagd24.tvguide.model.TVShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TVShowRepository(private val tvService: TelevisionService) {
    private val apiKey = "4b6a464636483bd0c7d5451186b74c0e"

    fun fetchTVShows(): Flow<List<TVShow>> {
        return flow {
            emit(tvService.getTVShows(apiKey).results)
        }.flowOn(Dispatchers.IO)

    }

}
