plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.ashutosh.pagingapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ashutosh.pagingapi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt") ,
                "proguard-rules.pro"
            )
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    //Constraint Layout Compose
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    //Mobile UI Controller Background
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.20.0")

    //Bottom sheet dialog compose
    implementation ("com.holix.android:bottomsheetdialog-compose:1.4.0")

    //Retrofit and LifeCycle Livedata Support for the ViewModels
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.code.gson:gson:2.9.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-livedata:2.3.1")

    //okhttp Network logging interceptor
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //Navigation
    implementation ("androidx.navigation:navigation-compose:2.6.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    //lottie Animations JSON support
    implementation ("com.airbnb.android:lottie-compose:6.0.0")

    //Pager Support in App for Onboarding Screen
    implementation ("com.google.accompanist:accompanist-pager:0.27.0")

    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    //LiveData
    implementation ("androidx.compose.runtime:runtime-livedata:1.2.0-beta01")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // ViewModel utilities for Compose
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    //Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.0")

    //Datastore Preferences
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    // Work Manager Library Android
    implementation ("androidx.work:work-runtime-ktx:2.9.0")

    // Hilt for work Manager
    implementation ("androidx.hilt:hilt-work:1.1.0")

    //coil Library for compose Image
    implementation("io.coil-kt:coil-compose:2.4.0")

    //Github Fancy Toast
    implementation ("io.github.shashank02051997:FancyToast:2.0.2")

    //Google Recaptcha
    implementation ("com.android.volley:volley:1.2.1")
    implementation ("com.google.android.gms:play-services-safetynet:18.0.1")

    //lottie Animations JSON support
    implementation ("com.airbnb.android:lottie-compose:6.0.0")

    //Room Database Library
    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")


}