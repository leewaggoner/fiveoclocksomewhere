package com.wreckingballsoftware.fiveoclocksomewhere.utils

import kotlin.random.Random

fun IntRange.rand(): Int = Random(System.currentTimeMillis()).nextInt(first, last)