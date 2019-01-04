package jp.cordea.ribsdemo.ui.regiondetail

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager

class RegionDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs), RegionDetailInteractor.RegionDetailPresenter {
    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun moveTo(position: Int) {
    }
}
