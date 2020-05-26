package com.unava.dia.trellolight.di

import android.app.Application
import com.unava.dia.trellolight.TrelloLightApp
import com.unava.dia.trellolight.di.network.RoomModule
import com.unava.dia.trellolight.di.useCases.UseCasesModule
import com.unava.dia.trellolight.di.viewModel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        ViewModelModule::class,
        UseCasesModule::class,
        RoomModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: TrelloLightApp)
}