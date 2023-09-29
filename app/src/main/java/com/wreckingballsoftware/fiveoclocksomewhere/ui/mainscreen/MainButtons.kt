package com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.fiveoclocksomewhere.R
import com.wreckingballsoftware.fiveoclocksomewhere.ui.compose.FiveButton
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.dimensions

@Composable
fun MainButtons(
    getRecipe: () -> Unit,
    somethingElse: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FiveButton(
            modifier = Modifier
                .padding(bottom = MaterialTheme.dimensions.MainSmallSpace),
            text = stringResource(id = R.string.get_recipe),
            onClick = { getRecipe() }
        )
        FiveButton(
            text = stringResource(id = R.string.another_drink),
            onClick = { somethingElse() }
        )
    }
}

@Preview(name = "MainButtons preview")
@Composable
fun MainButtonsPreview() {
    Column {
        MainButtons(getRecipe = { }, somethingElse = { })
    }
}