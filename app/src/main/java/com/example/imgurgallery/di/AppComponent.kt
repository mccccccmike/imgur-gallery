package com.example.imgurgallery.di

import com.example.imgurgallery.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, InjectorContributor::class])
interface AppComponent : AndroidInjector<App> {

}