package jp.cordea.ribsdemo.ui.regiondetail

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import jp.cordea.ribsdemo.api.response.Region

class RegionDetailPagerAdapter(
    private val regionDetailChildBuilder: RegionDetailChildBuilder
) : PagerAdapter() {
    var items: List<Region> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val router = regionDetailChildBuilder.build(container)
        router.interactor.region = items[position]
        container.addView(router.view)
        return router.view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun getCount(): Int = items.size

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj
}
