plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.newsapp.multiplatform.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.newsapp.multiplatform.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    val koin_android_version= "3.3.2"
    val koin_android_compose_version= "3.4.1"
    val accompanistVersion = "0.27.1"
    val navVersion = "2.5.3"
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.3.3")
    implementation("androidx.compose.ui:ui-tooling:1.3.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")

    // Navigation
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // Koin
    implementation("io.insert-koin:koin-android:$koin_android_version")
    implementation("io.insert-koin:koin-androidx-compose:$koin_android_compose_version")

    // Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-navigation-animation:$accompanistVersion")

    // Coil
    implementation("io.coil-kt:coil-compose:2.2.2")
}