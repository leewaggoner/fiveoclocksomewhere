package com.wreckingballsoftware.fiveoclocksomewhere.di

import androidx.room.Room
import com.wreckingballsoftware.fiveoclocksomewhere.database.ItsFiveOClockSomewhereDb
import com.wreckingballsoftware.fiveoclocksomewhere.repos.CocktailsRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val DB_NAME = "fiveoclocksomewhere"

val appModule = module {
    single {
        CocktailsRepo(
            cocktailsDao = get()
        )
    }

    single {
        val database = get<ItsFiveOClockSomewhereDb>()
        database.getCocktailsDao()
    }

    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = ItsFiveOClockSomewhereDb::class.java,
            name = DB_NAME,
        )
            .createFromAsset("databases/$DB_NAME.db")
            .build()
    }
}