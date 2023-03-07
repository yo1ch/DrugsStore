package com.vladimir_tsurko.drugstore.di


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.vladimir_tsurko.drugstore.data.RepositoryImplementation
import com.vladimir_tsurko.drugstore.data.remote.DrugstoreApi
import com.vladimir_tsurko.drugstore.data.remote.RetrofitInstance
import com.vladimir_tsurko.drugstore.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {


    @Binds
    fun bindRepository(implementation: RepositoryImplementation): Repository

    companion object {

        @Provides
        fun provideApiService(): DrugstoreApi {
            return RetrofitInstance.apiService
        }

        @Provides
        fun provideSharedPrefs(
            application: Application
        ): SharedPreferences {
            return application.getSharedPreferences("Auth_data", Context.MODE_PRIVATE)
        }


    }
}