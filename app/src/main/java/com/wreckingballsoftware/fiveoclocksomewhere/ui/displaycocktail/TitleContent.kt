package com.wreckingballsoftware.fiveoclocksomewhere.ui.displaycocktail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.fiveoclocksomewhere.R
import com.wreckingballsoftware.fiveoclocksomewhere.ui.compose.FiveImage
import com.wreckingballsoftware.fiveoclocksomewhere.ui.displaycocktail.models.DisplayCocktailState
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.customTypography
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.dimensions

@Composable
fun TitleContent(
    state: DisplayCocktailState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(
                id = R.string.how_to,
                state.cocktailName,
            ),
            style = MaterialTheme.customTypography.fiveTitle
        )

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.dimensions.DisplaySmallSpace)
        )

        FiveImage(url = state.imageUrl)
    }
}

@Preview("TitleContent Preview")
@Composable
fun TitleContentPreview() {
    TitleContent(
        state = DisplayCocktailState(
            cocktailName = "a Rob Roy",
            imageUrl = "",
        )
    )
}