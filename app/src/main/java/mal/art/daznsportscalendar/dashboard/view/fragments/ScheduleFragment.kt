package mal.art.daznsportscalendar.dashboard.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import io.reactivex.disposables.CompositeDisposable
import mal.art.daznsportscalendar.R
import mal.art.daznsportscalendar.dashboard.model.SportEvent
import mal.art.daznsportscalendar.dashboard.viewmodel.DAZNViewModel
import mal.art.daznsportscalendar.util.base.BaseValues
import mal.art.daznsportscalendar.util.extensions.setVisible
import mal.art.daznsportscalendar.util.item.EventListItem
import mal.art.daznsportscalendar.util.time.TimeHelper
import mal.art.daznsportscalendar.video.activity.VideoPlayerActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleFragment : Fragment(R.layout.schedule_fragment_layout) {
    private val viewModel by viewModel<DAZNViewModel>()
    private val timeHelper by inject<TimeHelper>()
    private val itemAdapter: GenericItemAdapter = ItemAdapter()
    private val adapter: GenericFastAdapter = FastAdapter.with(itemAdapter)
    private val disposable = CompositeDisposable()
    private lateinit var recyclerView: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.schedule_fragment_rv)

        setupAdapter()
    }

    private fun observeViewModel() {
        viewModel.subject.subscribe {
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
        val scheduledEvents = viewModel.filterTodayEvents(data)
        if (scheduledEvents.isNotEmpty()) {
            scheduledEvents
                .map { EventListItem(it) }
                .let {
                    itemAdapter.clear()
                    itemAdapter.add(it)
                }
        } else {
            val noEventsTv: TextView = view!!.findViewById(R.id.no_events_tv)
            noEventsTv.setVisible()
        }
    }

    private fun setupAdapter() {
        recyclerView.also {
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
        val errorTv: TextView = view!!.findViewById(R.id.scheduled_events_fragment_error_tv)
        errorTv.setVisible()
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }
}