package mal.art.daznsportscalendar.util.time

import mal.art.daznsportscalendar.util.base.BaseValues
import java.text.SimpleDateFormat
import java.util.*

class TimeHelper {

    fun convertDate(dateInMillis: Long): String {
        val event: Calendar = Calendar.getInstance()
        event.timeInMillis = dateInMillis

        val today: Calendar = Calendar.getInstance()

        val yesterday: Calendar = Calendar.getInstance()
        yesterday.add(Calendar.DATE, -1)

        val tomorrow = Calendar.getInstance()
        tomorrow.add(Calendar.DATE, +1)

        val longFormat = SimpleDateFormat(BaseValues.DateFormat.longFormat)
        val shortFormat = SimpleDateFormat(BaseValues.DateFormat.shortFormat)

        return when {
            yesterday.get(Calendar.DAY_OF_YEAR) == event.get(Calendar.DAY_OF_YEAR) -> {
                "${BaseValues.TimeValues.YESTERDAY}, ${shortFormat.format(event.time)}"
            }
            today.get(Calendar.DAY_OF_YEAR) == event.get(Calendar.DAY_OF_YEAR) -> {
                "${BaseValues.TimeValues.TODAY}, ${shortFormat.format(event.time)}"
            }
            tomorrow.get(Calendar.DAY_OF_YEAR) == event.get(Calendar.DAY_OF_YEAR) -> {
                "${BaseValues.TimeValues.TOMORROW}, ${shortFormat.format(event.time)}"
            }
            else -> {
                longFormat.format(event.time)
            }
        }
    }
}