package jp.cordea.ribsdemo.event.app

sealed class AppAction {
    object Init : AppAction()
    object Refresh : AppAction()
}
