package jp.cordea.ribsdemo.ui.regiondetail

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import jp.cordea.ribsdemo.api.response.Region

class RegionDetailChildView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), RegionDetailChildInteractor.RegionDetailChildPresenter {
    override fun setItem(region: Region) {
    }
}
