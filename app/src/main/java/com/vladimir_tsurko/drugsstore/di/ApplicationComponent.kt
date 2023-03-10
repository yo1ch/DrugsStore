package com.vladimir_tsurko.drugsstore.di


import android.app.Application
import com.vladimir_tsurko.drugsstore.presentation.fragments.*
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

    fun inject(fragment: PurchaseFragment)

    fun inject(fragment: OrdersFragment)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}