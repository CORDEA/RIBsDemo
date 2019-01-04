package jp.cordea.ribsdemo.ui.regiondetail

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class RegionDetailChildView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), RegionDetailChildInteractor.RegionDetailChildPresenter {
    override fun onFinishInflate() {
        super.onFinishInflate()
    }
}
