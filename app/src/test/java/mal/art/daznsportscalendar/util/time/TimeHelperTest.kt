package mal.art.daznsportscalendar.util.time

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

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
    fun convertDate_correctYesterdayHourData_returnsCorrectOutput() {
        val result = timeHelper.convertDate(yesterdayHourInMillis)

        assertThat(result, `is`("$yesterday, $hour"))
    }

    @Test
    fun convertDate_correctCurrentHourData_returnsCorrectOutput() {
        val result = timeHelper.convertDate(todayHourInMillis)

        assertThat(result, `is`("$today, $hour"))
    }

    @Test
    fun convertDate_correctTomorrowHourData_returnsCorrectOutput() {
        val result = timeHelper.convertDate(tomorrowHourInMillis)

        assertThat(result, `is`("$tomorrow, $hour"))
    }

    @Test
    fun convertDate_correctTwoDaysInAdvanceHourData_returnsCorrectOutput() {
        val result = timeHelper.convertDate(twoDaysInAdvanceHourInMillis)

        assertThat(result, `is`("$dayInFuture, $hour"))
    }

    @Test
    fun convertDate_correctFourteenDaysEarlierHourData_returnsCorrectOutput() {
        val result = timeHelper.convertDate(fourteenDaysEarlierHourInMillis)

        assertThat(result, `is`("$dayInPast, $hour"))
    }

    @Test
    fun convertDate_zeroAsInput_returns1970() {
        val result = timeHelper.convertDate(zeroMillis)

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