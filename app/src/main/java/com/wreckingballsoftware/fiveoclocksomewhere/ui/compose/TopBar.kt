package com.wreckingballsoftware.fiveoclocksomewhere.ui.compose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.White
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.customColorsPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    ) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.customColorsPalette.primary,
            titleContentColor = White,
        ),
    )
}
