package jp.cordea.ribsdemo.ui.regiondetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import jp.cordea.ribsdemo.api.response.Region
import jp.cordea.ribsdemo.di.ActivityScope
import javax.inject.Inject

@ActivityScope
class RegionDetailFragmentPagerAdapter @Inject constructor(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {
    var items: List<Region> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItem(position: Int): Fragment = RegionDetailFragment.newInstance(items[position])

    override fun getCount(): Int = items.size
}
