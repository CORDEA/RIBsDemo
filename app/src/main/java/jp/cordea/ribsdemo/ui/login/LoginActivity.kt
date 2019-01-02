package jp.cordea.ribsdemo.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import jp.cordea.ribsdemo.R
import jp.cordea.ribsdemo.databinding.ActivityLoginBinding
import jp.cordea.ribsdemo.event.login.LoginActionCreator
import jp.cordea.ribsdemo.event.login.LoginStore
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {
    @Inject
    lateinit var store: LoginStore

    @Inject
    lateinit var creator: LoginActionCreator

    @Inject
    lateinit var navigator: LoginNavigator

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.login_rib)

        binding.content.apiKey.editText!!
            .textChanges()
            .subscribeBy { creator.changedText(it.toString()) }
            .addTo(compositeDisposable)
        binding.fab
            .clicks()
            .subscribeBy { creator.clicked() }
            .addTo(compositeDisposable)

        store.onReady()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { navigator.navigateToMain() }
            .addTo(compositeDisposable)
        store.onError()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { }
            .addTo(compositeDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
