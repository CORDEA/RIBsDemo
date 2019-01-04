package jp.cordea.ribsdemo.ui.regiondetail

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import jp.cordea.ribsdemo.R
import jp.cordea.ribsdemo.api.response.Region

class RegionDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs), RegionDetailInteractor.RegionDetailPresenter {
    override fun onFinishInflate() {
        super.onFinishInflate()

        pageMargin = resources.getDimensionPixelSize(
            R.dimen.activity_region_detail_view_pager_margin
        )
    }

    override fun setAdapter(adapter: RegionDetailPagerAdapter) {
        this.adapter = adapter
    }

    override fun showItems(items: Collection<Region>) {
        (adapter as RegionDetailPagerAdapter).items = items.toList()
    }

    override fun moveTo(position: Int) {
        currentItem = position
    }
}
