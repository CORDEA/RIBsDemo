package jp.cordea.ribsdemo.ui.login

import android.content.Context
import android.util.AttributeSet
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import jp.cordea.ribsdemo.R

class LoginView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CoordinatorLayout(context, attrs, defStyleAttr), LoginInteractor.LoginPresenter {
    override fun apiKeyChanges(): Observable<String> =
        findViewById<TextInputLayout>(R.id.api_key).editText!!.textChanges().map { it.toString() }

    override fun clicks(): Observable<Unit> =
        findViewById<FloatingActionButton>(R.id.fab).clicks()
}
