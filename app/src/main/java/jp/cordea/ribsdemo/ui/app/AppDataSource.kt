package jp.cordea.ribsdemo.ui.app

import io.reactivex.Maybe
import jp.cordea.ribsdemo.api.response.Application

interface AppDataSource {
    fun fetchApp(): Maybe<Collection<Application>>
}
