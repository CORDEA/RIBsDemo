package jp.cordea.ribsdemo.ui.region


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
//import jp.cordea.ribsdemo.databinding.FragmentRegionBinding
import jp.cordea.ribsdemo.event.region.RegionActionCreator
import jp.cordea.ribsdemo.event.region.RegionStore
import javax.inject.Inject

class RegionFragment : Fragment() {
    companion object {
        fun newInstance(): RegionFragment = RegionFragment()
    }

    @Inject
    lateinit var itemFactory: RegionItem.Factory

    @Inject
    lateinit var store: RegionStore

    @Inject
    lateinit var creator: RegionActionCreator

    @Inject
    lateinit var navigator: RegionNavigator

//    private lateinit var binding: FragmentRegionBinding

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
    ): View? {
//        binding = FragmentRegionBinding.inflate(inflater, container, false)
//        binding.recyclerView.adapter = adapter
//        return binding.root
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.swipeRefresh
//            .refreshes()
//            .subscribeBy { creator.refresh() }
//            .addTo(compositeDisposable)

        store.onReady()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                adapter.addAll(it.map { itemFactory.create(RegionItemViewModel.from(it)) })
            }
            .addTo(compositeDisposable)
        store.onError()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { }
            .addTo(compositeDisposable)
        store.onClickedItem()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { navigator.navigateToDetail(it) }
            .addTo(compositeDisposable)

        creator.init()
    }
}
