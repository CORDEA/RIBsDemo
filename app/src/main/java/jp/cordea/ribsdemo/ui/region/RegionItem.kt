package jp.cordea.ribsdemo.ui.region

import com.xwray.groupie.databinding.BindableItem
import jp.cordea.ribsdemo.R
import jp.cordea.ribsdemo.databinding.ListItemRegionBinding
import jp.cordea.ribsdemo.event.region.RegionActionCreator
import javax.inject.Inject

class RegionItem private constructor(
    private val creator: RegionActionCreator,
    private val viewModel: RegionItemViewModel
) : BindableItem<ListItemRegionBinding>() {
    class Factory @Inject constructor(private val creator: RegionActionCreator) {
        fun create(viewModel: RegionItemViewModel) = RegionItem(creator, viewModel)
    }

    override fun getLayout(): Int = R.layout.list_item_region

    override fun bind(viewBinding: ListItemRegionBinding, position: Int) {
        viewBinding.vm = viewModel
        viewBinding.root.setOnClickListener { creator.clickedItem(position) }
    }
}
