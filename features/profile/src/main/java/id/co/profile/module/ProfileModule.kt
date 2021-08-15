package id.co.profile.module

import id.co.profile.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ProfileModule {
    val profileModule = module {
        viewModel {
            ProfileViewModel(get())
        }
    }
}