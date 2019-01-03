package jp.cordea.ribsdemo.ui.region

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import jp.cordea.ribsdemo.R

class RegionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SwipeRefreshLayout(context, attrs), RegionInteractor.RegionPresenter {
    private val adapter by lazy { GroupAdapter<ViewHolder>() }

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter
    }

    override fun showItems(items: List<RegionItem>) {
        adapter.clear()
        adapter.addAll(items)
    }
}
