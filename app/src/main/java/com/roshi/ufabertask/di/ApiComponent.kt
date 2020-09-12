package com.roshi.ufabertask.di

import com.roshi.ufabertask.contributor.ContriButorViewModel
import com.roshi.ufabertask.home.HomeViewModel
import com.roshi.ufabertask.repowner.RepoOwnerViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])


interface ApiComponent {
    @Component.Builder
    interface Builder {
        fun build(): ApiComponent
        fun networkModule(networkModule: NetworkModule): Builder
    }

    fun inject(homeViewModel: HomeViewModel)
    fun inject(contriButorViewModel: ContriButorViewModel)
    fun inject(repoOwnerViewModel: RepoOwnerViewModel)






}