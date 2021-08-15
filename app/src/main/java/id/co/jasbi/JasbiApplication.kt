package id.co.jasbi

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import id.co.core.di.CoreModule
import id.co.datastore.module.DataStoreModule.dataStoreModule
import id.co.quizapp.module.AppModule
import id.co.quizapp.module.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class JasbiApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@JasbiApplication)
            modules(listOf(
                CoreModule.databaseModule,
                dataStoreModule,
                NetworkModule.networkModule,
                CoreModule.repositoryModule,
                AppModule.useCaseModule
            ))
        }
    }
}