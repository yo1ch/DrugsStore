package com.vladimir_tsurko.drugsstore.di

import android.app.Application
import com.vladimir_tsurko.drugstore.presentation.fragments.CatalogFragment
import com.vladimir_tsurko.drugstore.presentation.fragments.LoginFragment
import com.vladimir_tsurko.drugstore.presentation.fragments.RegistrationFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
    ]
)
interface ApplicationComponent {

    fun inject(fragment: RegistrationFragment)

    fun inject(fragment: LoginFragment)

    fun inject(fragment: CatalogFragment)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}