package com.wreckingballsoftware.fiveoclocksomewhere.ui.compose

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.fiveoclocksomewhere.R

@Composable
fun FiveErrorAlert(
    message: String,
    onDismissAlert: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        modifier = modifier,
        title = { Text(text = stringResource(id = R.string.error_title)) },
        text = { Text(text = message) },
        onDismissRequest = { onDismissAlert() },
        confirmButton = {
            Button(
                onClick = { onDismissAlert() }
            ) {
                Text(text = stringResource(id = R.string.ok))
            }
        }
    )
}

@Preview(name = "FiveErrorAlert Preview")
@Composable
fun FiveErrorAlertPreview() {
    FiveErrorAlert(
        message = "There was an error",
        onDismissAlert = { }
    )
}