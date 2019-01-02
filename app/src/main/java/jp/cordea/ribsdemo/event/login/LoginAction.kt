package jp.cordea.ribsdemo.event.login

sealed class LoginAction {
    class ChangedText(val query: String) : LoginAction()
    object Clicked : LoginAction()
}
