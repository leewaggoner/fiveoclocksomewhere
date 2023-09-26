package com.wreckingballsoftware.fiveoclocksomewhere.ui.displaycocktail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wreckingballsoftware.fiveoclocksomewhere.R
import com.wreckingballsoftware.fiveoclocksomewhere.ui.displaycocktail.models.DisplayCocktailState
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.customTypography
import com.wreckingballsoftware.fiveoclocksomewhere.ui.theme.dimensions

@Composable
fun CocktailInstructions(
    state: DisplayCocktailState
) {
    Text(
        text = stringResource(id = R.string.ingredients),
        style = MaterialTheme.customTypography.fiveSubtitle,
    )

    state.cocktailIngredients.forEachIndexed { index, ingredient ->
        val measure = if (index < state.cocktailMeasures.size) {
            state.cocktailMeasures[index]
        } else {
            ""
        }
        Text(
            text = "$measure $ingredient",
            style = MaterialTheme.customTypography.fiveInstructions
        )
    }

    Spacer(
        modifier = Modifier
            .padding(top = MaterialTheme.dimensions.DisplaySmallSpace)
    )

    Text(
        text = stringResource(id = R.string.instructions),
        style = MaterialTheme.customTypography.fiveSubtitle,
    )

    Text(
        text = state.cocktailInstructions,
        style = MaterialTheme.customTypography.fiveInstructions
    )
}

@Preview("CocktailInstructionsPreview")
@Composable
fun CocktailInstructionsPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CocktailInstructions(
            state = DisplayCocktailState(
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
        )    }
}