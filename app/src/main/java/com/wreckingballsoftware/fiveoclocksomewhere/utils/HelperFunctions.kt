package com.wreckingballsoftware.fiveoclocksomewhere.utils

fun determineArticle(cocktail: String): String {
    //check if the cocktail starts with a non-alphabetic character
    if (!cocktail[0].isLetter()) {
        return "a"
    }

    //check if the first character of the word is a vowel
    val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    return if (cocktail[0] in vowels) "an" else "a"
}
