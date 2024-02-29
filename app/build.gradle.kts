plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT_LIBRARY)
}

android {
    namespace = "com.example.weatherapp"
    compileSdk = ConfigData.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.example.weatherapp"
        minSdk = ConfigData.MIN_SDK_VERSION
        targetSdk = ConfigData.TARGET_SDK_VERSION
        versionCode = ConfigData.VERSION_CODE
        versionName = ConfigData.VERSION_NAME

        testInstrumentationRunner = ConfigData.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }


    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = ConfigData.JAVA_VERSIONS_CODE
        targetCompatibility = ConfigData.JAVA_VERSIONS_CODE
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    }
    kotlinOptions {
        jvmTarget = ConfigData.JAVA_VERSIONS_CODE.toString()
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.DOMAIN))
    implementation(project(BuildModules.DATA))
    implementation(project(BuildModules.City_INPUT))
    implementation(project(BuildModules.CURRENT_WEATHER))
    implementation(project(BuildModules.DAY_FORECAST))
    Dependencies.uiDependencies.forEach { implementation(it) }
    Dependencies.composeDependency.forEach { implementation(it) }
    Dependencies.junitDependency.forEach { testImplementation(it) }
    Dependencies.androidTestDependencies.forEach { androidTestImplementation(it) }
    implementation(platform(Dependencies.composePlatformBom))
    androidTestImplementation(platform(Dependencies.composePlatformBomAndroidTest))
    //Navigation
    Dependencies.navigationDependencies.forEach { implementation(it) }
    //Hilt
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.hiltDependency)
    //retrofit
    Dependencies.retrofitDependencies.forEach { implementation(it) }
    //Room
    Dependencies.roomDependencies.forEach { implementation(it) }
    kapt(Dependencies.roomCompiler)


}