package com.wreckingballsoftware.fiveoclocksomewhere.ui.intro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wreckingballsoftware.fiveoclocksomewhere.R
import com.wreckingballsoftware.fiveoclocksomewhere.ui.Actions
import com.wreckingballsoftware.fiveoclocksomewhere.ui.compose.FiveButton
import com.wreckingballsoftware.fiveoclocksomewhere.ui.intro.models.IntroNavigation
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.customTypography
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.dimensions
import org.koin.androidx.compose.getViewModel

@Composable
fun IntroScreen(
    actions: Actions,
    viewModel: IntroViewModel = getViewModel(),
    ) {
    val navigation = viewModel.navigation.collectAsStateWithLifecycle(null)
    navigation.value?.let { nav ->
        when (nav) {
            IntroNavigation.MainActivity -> {
                actions.navigateToMainScreen()
            }
        }
    }

    IntroScreenContent(
        onClick = viewModel::onButtonClick
    )
}

@Composable
fun IntroScreenContent(
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = MaterialTheme.dimensions.IntroSpace),
            text = stringResource(id = R.string.no_excuse),
            style = MaterialTheme.customTypography.introSetup,
        )
        Text(
            text = stringResource(id = R.string.five_oclock),
            style = MaterialTheme.customTypography.introTitle,
        )
        Spacer(modifier = Modifier.height(height = MaterialTheme.dimensions.IntroSpaceBig))
        Text(
            modifier = Modifier
                .padding(bottom = MaterialTheme.dimensions.IntroSpace),
            text = stringResource(id = R.string.find_out),
            style = MaterialTheme.customTypography.introCta,
        )
        FiveButton(
            text = stringResource(id = R.string.go),
            onClick = onClick,
        )
    }
}

@Preview
@Composable
fun IntroScreenContentPreview() {
    IntroScreenContent(
        onClick = { }
    )
}
