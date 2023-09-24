package com.wreckingballsoftware.fiveoclocksomewhere.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.wreckingballsoftware.fiveoclocksomewhere.R
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.dimensions

@Composable
fun FiveImage(url: String) {
    SubcomposeAsyncImage(
        modifier = Modifier
            .size(MaterialTheme.dimensions.MainImageSize),
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .crossfade(500)
            .build(),
        contentDescription = stringResource(id = R.string.image),
        loading = {
            CircularProgressIndicator()
        },
        error = {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.image_error)
            )
        }
    )
}

@Preview(name = "FiveImage preview")
@Composable
fun FiveImagePreview() {
    FiveImage(url = "")
}