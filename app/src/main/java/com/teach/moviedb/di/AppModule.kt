package com.teach.moviedb.di

import android.provider.SyncStateContract.Constants
import androidx.lifecycle.ViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.teach.moviedb.data.MovieApi
import com.teach.moviedb.data.repository.RemoteDataRepositoryImpl
import com.teach.moviedb.domain.repository.RemoteDataRepository
import com.teach.moviedb.domain.use_case.GetMovieDataUseCase
import com.teach.moviedb.domain.use_case.GetSearchResultUseCase
import com.teach.moviedb.domain.use_case.UseCases
import com.teach.moviedb.presentation.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi():Moshi= Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideMovieApi(moshi: Moshi):MovieApi{
        return Retrofit.Builder()
            .baseUrl(com.teach.moviedb.util.Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(MovieApi::class.java)
    }
    @Provides
    @Singleton
    fun provideRepository(api:MovieApi):RemoteDataRepository{
        return RemoteDataRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun provideUseCases(repository: RemoteDataRepository):UseCases{
        return UseCases(getMovieDataUseCase = GetMovieDataUseCase(repository),
            getSearchResultUseCase = GetSearchResultUseCase(repository)
        )
    }
  /*  @Provides
    @Singleton
    fun provideMainViewModel():ViewModel{
        return MainViewModel(
            provideUseCases(provideRepository(provideMovieApi(provideMoshi())))
        )
    }*/

}