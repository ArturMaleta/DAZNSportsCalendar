package mal.art.daznsportscalendar.dashboard.view.fragments

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
import mal.art.daznsportscalendar.video.activity.VideoPlayerActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsFragment : Fragment(R.layout.events_fragment_layout) {
    private val viewModel by viewModel<DAZNViewModel>()
    private val itemAdapter: GenericItemAdapter = ItemAdapter()
    private val adapter: GenericFastAdapter = FastAdapter.with(itemAdapter)
    private val disposable = CompositeDisposable()
    private lateinit var recyclerView: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.events_fragment_rv)

        setupAdapter()

        observeViewModel()
        viewModel.getEventsAscending()
    }

    private fun observeViewModel() {
        viewModel.subject.subscribe {
            when (it) {
                is DAZNViewModel.Event.LoadingFailure -> handleLoadingFailure(it.throwable)
                is DAZNViewModel.Event.LoadingEventsSuccess -> handleLoadingSuccess(it.data)
                else -> Unit
            }
        }
    }

    private fun handleLoadingSuccess(data: List<SportEvent>) {
        data.map { EventListItem(it) }
            .let { itemAdapter.add(it) }
    }

    private fun setupAdapter() {
        recyclerView.also {
            it.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            it.adapter = adapter
            it.addItemDecoration(DividerItemDecoration(context, 1))
        }
        setupOnClickListener()
    }

    private fun setupOnClickListener() {
        adapter.onClickListener = { v, adapter, item, position ->
            when (item) {
                is EventListItem -> performEventClick(item.event)
                else -> Unit
            }
            false
        }
    }

    private fun performEventClick(event: SportEvent) =
        startActivity(VideoPlayerActivity.intent(requireContext())
            .apply { putExtra(BaseValues.Message.VIDEO_URL_MESSAGE, event.videoUrl) })

    private fun handleLoadingFailure(throwable: Throwable) {
        val errorTv: TextView = view!!.findViewById(R.id.events_fragment_error_tv)
        errorTv.setVisible()
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }
}