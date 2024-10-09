package com.aichat.ainox.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import com.aichat.ainox.ui.MainActivity
import javax.inject.Named


@Component
interface AppComponent {

    @Component.Factory
    interface Factory {

        @Named("AppContext")
        fun create(@BindsInstance context: Context): AppComponent
    }


    fun inject(mainActivity: MainActivity)

}