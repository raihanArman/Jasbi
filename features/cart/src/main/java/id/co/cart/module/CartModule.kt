package id.co.cart.module

import id.co.cart.CartViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CartModule {
    val cartModule = module{
        viewModel {
            CartViewModel(get())
        }
    }
}