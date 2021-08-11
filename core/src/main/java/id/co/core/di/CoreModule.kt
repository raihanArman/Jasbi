package id.co.core.di

import id.co.core.data.repositories.DataRepository
import id.co.core.data.repositories.remote.RemoteDataSource
import id.co.core.domain.repository.Repository
import org.koin.dsl.module

object CoreModule {
    val repositoryModule = module{
        single {
            RemoteDataSource(get(), get())
        }
//        single {
//            LocalDataSource(get())
//        }
        single<Repository> {
            DataRepository(
                get()
//                get()
            )
        }
    }

}