package com.example.imgurgallery.di

import com.example.imgurgallery.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InjectorContributor {
    //    @ContributesAndroidInjector(modules = [MainModule::class])
    @ContributesAndroidInjector
    abstract fun contributeActivityInjector(): MainActivity
}