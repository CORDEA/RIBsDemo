package jp.cordea.ribsdemo.ui.regiondetail

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import jp.cordea.ribsdemo.api.response.Region

class RegionDetailPagerAdapter(
    private val onAttach: (container: ViewGroup, item: Region) -> View,
    private val onDetach: (container: ViewGroup, item: Region) -> Unit
) : PagerAdapter() {
    var items: List<Region> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun instantiateItem(container: ViewGroup, position: Int): Any =
        onAttach(container, items[position])

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        onDetach(container, items[position])
    }

    override fun getCount(): Int = items.size

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj
}
