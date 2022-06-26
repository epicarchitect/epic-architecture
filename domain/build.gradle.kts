plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
}

kotlin {
    android()
    jvm()
}

android {
    namespace = "epicarchirect.arch.multiplatform"
    compileSdk = 32

    defaultConfig {
        minSdk = 26
        targetSdk = 32
    }
}