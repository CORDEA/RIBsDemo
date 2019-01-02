package jp.cordea.ribsdemo.ui.region

import androidx.fragment.app.Fragment
import jp.cordea.ribsdemo.api.response.Region
import jp.cordea.ribsdemo.di.FragmentScope
import jp.cordea.ribsdemo.ui.regiondetail.RegionDetailActivity
import javax.inject.Inject

@FragmentScope
class RegionNavigator @Inject constructor(
    private val fragment: Fragment
) {
    fun navigateToDetail(position: Int) {
        val context = fragment.context ?: return
        fragment.startActivity(
            RegionDetailActivity.createIntent(context, position)
        )
    }
}
