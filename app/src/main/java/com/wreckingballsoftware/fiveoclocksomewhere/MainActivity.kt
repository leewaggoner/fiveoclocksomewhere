package com.wreckingballsoftware.fiveoclocksomewhere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.wreckingballsoftware.fiveoclocksomewhere.ui.compose.TopBar
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.FiveOClockSomewhereTheme
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.customColorsPalette

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            FiveOClockSomewhereTheme(
//                darkTheme = true,
            ) {
                Scaffold(
                    topBar = {
                        TopBar(
                            title = stringResource(id = R.string.app_name)
                        )
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                            .background(MaterialTheme.customColorsPalette.background),
                    ) {
                        FiveHost()
                    }
                }
            }
        }
    }
}
