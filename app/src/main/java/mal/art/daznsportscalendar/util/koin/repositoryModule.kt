package mal.art.daznsportscalendar.util.koin

import mal.art.daznsportscalendar.database.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single { Repository(get()) }
}
