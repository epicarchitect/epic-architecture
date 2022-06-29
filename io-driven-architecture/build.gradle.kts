plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    jvm()
}

dependencies {
    commonMainApi(project(":architecture-core"))
}