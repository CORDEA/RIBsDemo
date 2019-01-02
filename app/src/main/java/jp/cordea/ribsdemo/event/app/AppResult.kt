package jp.cordea.ribsdemo.event.app

import jp.cordea.ribsdemo.api.response.Application

sealed class AppResult {
    class Success(val apps: Collection<Application>) : AppResult()
    object Failure : AppResult()
}
