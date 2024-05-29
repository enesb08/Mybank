package com.enesbaskaya.mybank.di

import android.app.Application
import android.content.Context
import com.enesbaskaya.mybank.App
import com.enesbaskaya.mybank.util.LocalDataManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }

    @Singleton
    @Provides
    @ActivityContext
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideLocalDataManager(
        application: Application,
    ): LocalDataManager {
        return LocalDataManager(application);
    }


}