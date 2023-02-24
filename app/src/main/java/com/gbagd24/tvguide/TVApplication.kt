package com.gbagd24.tvguide

import android.app.Application
import com.gbagd24.tvguide.api.TelevisionService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TVApplication : Application() {


    lateinit var tvShowRepository: TVShowRepository

    override fun onCreate() {
        super.onCreate()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val tvService = retrofit.create(TelevisionService::class.java)


        tvShowRepository = TVShowRepository(tvService)
    }


}
