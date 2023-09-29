package com.wreckingballsoftware.fiveoclocksomewhere.utils

import kotlin.random.Random

fun IntRange.rand(): Int = Random(System.currentTimeMillis()).nextInt(first, last)

fun String.getArticle(): String {
    //word starts with a non-alphabetic character, just use 'a'
    if (!this[0].isLetter()) {
        return "a"
    }

    //if the first character of the word is a vowel use 'an', otherwise 'a'
    val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    return if (this[0] in vowels) "an" else "a"
}
