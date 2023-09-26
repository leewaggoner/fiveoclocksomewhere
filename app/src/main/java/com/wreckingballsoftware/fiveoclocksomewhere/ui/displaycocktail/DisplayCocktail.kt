package com.wreckingballsoftware.fiveoclocksomewhere.ui.displaycocktail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wreckingballsoftware.fiveoclocksomewhere.ui.compose.FiveErrorAlert
import com.wreckingballsoftware.fiveoclocksomewhere.ui.displaycocktail.models.DisplayCocktailState
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.dimensions
import org.koin.androidx.compose.getViewModel

@Composable
fun DisplayCocktail(
    cocktailId: Long,
    viewModel: DisplayCocktailViewModel = getViewModel()
    ) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getCocktailRecipe(cocktailId)
    }

    DisplayCocktailContent(
        state = viewModel.state,
        onDismissAlert = viewModel::onDismissAlert
    )
}

@Composable
fun DisplayCocktailContent(
    state: DisplayCocktailState,
    onDismissAlert: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TitleContent(state = state)

        Spacer(
            modifier = Modifier
                .padding(top = MaterialTheme.dimensions.DisplayBigSpace)
        )

        CocktailInstructions(state = state)

        if(state.isLoading) {
            CircularProgressIndicator()
        }

        if (state.cocktailErrorId != null || state.cocktailError != null) {
            FiveErrorAlert(
                message = if (state.cocktailErrorId == null) {
                    state.cocktailError ?: ""
                } else {
                    stringResource(id = state.cocktailErrorId)
                },
                onDismissAlert = onDismissAlert
            )
        }
    }
}

@Preview("DisplayCocktailContent Preview")
@Composable
fun DisplayCocktailContentPreview() {
    DisplayCocktailContent(
        state = DisplayCocktailState(
            cocktailName = "a Bloody Mary",
            cocktailIngredients = listOf("Vodka", "Olive"),
            cocktailMeasures = listOf("1/2 cup", "1"),
            cocktailInstructions = "Squeeze the juice from 1½ limes and blend " +
                    "with the mango to give a smooth purée. Cut the rest of " +
                    "the limes into quarters, and then cut each wedge in half " +
                    "again. Put 2 pieces of lime in a highball glass for each " +
                    "person and add 1 teaspoon of caster sugar and 5-6 mint " +
                    "leaves to each glass. Squish everything together with a " +
                    "muddler or the end of a rolling pin to release all the " +
                    "flavours from the lime and mint. Divide the mango purée " +
                    "between the glasses and add 30ml white rum and a handful " +
                    "of crushed ice to each one, stirring well to mix " +
                    "everything together. Top up with soda water to serve and " +
                    "garnish with extra mint, if you like."
        ),
        onDismissAlert = { }
    )
}
