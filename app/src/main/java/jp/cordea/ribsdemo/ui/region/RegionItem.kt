package jp.cordea.ribsdemo.ui.region

import com.xwray.groupie.databinding.BindableItem
import jp.cordea.ribsdemo.R
import jp.cordea.ribsdemo.databinding.ListItemRegionBinding
import javax.inject.Inject

class RegionItem private constructor(
    private val viewModel: RegionItemViewModel,
    val position: Int
) : BindableItem<ListItemRegionBinding>() {
    class Factory @Inject constructor() {
        fun create(viewModel: RegionItemViewModel, position: Int) = RegionItem(viewModel, position)
    }

    override fun getLayout(): Int = R.layout.list_item_region

    override fun bind(viewBinding: ListItemRegionBinding, position: Int) {
        viewBinding.vm = viewModel
    }
}
