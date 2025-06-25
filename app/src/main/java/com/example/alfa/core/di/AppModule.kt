package com.example.alfa.core.di

import androidx.room.Room
import com.example.alfa.data.local.db.BinDataBase
import com.example.alfa.data.network.api.BinApiService
import com.example.alfa.data.repository.BinRepositoryImpl
import com.example.alfa.data.repository.CardHistoryRepositoryImpl
import com.example.alfa.domain.repository.BinRepository
import com.example.alfa.domain.repository.CardHistoryRepository
import com.example.alfa.domain.usecases.CardUseCase
import com.example.alfa.domain.usecases.GetBinInfoUseCase
import com.example.alfa.presenter.viewmodel.BinViewModel
import com.example.alfa.presenter.viewmodel.CardViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://lookup.binlist.net/"

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinApiService::class.java)
    }

    single {
        Room.databaseBuilder(
                androidContext(),
                BinDataBase::class.java,
                "bin_database"
            ).fallbackToDestructiveMigration(false)
            .build()
    }

    single { get<BinDataBase>().dao() }

    single<BinRepository> { BinRepositoryImpl(get(), get()) }
    single { GetBinInfoUseCase(get()) }
    viewModel { BinViewModel(get()) }

    single<CardHistoryRepository> { CardHistoryRepositoryImpl(get()) }
    single { CardUseCase(get()) }
    viewModel { CardViewModel(get()) }
}