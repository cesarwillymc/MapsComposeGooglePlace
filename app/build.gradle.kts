plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.cesarwillymc.technicaltest99minutes"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }

        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

dependencies {
    // -- ANDROID
    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.4.1")
    implementation ("com.google.android.material:material:1.5.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.3")

    // -- DI
    val hiltVersion = "2.38.1"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // -- Network
    val squareUpVersion = "2.9.0"
    val gsonVersion = "2.8.7"
    implementation("com.squareup.retrofit2:retrofit:$squareUpVersion")
    implementation("com.squareup.retrofit2:converter-gson:$squareUpVersion")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    implementation("com.google.code.gson:gson:$gsonVersion")

    // KTX for the Maps SDK for Android library
    implementation("com.google.maps.android:maps-ktx:3.2.0")
    implementation("com.google.android.gms:play-services-maps:18.0.2")

    // KTX for the Maps SDK for Android Utility Library
    implementation("com.google.maps.android:maps-utils-ktx:3.2.0")

    // -- Test
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("io.mockk:mockk-android:1.12.0")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
}