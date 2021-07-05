package com.muabdz.academy.di

import android.content.Context
import com.muabdz.academy.data.source.AcademyRepository
import com.muabdz.academy.data.source.remote.RemoteDataSource
import com.muabdz.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }
}