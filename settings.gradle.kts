dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(":android:app")
include(":desktop:app")
include(":domain")
include(":architecture-core")
include(":flow-driven-architecture")
