package jp.cordea.ribsdemo.ui.regiondetail

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import jp.cordea.ribsdemo.R
import jp.cordea.ribsdemo.api.response.Region

class RegionDetailChildView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), RegionDetailChildInteractor.RegionDetailChildPresenter {
    override fun setItem(region: Region) {
        findViewById<TextView>(R.id.title).text = region.name
        findViewById<TextView>(R.id.description).text = region.country
        findViewById<TextView>(R.id.continent).text = region.continent
    }
}
