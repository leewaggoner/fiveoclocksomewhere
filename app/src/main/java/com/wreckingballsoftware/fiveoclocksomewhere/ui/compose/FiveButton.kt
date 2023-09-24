package com.wreckingballsoftware.fiveoclocksomewhere.ui.compose

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.AppBrown
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.White
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.dimensions

@Composable
fun FiveButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier.then(
            Modifier
                .width(MaterialTheme.dimensions.ButtonWidth)
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppBrown
        ),
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            color = White,
        )
    }
}

@Preview(name = "FiveButton preview")
@Composable
fun FiveButtonPreview() {
    FiveButton(text = "Make Cocktail", onClick = { })
}