package mal.art.daznsportscalendar.util.koin

import mal.art.daznsportscalendar.dashboard.viewmodel.DAZNViewModel
import mal.art.daznsportscalendar.util.time.TimeHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DAZNViewModel(repository = get()) }
}

