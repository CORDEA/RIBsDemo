package jp.cordea.ribsdemo.di

import dagger.Module
import jp.cordea.ribsdemo.ui.login.LoginActivityModule
import jp.cordea.ribsdemo.ui.main.MainActivityModule
import jp.cordea.ribsdemo.ui.regiondetail.RegionDetailActivityModule

@Module(
    includes = [
        MainActivityModule::class,
        LoginActivityModule::class,
        RegionDetailActivityModule::class
    ]
)
class ActivityModule
