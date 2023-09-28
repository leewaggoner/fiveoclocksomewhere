package com.wreckingballsoftware.fiveoclocksomewhere.di

import androidx.room.Room
import com.wreckingballsoftware.fiveoclocksomewhere.BuildConfig
import com.wreckingballsoftware.fiveoclocksomewhere.database.ItsFiveOClockSomewhereDb
import com.wreckingballsoftware.fiveoclocksomewhere.network.CocktailDBService
import com.wreckingballsoftware.fiveoclocksomewhere.repos.CocktailsRepo
import com.wreckingballsoftware.fiveoclocksomewhere.repos.PlacesRepo
import com.wreckingballsoftware.fiveoclocksomewhere.repos.TimeZonesRepo
import com.wreckingballsoftware.fiveoclocksomewhere.ui.displaycocktail.DisplayCocktailViewModel
import com.wreckingballsoftware.fiveoclocksomewhere.ui.intro.IntroViewModel
import com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen.MainViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val DB_NAME = "fiveoclocksomewhere"
private const val CONNECT_TIMEOUT = 30L
private const val READ_TIMEOUT = 30L
private const val WRITE_TIMEOUT = 30L

val appModule = module {
    viewModel {
        IntroViewModel()
    }

    viewModel {
        MainViewModel(
            handle = get(),
            placesRepo = get(),
            cocktailsRepo = get(),
        )
    }

    viewModel {
        DisplayCocktailViewModel(
            handle = get(),
            cocktailsRepo = get(),
        )
    }

    single {
        TimeZonesRepo(
            timeZonesDao = get(),
        )
    }

    single {
        PlacesRepo(
            timeZonesRepo = get(),
            placesDao = get(),
        )
    }

    single {
        CocktailsRepo(
            cocktailDBService = get(),
            regionalCocktailsDao = get(),
        )
    }

    single {
        val database = get<ItsFiveOClockSomewhereDb>()
        database.getPlacesDao()
    }

    single {
        val database = get<ItsFiveOClockSomewhereDb>()
        database.getTimeZonesDao()
    }

    single {
        val database = get<ItsFiveOClockSomewhereDb>()
        database.getRegionalCocktails()
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

    factory<CocktailDBService> {
        createService(
            retrofit = retrofitService(
                url = BuildConfig.COCKTAILDB_URL,
                okHttpClient = okHttp(),
                converterFactory = GsonConverterFactory.create(),
            )
        )
    }

}

inline fun <reified T> createService(retrofit: Retrofit) : T = retrofit.create(T::class.java)

private fun retrofitService(
    url: String,
    okHttpClient: OkHttpClient,
    converterFactory: GsonConverterFactory
) = Retrofit.Builder().apply {
    baseUrl(url)
    client(okHttpClient)
    addConverterFactory(converterFactory)
}.build()

private fun okHttp(authInterceptor: Interceptor? = null) = OkHttpClient.Builder().apply {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    addInterceptor(interceptor)
    connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
    readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    connectTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
    retryOnConnectionFailure(true)
    authInterceptor?.let {
        addInterceptor(authInterceptor)
    }
}.build()

