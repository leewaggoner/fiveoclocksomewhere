package com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wreckingballsoftware.fiveoclocksomewhere.R
import com.wreckingballsoftware.fiveoclocksomewhere.ui.Actions
import com.wreckingballsoftware.fiveoclocksomewhere.ui.compose.FiveImage
import com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen.models.MainScreenState
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.dimensions
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(
    actions: Actions,
    viewModel: MainViewModel = getViewModel()
) {
    val toasts = stringArrayResource(id = R.array.toasts)
    LaunchedEffect(key1 = Unit) {
        viewModel.chooseNewToast(toasts)
    }

    MainScreenContent(
        state = viewModel.state,
        getRecipe = viewModel::getRecipe,
        somethingElse = viewModel::somethingElse,
    )
}

@Composable
fun MainScreenContent(
    state: MainScreenState,
    getRecipe: () -> Unit,
    somethingElse: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MainInfoText(state = state)
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.MainSpaceBig))
        FiveImage(url = state.imageUrl)
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.MainSpaceBig))
        MainButtons(
            getRecipe = getRecipe,
            somethingElse = somethingElse
        )
    }
}

@Preview(name = "Default Font Size", fontScale = 1f)
@Preview(name = "Large Font Size", fontScale = 2f)
annotation class FontScalePreviews

@FontScalePreviews
@Composable
fun MainScreenContentPreview() {
    MainScreenContent(
        state = MainScreenState(
            toast = "If you have an appetite for life, stay hungry!",
            placeName = "in South Georgia and the South Sandwich Islands",
            cocktailName = "an Empelló Cocina's Fat-Washed Mezcal"
        ),
        getRecipe = { },
        somethingElse = { },
    )
}