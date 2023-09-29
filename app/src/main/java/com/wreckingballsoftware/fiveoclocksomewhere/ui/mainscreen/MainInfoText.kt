package com.wreckingballsoftware.fiveoclocksomewhere.ui.mainscreen

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
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.customTypography
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.dimensions

@Composable
fun MainInfoText(
    toast: String,
    placeName: String,
    cocktailName: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = toast,
            style = MaterialTheme.customTypography.fiveTitle
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimensions.MainSpaceBig))
        Text(
            text = stringResource(
                id = R.string.its_five_oclock,
                placeName,
                cocktailName,
            ),
            style = MaterialTheme.customTypography.fiveSubtitle
        )
    }
}

@Preview(name = "Main Info Text Preview")
@Composable
fun MainInfoTextPreview() {
    MainInfoText(
        toast = "Up yours!",
        placeName = "Rancho Cucamonga, California",
        cocktailName = "a Tequila"
    )
}
