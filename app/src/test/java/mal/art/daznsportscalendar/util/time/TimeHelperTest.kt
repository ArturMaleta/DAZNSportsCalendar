package mal.art.daznsportscalendar.util.time

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TimeHelperTest {
    private lateinit var timeHelper: TimeHelper
    private val hour = "00:58"
    private val yesterdayHourInMillis = 1619564280000
    private val todayHourInMillis = 1619650680000
    private val tomorrowHourInMillis = 1619737080000
    private val twoDaysInAdvanceHourInMillis = 1619823480000
    private val fourteenDaysEarlierHourInMillis = 1618441080000


    @Before
    fun setUp() {
        timeHelper = TimeHelper()
    }

    @Test
    fun convertDate_correctYesterdayHourData_returnsTrue() {
        val result = timeHelper.convertDate(yesterdayHourInMillis)

        assertThat(result, `is`("Yesterday, $hour"))
    }

    @Test
    fun convertDate_correctCurrentHourData_returnsTrue() {
        val result = timeHelper.convertDate(todayHourInMillis)

        assertThat(result, `is`("Today, $hour"))
    }

    @Test
    fun convertDate_correctTomorrowHourData_returnsTrue() {
        val result = timeHelper.convertDate(tomorrowHourInMillis)

        assertThat(result, `is`("Tomorrow, $hour"))
    }

    @Test
    fun convertDate_correctTwoDaysInAdvanceHourData_returnsTrue() {
        val result = timeHelper.convertDate(twoDaysInAdvanceHourInMillis)

        assertThat(result, `is`("2021-05-01, 00:58"))
    }

    @Test
    fun convertDate_correctfourteenDaysEarlierHourData_returnsTrue() {
        val result = timeHelper.convertDate(fourteenDaysEarlierHourInMillis)

        assertThat(result, `is`("2021-04-15, 00:58"))
    }
}