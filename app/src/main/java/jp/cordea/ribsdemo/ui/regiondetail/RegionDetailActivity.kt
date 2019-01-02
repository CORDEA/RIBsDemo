package jp.cordea.ribsdemo.ui.regiondetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import jp.cordea.ribsdemo.R
import jp.cordea.ribsdemo.databinding.ActivityRegionDetailBinding
import jp.cordea.ribsdemo.event.regiondetail.RegionDetailActionCreator
import jp.cordea.ribsdemo.event.regiondetail.RegionDetailStore
import javax.inject.Inject

class RegionDetailActivity : AppCompatActivity(), HasSupportFragmentInjector {
    companion object {
        private const val POSITION_KEY = "position"

        fun createIntent(context: Context, position: Int): Intent =
            Intent(context, RegionDetailActivity::class.java).apply {
                putExtra(POSITION_KEY, position)
            }
    }

    @Inject
    lateinit var adapter: RegionDetailFragmentPagerAdapter

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var store: RegionDetailStore

    @Inject
    lateinit var creator: RegionDetailActionCreator

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityRegionDetailBinding>(
            this,
            R.layout.activity_region_detail
        )
        setSupportActionBar(binding.toolbar)

        val position = intent.getIntExtra(POSITION_KEY, 0)
        binding.content.viewPager.also {
            it.adapter = adapter
            it.pageMargin = resources.getDimensionPixelSize(
                R.dimen.activity_region_detail_view_pager_margin
            )
        }

        store.onReady()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                adapter.items = it.toList()
                binding.content.viewPager.currentItem = position
            }
            .addTo(compositeDisposable)
        store.onError()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { }
            .addTo(compositeDisposable)

        creator.init()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector
}
