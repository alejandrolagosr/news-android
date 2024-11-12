plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.flagos.news"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.flagos.news"
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
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    hilt {
        enableAggregatingTask = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Modules
    implementation(project(":domain"))
    implementation(project(":data"))

    // Android Core and Kotlin
    implementation(libs.core.ktx)

    // UI
    implementation(libs.bundles.coil.impl)
    implementation(libs.androidx.splashscreen)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose.impl)

    // DI
    implementation(libs.bundles.hilt.impl)
    kapt(libs.hilt.compiler)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.hilt.android.testing)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.bundles.android.core.testing.impl)
    androidTestImplementation(libs.bundles.android.compose.testing.impl)

    kaptTest(libs.hilt.compiler)
    kaptAndroidTest(libs.hilt.compiler)
    debugImplementation(libs.bundles.compose.debug.impl)
}

kapt {
    correctErrorTypes = true
}