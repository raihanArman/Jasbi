package id.co.jasbi.module

import id.co.jasbi.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModule {
    val mainModule = module {
        viewModel {
            HomeViewModel(get())
        }
    }
}