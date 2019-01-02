package jp.cordea.ribsdemo.ui.app


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import jp.cordea.ribsdemo.databinding.FragmentAppBinding
import jp.cordea.ribsdemo.event.app.AppActionCreator
import jp.cordea.ribsdemo.event.app.AppStore
import javax.inject.Inject

class AppFragment : Fragment() {
    companion object {
        fun newInstance(): AppFragment = AppFragment()
    }

    @Inject
    lateinit var itemFactory: AppItem.Factory

    @Inject
    lateinit var store: AppStore

    @Inject
    lateinit var creator: AppActionCreator

    private lateinit var binding: FragmentAppBinding

    private val compositeDisposable = CompositeDisposable()
    private val adapter by lazy { GroupAdapter<ViewHolder>() }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeRefresh
            .refreshes()
            .subscribeBy { creator.refresh() }
            .addTo(compositeDisposable)

        store.onReady()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { adapter.addAll(it.map { itemFactory.create(AppItemViewModel.from(it)) }) }
            .addTo(compositeDisposable)
        store.onError()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { }
            .addTo(compositeDisposable)

        creator.init()
    }
}
