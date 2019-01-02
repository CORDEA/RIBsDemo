package jp.cordea.ribsdemo.ui.regiondetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import jp.cordea.ribsdemo.api.response.Region
import jp.cordea.ribsdemo.databinding.FragmentRegionDetailBinding

class RegionDetailFragment : Fragment() {
    companion object {
        private const val REGION_KEY = "region"

        fun newInstance(region: Region): RegionDetailFragment =
            RegionDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(REGION_KEY, region)
                }
            }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentRegionDetailBinding.inflate(inflater, container, false).apply {
        }.root
}
