package com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.fiveoclocksomewhere.R
import com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen.models.MainScreenState
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.customTypography
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.dimensions

@Composable
fun MainInfoText(state: MainScreenState) {
    val place = if (state.placeErrorId == null) {
        state.placeName
    } else {
        stringResource(id = state.placeErrorId)
    }
    val cocktail = if (state.cocktailErrorId == null) {
        state.cocktailName
    } else {
        stringResource(id = state.cocktailErrorId)
    }
    Text(
        text = state.toast,
        style = MaterialTheme.customTypography.fiveTitle
    )
    Spacer(modifier = Modifier.height(MaterialTheme.dimensions.MainSpaceBig))
    Text(
        text = stringResource(
            id = R.string.its_five_oclock,
            place,
            cocktail,
        ),
        style = MaterialTheme.customTypography.fiveSubtitle
    )
}

@Preview(name = "Main Info Text Preview")
@Composable
fun MainInfoTextPreview() {
    MainInfoText(
        state = MainScreenState(
            placeErrorId = R.string.country_error,
            cocktailErrorId = R.string.water
        )
    )
}
