package com.wreckingballsoftware.fiveoclocksomewhere.di

import androidx.room.Room
import com.wreckingballsoftware.fiveoclocksomewhere.database.ItsFiveOClockSomewhereDb
import com.wreckingballsoftware.fiveoclocksomewhere.repos.CocktailsRepo
import com.wreckingballsoftware.fiveoclocksomewhere.repos.CountriesRepo
import com.wreckingballsoftware.fiveoclocksomewhere.repos.TimeZonesRepo
import com.wreckingballsoftware.fiveoclocksomewhere.ui.intro.IntroViewModel
import com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val DB_NAME = "fiveoclocksomewhere"

val appModule = module {
    viewModel {
        IntroViewModel()
    }

    viewModel {
        MainViewModel(
            handle = get(),
            countriesRepo = get(),
            cocktailsRepo = get(),
        )
    }

    single {
        TimeZonesRepo(
            timeZonesDao = get(),
        )
    }

    single {
        CountriesRepo(
            timeZonesRepo = get(),
            countriesDao = get(),
        )
    }

    single {
        CocktailsRepo(
            timeZonesRepo = get(),
            cocktailsDao = get(),
            regionalCocktailsDao = get(),
        )
    }

    single {
        val database = get<ItsFiveOClockSomewhereDb>()
        database.getCountriesDao()
    }

    single {
        val database = get<ItsFiveOClockSomewhereDb>()
        database.getTimeZonesDao()
    }

    single {
        val database = get<ItsFiveOClockSomewhereDb>()
        database.getCocktailsDao()
    }

    single {
        val database = get<ItsFiveOClockSomewhereDb>()
        database.getRegionalCocktailsDao()
    }

    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = ItsFiveOClockSomewhereDb::class.java,
            name = DB_NAME,
        )
            .createFromAsset("databases/$DB_NAME.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}
