package jp.cordea.ribsdemo.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import jp.cordea.ribsdemo.R

class MainView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : DrawerLayout(context, attrs, defStyleAttr),
    MainInteractor.MainPresenter,
    NavigationView.OnNavigationItemSelectedListener {

    private val _navigationItemClicks = PublishSubject.create<Int>()

    override fun onFinishInflate() {
        super.onFinishInflate()
        val nav = findViewById<NavigationView>(R.id.nav_view)
        nav.setNavigationItemSelectedListener(this)
    }

    override fun handleDrawer(): Boolean {
        if (isDrawerOpen(GravityCompat.START)) {
            closeDrawer(GravityCompat.START)
            return true
        }
        return false
    }

    override fun navigationItemClicks(): Observable<Int> = _navigationItemClicks

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        _navigationItemClicks.onNext(menu.itemId)
        closeDrawer(GravityCompat.START)
        return true
    }
}
