package jp.cordea.ribsdemo.event.login

sealed class LoginResult {
    object Success : LoginResult()
    object Failure : LoginResult()
}
