plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.6.21"
    id("com.apollographql.apollo3") version "3.3.1"
    id("com.google.devtools.ksp") version "1.6.21-1.0.6"
}

android {
    namespace = "tv.olaris.android"
    compileSdk = 32

    defaultConfig {
        applicationId = "tv.olaris.android"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-rc01"
    }
    packagingOptions {
        resources {
//            excludes += '/META-INF/{AL2.0,LGPL2.1}'
        }
    }
    apollo {
        // instruct the compiler to generate Kotlin models
        packageName.set("tv.olaris.android")
    }
}

dependencies {
    val accompanistVersion = "0.24.11-rc"

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.activity:activity-compose:1.4.0")

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    // Okhttp
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // Jetpack Navigation
    implementation("androidx.navigation:navigation-compose:2.4.2")

    // Android Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0-rc02")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0-rc02")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.5.0-rc02")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0-rc02")

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.2.0-rc01")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0-rc01")

    // Google Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-placeholder-material:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-permissions:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-navigation-material:$accompanistVersion")

    // Compose Material3
    implementation("androidx.compose.material3:material3:1.0.0-alpha13")
    implementation("androidx.compose.material3:material3-window-size-class:1.0.0-alpha13")

    // Apollo
    implementation("com.apollographql.apollo3:apollo-runtime:3.3.1")

    // JWT
    implementation("com.auth0.android:jwtdecode:2.0.1")

    // Room components
    implementation("androidx.room:room-ktx:2.5.0-alpha02")
    implementation("androidx.room:room-paging:2.5.0-alpha02")
    ksp("androidx.room:room-compiler:2.5.0-alpha02")

    // Coil
    implementation("io.coil-kt:coil:2.1.0")
    implementation("io.coil-kt:coil-compose:2.1.0")

    // Paging components
    implementation("androidx.paging:paging-runtime:3.2.0-alpha01")
    implementation("androidx.paging:paging-compose:1.0.0-alpha15")

    // Koin main features for Android
    implementation("io.insert-koin:koin-android:3.2.0")
    implementation("io.insert-koin:koin-androidx-workmanager:3.2.0")
    implementation("io.insert-koin:koin-androidx-navigation:3.2.0")
    implementation("io.insert-koin:koin-androidx-compose:3.2.0")

    //
    // KMP Libraries
    //
    // Napier
    implementation("io.github.aakira:napier:2.6.1")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0-rc01")

    debugImplementation("androidx.compose.ui:ui-tooling:1.2.0-rc01")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0-rc01")
}
