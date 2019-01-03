package jp.cordea.ribsdemo.ui.main

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import jp.cordea.ribsdemo.R

class MainActivity : RibActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> =
        MainBuilder(object : MainBuilder.ParentComponent {}).build(parentViewGroup)
}
