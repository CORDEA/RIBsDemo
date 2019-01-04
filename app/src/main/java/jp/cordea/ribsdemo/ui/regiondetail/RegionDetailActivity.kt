package jp.cordea.ribsdemo.ui.regiondetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import dagger.android.AndroidInjection

class RegionDetailActivity : RibActivity() {
    companion object {
        private const val POSITION_KEY = "position"

        fun createIntent(context: Context, position: Int): Intent =
            Intent(context, RegionDetailActivity::class.java).apply {
                putExtra(POSITION_KEY, position)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
//        val position = intent.getIntExtra(POSITION_KEY, 0)
    }

    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> =
        RegionDetailBuilder(object : RegionDetailBuilder.ParentComponent {}).build(parentViewGroup)
}
