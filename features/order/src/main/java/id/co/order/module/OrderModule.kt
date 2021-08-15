package id.co.order.module

import id.co.order.OrderViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object OrderModule {
    val orderModule = module {
        viewModel {
            OrderViewModel(get())
        }
    }
}