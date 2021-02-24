package mal.art.daznsportscalendar.database.api

import io.reactivex.Single
import mal.art.daznsportscalendar.dashboard.model.SportEvent
import retrofit2.http.GET

interface APIService {

    @GET("/getEvents")
    fun getEvents(): Single<List<SportEvent>>

    @GET("/getSchedule")
    fun getSchedule(): Single<List<SportEvent>>
}