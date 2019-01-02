package jp.cordea.ribsdemo

import android.view.ViewGroup
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class RootActivity : RibActivity() {
    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> =
        RootBuilder(object : RootBuilder.ParentComponent {}).build(parentViewGroup)
}
