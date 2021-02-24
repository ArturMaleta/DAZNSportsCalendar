package mal.art.daznsportscalendar.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import mal.art.daznsportscalendar.dashboard.model.SportEvent
import mal.art.daznsportscalendar.database.repository.Repository
import mal.art.daznsportscalendar.util.base.BaseValues
import org.joda.time.DateTime
import java.util.*
import java.util.concurrent.TimeUnit

class DAZNViewModel(private val repository: Repository) : ViewModel() {
    private val disposable = CompositeDisposable()
    val subject = PublishSubject.create<Event>()

    sealed class Event {
        data class LoadingFailure(val throwable: Throwable) : Event()
        data class LoadingEventsSuccess(val data: List<SportEvent>) : Event()
        data class LoadingScheduledEventsSuccess(val data: List<SportEvent>) : Event()
    }

    fun getEventsAscending() {
        repository.getEvents()
            .map { events -> events.sortedBy { it.date } }
            .subscribe(
                { subject.onNext(Event.LoadingEventsSuccess(it)) },
                { subject.onNext(Event.LoadingFailure(it)) })
            .let { disposable.add(it) }
    }

    fun getScheduledEventsAscendingWithAutoRefresh() {
        Observable.interval(BaseValues.TimedEvents.AUTO_REFRESH_PERIOD, TimeUnit.SECONDS).startWith(0)
            .flatMapSingle { repository.getScheduledEvents() }
            .map { events -> events.sortedBy { it.date } }
            .subscribe(
                { subject.onNext(Event.LoadingScheduledEventsSuccess(it)) },
                { subject.onNext(Event.LoadingFailure(it)) })
            .let { disposable.add(it) }
    }

    fun filterTodayEvents(eventsList: List<SportEvent>): List<SportEvent> {
        var todayEvents: MutableList<SportEvent> = mutableListOf()
        val today = Calendar.getInstance()

        for(event: SportEvent in eventsList) {
            val eventDate: Calendar = Calendar.getInstance()
            eventDate.timeInMillis = DateTime(event.date).millis
            if (eventDate.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
                todayEvents.add(event)
            }
        }

        return todayEvents
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}