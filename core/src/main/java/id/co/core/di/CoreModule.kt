package id.co.core.di

import androidx.room.Room
import id.co.core.data.db.AppDatabase
import id.co.core.data.repositories.DataRepository
import id.co.core.data.repositories.local.LocalDataSource
import id.co.core.data.repositories.remote.RemoteDataSource
import id.co.core.domain.repository.Repository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object CoreModule {

    val databaseModule = module {
        factory {
            get<AppDatabase>().databaseDao()
        }
        single {
            val passphrase = SQLiteDatabase.getBytes("mantap".toCharArray())
            val factory = SupportFactory(passphrase)

            Room.databaseBuilder(
                androidContext(),
                AppDatabase::class.java, "jasbi.db"
            ).fallbackToDestructiveMigration()
                .openHelperFactory(factory)
                .allowMainThreadQueries()
                .build()
        }
    }

    val repositoryModule = module{
        single {
            RemoteDataSource(get(), get())
        }
        single {
            LocalDataSource(get())
        }
        single<Repository> {
            DataRepository(
                get(),
                get()
            )
        }
    }

}