package jp.cordea.ribsdemo.ui.region

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import jp.cordea.ribsdemo.R

class RegionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SwipeRefreshLayout(context, attrs), RegionInteractor.RegionPresenter {
    private val adapter by lazy { GroupAdapter<ViewHolder>() }
    private val _itemClicks = PublishSubject.create<Int>()

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter
        adapter.setOnItemClickListener { item, _ ->
            _itemClicks.onNext((item as RegionItem).position)
        }
    }

    override fun swipeRefreshes(): Observable<Unit> = refreshes()

    override fun itemClicks(): Observable<Int> = _itemClicks

    override fun showItems(items: List<RegionItem>) {
        adapter.clear()
        adapter.addAll(items)
    }

    override fun completeUpdate() {
        isRefreshing = false
    }
}
