package com.example.nativelib

object NativeLib {

    init {
        System.loadLibrary("native-lib")
    }

    external fun publicKey(): String
    external fun privateKey(): String
    external fun hashAlgorithm(): String
}