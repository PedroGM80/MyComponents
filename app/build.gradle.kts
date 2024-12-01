plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "1.9.0" // Use the appropriate version

}

android {
    namespace = "dev.pgm.mycomponents"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.pgm.mycomponents"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    buildToolsVersion = "35.0.0"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.androidx.material3.android)
    debugImplementation(libs.compose.ui.tooling)
    //Google fonts
    implementation(libs.androidx.ui.text.google.fonts)
    //Icons
    implementation(libs.androidx.material)
    implementation(libs.androidx.material.icons.extended)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)

    implementation(libs.coil.compose)
    implementation(libs.kotlinx.coroutines.android)
    //Serializacion
    implementation(libs.kotlinx.serialization.json)// Or your preferred format

    implementation (libs.retrofit.v290)
    implementation (libs.okhttp)
}