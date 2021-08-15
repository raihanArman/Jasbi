package id.co.payment.module

import id.co.payment.PaymentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PaymentModule {
    val paymentModule = module{
        viewModel {
            PaymentViewModel(get())
        }
    }
}