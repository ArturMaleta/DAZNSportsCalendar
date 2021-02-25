package mal.art.daznsportscalendar.dashboard.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import io.reactivex.disposables.CompositeDisposable
import mal.art.daznsportscalendar.dashboard.model.SportEvent
import mal.art.daznsportscalendar.dashboard.viewmodel.DAZNViewModel
import mal.art.daznsportscalendar.databinding.ScheduleFragmentLayoutBinding
import mal.art.daznsportscalendar.util.base.BaseValues
import mal.art.daznsportscalendar.util.extensions.setGone
import mal.art.daznsportscalendar.util.extensions.setVisible
import mal.art.daznsportscalendar.util.item.EventListItem
import mal.art.daznsportscalendar.video.activity.VideoPlayerActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleFragment : Fragment() {
    private val viewModel by viewModel<DAZNViewModel>()
    private val itemAdapter: GenericItemAdapter = ItemAdapter()
    private val adapter: GenericFastAdapter = FastAdapter.with(itemAdapter)
    private val disposable = CompositeDisposable()
    private var _binding: ScheduleFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScheduleFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
    }

    private fun observeViewModel() {
        viewModel.subject
            .doOnSubscribe { showLoader() }
            .subscribe {
            when (it) {
                is DAZNViewModel.Event.LoadingFailure -> handleLoadingFailure(it.throwable)
                is DAZNViewModel.Event.LoadingScheduledEventsSuccess -> handleLoadingSuccess(it.data)
                else -> Unit
            }
        }
    }

    override fun onResume() {
        super.onResume()
        observeViewModel()
        viewModel.getScheduledEventsAscendingWithAutoRefresh()
    }

    private fun handleLoadingSuccess(data: List<SportEvent>) {
        hideLoader()
        val scheduledEvents = viewModel.filterTodayEvents(data)
        if (scheduledEvents.isNotEmpty()) {
            scheduledEvents
                .map { EventListItem(it) }
                .let {
                    itemAdapter.clear()
                    itemAdapter.add(it)
                }
        } else {
            binding.noEventsTv.setVisible()
        }
    }

    private fun setupAdapter() {
        binding.scheduleFragmentRv.also {
            it.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            it.adapter = adapter
            it.addItemDecoration(DividerItemDecoration(context, 1))
        }
        setupClickListener()
    }

    private fun setupClickListener() {
        adapter.onClickListener = { v, adapter, item, position ->
            when (item) {
                is EventListItem -> performEventClick(item.event)
                else -> Unit
            }
            disposable.clear()
            false
        }
    }

    private fun performEventClick(event: SportEvent) {
        val intent = Intent(activity, VideoPlayerActivity::class.java).apply {
            putExtra(BaseValues.Message.VIDEO_URL_MESSAGE, event.videoUrl)
        }
        startActivity(intent)
    }

    private fun handleLoadingFailure(throwable: Throwable) {
        hideLoader()
        binding.scheduledEventsFragmentErrorTv.setVisible()
    }

    private fun showLoader() {
        binding.progressBar.setVisible()
    }

    private fun hideLoader() {
        binding.progressBar.setGone()
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }
}