package id.co.quizapp.module

import id.co.core.domain.iterator.Iterator
import id.co.core.domain.usecase.Usecase
import org.koin.dsl.module

object AppModule {
    val useCaseModule = module{
        factory<Usecase>{
            Iterator(get())
        }
    }
}