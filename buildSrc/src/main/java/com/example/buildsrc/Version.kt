package com.example.buildsrc

object Version {

    object Classpaths {
        const val gradle = "7.1.1"
        const val kotlin = "1.6.21"
        const val hilt = "2.42"
        const val safeArgs = "2.4.2"
    }

    object BoM {
        const val okHttp = "4.9.0"
    }

    object Libs {
        const val core = "1.7.0"
        const val appCompat = "1.4.1"
        const val constraint = "2.1.3"
        const val material = "1.6.0"
        const val navigation = "2.4.2"
        const val lifecycle = "2.4.1"
        const val coroutines = "1.6.2"
        const val retrofit = "2.9.0"
        const val hilt = "2.42"
        const val room = "2.4.2"
        const val paging = "3.1.1"
        const val glide = "4.12.0"
        const val veilLayout = "1.1.2"
        const val dataStore = "1.0.0"
        const val detekt = "1.20.0"
        const val javaInject = "1"
    }

    object TestLibs {
        const val junit = "5.8.2"
        const val core = "2.1.0"
        const val coroutines = "1.6.2"
        const val mockito = "4.0.0"
        const val mockitoJupiter = "4.6.0"
        const val room = "2.4.2"
    }

    object AndroidTestLibs {
        const val junit = "1.1.3"
        const val espresso = "3.4.0"
    }
}