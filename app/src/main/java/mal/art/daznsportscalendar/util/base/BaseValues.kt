package mal.art.daznsportscalendar.util.base

import java.text.SimpleDateFormat

class BaseValues {

    interface TimeValues {
        companion object {
            const val YESTERDAY = "Yesterday"
            const val TODAY = "Today"
            const val TOMORROW = "Tomorrow"
        }
    }

    interface Url {
        companion object {
            const val baseUrl = "https://us-central1-dazn-sandbox.cloudfunctions.net/"
        }
    }

    interface TimedEvents {
        companion object {
            const val SPLASH_TIME_IN_MILLIS: Long = 2000
            const val AUTO_REFRESH_PERIOD: Long = 30
        }
    }

    interface Message {
        companion object {
            const val VIDEO_URL_MESSAGE = "VIDEO_URL_MESSAGE"
        }
    }

    interface DateFormat {
        companion object {
            const val longFormat = "yyyy-MM-dd, HH:mm"
            const val shortFormat = "HH:mm"
        }
    }
}