package com.vladimir_tsurko.drugsstore.di

import androidx.lifecycle.ViewModel
import com.vladimir_tsurko.drugsstore.presentation.viewmodels.AuthViewModel
import com.vladimir_tsurko.drugsstore.presentation.viewmodels.CatalogViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindLoginViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CatalogViewModel::class)
    fun bindCatalogViewModel(viewModel: CatalogViewModel): ViewModel
}