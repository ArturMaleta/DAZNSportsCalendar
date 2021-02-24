package mal.art.daznsportscalendar.util.koin

import mal.art.daznsportscalendar.util.time.TimeHelper
import org.koin.dsl.module

val timeHelper = module {
    single { TimeHelper() }
}