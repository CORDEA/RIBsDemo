package jp.cordea.ribsdemo.ui.app

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import io.reactivex.Observable
import jp.cordea.ribsdemo.R

class AppView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SwipeRefreshLayout(context, attrs), AppInteractor.AppPresenter {
    private val adapter by lazy { GroupAdapter<ViewHolder>() }

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter
    }

    override fun swipeRefreshes(): Observable<Unit> = refreshes()

    override fun showItems(items: List<AppItem>) {
        adapter.clear()
        adapter.addAll(items)
    }

    override fun completeUpdate() {
        isRefreshing = false
    }
}
