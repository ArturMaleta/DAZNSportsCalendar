package mal.art.daznsportscalendar.database.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mal.art.daznsportscalendar.database.api.APIService

class Repository (
    private val apiService: APIService
) {
    fun getEvents() = apiService.getEvents().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    fun getScheduledEvents() = apiService.getSchedule().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}