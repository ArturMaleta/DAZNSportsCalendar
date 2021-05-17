package mal.art.daznsportscalendar.database.repository

import io.reactivex.Single
import io.reactivex.observers.TestObserver
import mal.art.daznsportscalendar.dashboard.model.SportEvent
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class RepositoryTest {
    private lateinit var repository: Repository

    private val id1 = 1
    private val title1 = "title1"
    private val subtitle2 = "subtitle2"
    private val date2 = "date2"
    private val videoUrl3 = "videoUrl3"
    private val imageUrl3 = "imageUrl3"
    private val errorMessage = "oops"

    private val events = listOf(
        SportEvent(1, "title1", "subtitle1", "date1", "imageUrl1", "videoUrl1"),
        SportEvent(2, "title2", "subtitle2", "date2", "imageUrl2", "videoUrl2"),
        SportEvent(3, "title3", "subtitle3", "date3", "imageUrl3", "videoUrl3")
    )

    @Before
    fun setUp() {
        repository = mock(Repository::class.java)
    }

    @Test
    fun `test getEvents eventsAreNotEmptyReturned`() {
        // given
        val result = repository.getEvents().blockingGet()

        // when
        `when`(repository.getEvents()).thenReturn(Single.just(events))

        // then
        assert(result.isNotEmpty())
    }

    @Test
    fun `test getEvents listOfEventsEmptyReturned`() {
        // given
        val result = repository.getEvents().blockingGet()

        // when
        `when`(repository.getEvents()).thenReturn(Single.just(emptyList()))

        // then
        assert(result.isEmpty())
    }

    @Test
    fun `test getEvents error occurred errorReturned`() {
        // given
        val result = repository.getEvents()

        // when
        `when`(repository.getEvents()).thenReturn(Single.error(Error(errorMessage)))

        // then
        val testObserver = TestObserver<List<SportEvent>>()
        result.subscribe(testObserver)
        testObserver.assertError(Error(errorMessage).javaClass)
        testObserver.assertErrorMessage(errorMessage)
    }

    @Test
    fun `test getEvents eventsAreEqual`() {
        // given
        val result = repository.getEvents()

        val testObserver = TestObserver<List<SportEvent>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        // when
        `when`(repository.getEvents()).thenReturn(Single.just(events))

        //then
        val listResult = testObserver.values()[0]
        assertThat(listResult.size, `is`(3))
        assertThat(listResult[0].id, `is`(id1))
        assertThat(listResult[0].title, `is`(title1))
        assertThat(listResult[1].subtitle, `is`(subtitle2))
        assertThat(listResult[1].date, `is`(date2))
        assertThat(listResult[2].videoUrl, `is`(videoUrl3))
        assertThat(listResult[2].imageUrl, `is`(imageUrl3))
    }

    @Test
    fun `test getScheduledEvents eventsAreNotEmptyReturned`() {
        // given
        val result = repository.getScheduledEvents().blockingGet()

        // when
        `when`(repository.getScheduledEvents()).thenReturn(Single.just(events))

        //then
        assert(result.isNotEmpty())
    }

    @Test
    fun ` test getScheduledEvents listOfEventsEmptyReturned`() {
        // given
        val result = repository.getScheduledEvents().blockingGet()

        // when
        `when`(repository.getScheduledEvents()).thenReturn(Single.just(emptyList()))

        // then
        assert(result.isEmpty())
    }

    @Test
    fun `test getScheduledEvents error occurred errorReturned`() {
        //given
        val result = repository.getScheduledEvents()

        // when
        `when`(repository.getScheduledEvents()).thenReturn(Single.error(Error(errorMessage)))

        //then
        val testObserver = TestObserver<List<SportEvent>>()
        result.subscribe(testObserver)
        testObserver.assertError(Error(errorMessage).javaClass)
        testObserver.assertErrorMessage(errorMessage)
    }

    @Test
    fun `test getScheduledEvents eventsAreEqual`() {
        //given
        val result = repository.getScheduledEvents()

        val testObserver = TestObserver<List<SportEvent>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        // when
        `when`(repository.getScheduledEvents()).thenReturn(Single.just(events))

        // then
        val listResult = testObserver.values()[0]
        assertThat(listResult.size, `is`(3))
        assertThat(listResult[0].id, `is`(id1))
        assertThat(listResult[0].title, `is`(title1))
        assertThat(listResult[1].subtitle, `is`(subtitle2))
        assertThat(listResult[1].date, `is`(date2))
        assertThat(listResult[2].videoUrl, `is`(videoUrl3))
        assertThat(listResult[2].imageUrl, `is`(imageUrl3))
    }
}

