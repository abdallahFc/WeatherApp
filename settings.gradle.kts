pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "WeatherApp"
include(":app")
include(":data")
include(":core")
include(":features")
include(":features:city_input")
include(":domain")
include(":features:current_weather")
include(":features:forecast_weather")
