package mal.art.daznsportscalendar.util.time

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.text.SimpleDateFormat
import java.util.*

@RunWith(JUnit4::class)
class TimeHelperTest {
    private lateinit var timeHelper: TimeHelper

    private val yesterday = "Yesterday"
    private val today = "Today"
    private val tomorrow = "Tomorrow"
    private val dayInFuture = "2021-05-01"
    private val dayInPast = "2021-04-15"
    private val hour = getHour()
    private val zeroHour = "01:00"
    private val zeroDay = "1970-01-01"
    private val yesterdayHourInMillis = getYesterdayInMillis()
    private val todayHourInMillis = System.currentTimeMillis()
    private val tomorrowHourInMillis = getTomorrowInMillis()
    private val twoDaysInAdvanceHourInMillis = getTwoDaysInAdvanceInMillis()
    private val fourteenDaysEarlierHourInMillis = getFourteenDaysEarlierInMillis()
    private val zeroMillis = 0L


    @Before
    fun setUp() {
        timeHelper = TimeHelper()
    }

    @Test
    fun `test convertDate correctYesterdayHourData returnsCorrectOutput`() {
        // given
        val result = timeHelper.convertDate(yesterdayHourInMillis)

        // then
        assertThat(result, `is`("$yesterday, $hour"))
    }

    @Test
    fun `test convertDate correctCurrentHourData returnsCorrectOutput`() {
        // given
        val result = timeHelper.convertDate(todayHourInMillis)

        // then
        assertThat(result, `is`("$today, $hour"))
    }

    @Test
    fun `test convertDate correctTomorrowHourData returnsCorrectOutput`() {
        // given
        val result = timeHelper.convertDate(tomorrowHourInMillis)

        // then
        assertThat(result, `is`("$tomorrow, $hour"))
    }

    @Test
    fun `test convertDate correctTwoDaysInAdvanceHourData returnsCorrectOutput`() {
        // given
        val result = timeHelper.convertDate(twoDaysInAdvanceHourInMillis)

        // then
        assertThat(result, `is`("$dayInFuture, $hour"))
    }

    @Test
    fun `test convertDate correctFourteenDaysEarlierHourData returnsCorrectOutput`() {
        // given
        val result = timeHelper.convertDate(fourteenDaysEarlierHourInMillis)

        // then
        assertThat(result, `is`("$dayInPast, $hour"))
    }

    @Test
    fun `test convertDate zeroAsInput returns1970`() {
        // when
        val result = timeHelper.convertDate(zeroMillis)

        // then
        assertThat(result, `is`("$zeroDay, $zeroHour"))
    }

    private fun getHour(): String {
        val hour = Date(Calendar.getInstance().timeInMillis)
        val format = SimpleDateFormat("HH:mm")

        return format.format(hour)
    }

    private fun getYesterdayInMillis(): Long {
        val yesterday = Calendar.getInstance()
        yesterday.add(Calendar.DATE, -1)
        yesterday.get(Calendar.DAY_OF_YEAR)

        return yesterday.timeInMillis
    }


    private fun getTomorrowInMillis(): Long {
        val tomorrow = Calendar.getInstance()
        tomorrow.add(Calendar.DATE, +1)
        tomorrow.get(Calendar.DAY_OF_YEAR)

        return tomorrow.timeInMillis
    }

    private fun getTwoDaysInAdvanceInMillis(): Long {
        val twoDaysInAdvance = Calendar.getInstance()
        twoDaysInAdvance.add(Calendar.DATE, +2)
        twoDaysInAdvance.get(Calendar.DAY_OF_YEAR)

        return twoDaysInAdvance.timeInMillis
    }

    private fun getFourteenDaysEarlierInMillis(): Long {
        val fourteenDaysInAdvance = Calendar.getInstance()
        fourteenDaysInAdvance.add(Calendar.DATE, -14)
        fourteenDaysInAdvance.get(Calendar.DAY_OF_YEAR)

        return fourteenDaysInAdvance.timeInMillis
    }
}