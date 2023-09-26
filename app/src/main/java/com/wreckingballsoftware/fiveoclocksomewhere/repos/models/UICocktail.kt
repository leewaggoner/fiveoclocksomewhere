package com.wreckingballsoftware.fiveoclocksomewhere.repos.models

class UICocktail(
    val error: String? = null,
    val id: Long? = null,
    val name: String? = null,
    val displayName: String? = null,
    val glass: String? = null,
    val instructions: String? = null,
    val imageUrl: String? = null,
    val ingredients: List<String>? = null,
    val measures: List<String>? = null,
    val attribution: String? = null,
)