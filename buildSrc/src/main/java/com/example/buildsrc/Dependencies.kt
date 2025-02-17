package com.example.buildsrc

object Dependencies {

    object Classpaths {
        private const val gradle = "com.android.tools.build:gradle:${Version.Classpaths.gradle}"
        private const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.Classpaths.kotlin}"
        private const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Version.Classpaths.hilt}"
        private const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.Classpaths.safeArgs}"

        val list = listOf(
            gradle,
            kotlin,
            hilt,
            safeArgs
        )
    }

    object BoM {
        const val okHttp = "com.squareup.okhttp3:okhttp-bom:${Version.BoM.okHttp}"
    }

    object Libs {
        const val core = "androidx.core:core-ktx:${Version.Libs.core}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.Libs.appCompat}"
        const val constraint = "androidx.constraintlayout:constraintlayout:${Version.Libs.constraint}"
        const val material = "com.google.android.material:material:${Version.Libs.material}"
        const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Version.Libs.navigation}"
        const val navUi = "androidx.navigation:navigation-ui-ktx:${Version.Libs.navigation}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.Libs.lifecycle}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.Libs.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.Libs.lifecycle}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.Libs.coroutines}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Libs.coroutines}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.Libs.retrofit}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Version.Libs.retrofit}"
        const val okHttp = "com.squareup.okhttp3:okhttp"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor"
        const val hilt = "com.google.dagger:hilt-android:${Version.Libs.hilt}"
        const val room = "androidx.room:room-ktx:${Version.Libs.room}"
        const val roomRuntime = "androidx.room:room-runtime:${Version.Libs.room}"
        const val paging = "androidx.paging:paging-runtime-ktx:${Version.Libs.paging}"
        const val pagingCommon = "androidx.paging:paging-common:${Version.Libs.paging}"
        const val glide = "com.github.bumptech.glide:glide:${Version.Libs.glide}"
        const val veilLayout = "com.github.skydoves:androidveil:${Version.Libs.veilLayout}"
        const val dataStore = "androidx.datastore:datastore-preferences:${Version.Libs.dataStore}"
        const val javaInject = "javax.inject:javax.inject:${Version.Libs.javaInject}"
    }

    object Kapt {
        const val hilt = "com.google.dagger:hilt-android-compiler:${Version.Libs.hilt}"
        const val room = "androidx.room:room-compiler:${Version.Libs.room}"
        const val glide = "com.github.bumptech.glide:compiler:${Version.Libs.glide}"
    }

    object TestLibs {
        const val junit = "org.junit.jupiter:junit-jupiter:${Version.TestLibs.junit}"
        const val core = "androidx.arch.core:core-testing:${Version.TestLibs.core}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.TestLibs.coroutines}"
        const val mockito = "org.mockito.kotlin:mockito-kotlin:${Version.TestLibs.mockito}"
        const val mockitoJupiter = "org.mockito:mockito-junit-jupiter:${Version.TestLibs.mockitoJupiter}"
        const val room = "androidx.room:room-testing:${Version.TestLibs.room}"
    }

    object AndroidTestLibs {
        const val junit = "androidx.test.ext:junit:${Version.AndroidTestLibs.junit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.AndroidTestLibs.espresso}"
    }

    object AppModule {
        val listLibs = listOf(
            Libs.core,
            Libs.appCompat,
            Libs.constraint,
            Libs.material,
            Libs.navFragment,
            Libs.navUi,
            Libs.viewModel,
            Libs.liveData,
            Libs.runtime,
            Libs.coroutinesAndroid,
            Libs.hilt,
            Libs.paging,
            Libs.glide,
            Libs.veilLayout
        )

        val listKapt = listOf(
            Kapt.hilt,
            Kapt.room,
            Kapt.glide
        )

        val listAndroidTest = listOf(
            AndroidTestLibs.junit,
            AndroidTestLibs.espresso
        )
    }

    object DomainModule {
        val listLibs = listOf(
            Libs.coroutinesCore,
            Libs.pagingCommon,
            Libs.javaInject
        )
    }

    object DataModule {
        val listLibs = listOf(
            Libs.retrofit,
            Libs.retrofitGson,
            BoM.okHttp,
            Libs.okHttp,
            Libs.loggingInterceptor,
            Libs.hilt,
            Libs.room,
            Libs.roomRuntime,
            Libs.paging,
            Libs.dataStore
        )

        val listKapt = listOf(
            Kapt.hilt,
            Kapt.room
        )

        val listTest = listOf(
            TestLibs.room
        )
    }

    object BaseTestModule {
        val listTest = listOf(
            TestLibs.junit,
            TestLibs.core,
            TestLibs.coroutines,
            TestLibs.mockito,
            TestLibs.mockitoJupiter
        )
    }
}