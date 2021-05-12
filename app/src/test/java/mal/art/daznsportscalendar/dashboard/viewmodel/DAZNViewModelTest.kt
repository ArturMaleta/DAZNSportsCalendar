package mal.art.daznsportscalendar.dashboard.viewmodel

import io.reactivex.Single
import io.reactivex.observers.TestObserver
import mal.art.daznsportscalendar.dashboard.model.SportEvent
import mal.art.daznsportscalendar.database.repository.Repository
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DAZNViewModelTest {

    private lateinit var viewModel: DAZNViewModel
    private lateinit var repository: Repository

    private val id1 = 1
    private val title1 = "title1"
    private val subtitle2 = "subtitle2"
    private val date2 = "date2"
    private val videoUrl3 = "videoUrl3"
    private val imageUrl3 = "imageUrl3"

    private val mixedEvents = listOf(
        SportEvent(3, "title3", "subtitle3", "date3", "imageUrl3", "videoUrl3"),
        SportEvent(1, "title1", "subtitle1", "date1", "imageUrl1", "videoUrl1"),
        SportEvent(2, "title2", "subtitle2", "date2", "imageUrl2", "videoUrl2")
    )

    private val eventsAscending = listOf(
        SportEvent(1, "title1", "subtitle1", "date1", "imageUrl1", "videoUrl1"),
        SportEvent(2, "title2", "subtitle2", "date2", "imageUrl2", "videoUrl2"),
        SportEvent(3, "title3", "subtitle3", "date3", "imageUrl3", "videoUrl3")
    )

    @Before
    fun setUp() {
        repository = mock(Repository::class.java)
        viewModel = DAZNViewModel(repository)
    }

}