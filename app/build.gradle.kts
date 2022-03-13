plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

}

apply(from = "${rootProject.projectDir}/jacoco.gradle")

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
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.1.0"
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
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
// -- Constraint
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0")

    implementation("androidx.compose.ui:ui:1.1.1")
    implementation("androidx.compose.material:material:1.1.1")
    implementation("androidx.compose.material:material-icons-extended:1.1.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.1.1")

    // Coil
    implementation("io.coil-kt:coil-compose:1.4.0")
    implementation("io.coil-kt:coil-svg:1.4.0")

    //Permission
    implementation("com.google.accompanist:accompanist-permissions:0.21.3-beta")

    // -- Navigation
    implementation("androidx.navigation:navigation-compose:2.5.0-alpha03")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Lotie
    implementation("com.airbnb.android:lottie-compose:4.2.0")

    // -- Accompanist
    implementation("com.google.accompanist:accompanist-insets:0.20.2")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.20.2")
    implementation("com.google.accompanist:accompanist-flowlayout:0.20.2")

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
    implementation("com.google.maps.android:maps-compose:1.0.0")


    // KTX for the Maps SDK for Android Utility Library
    implementation("com.google.maps.android:maps-utils-ktx:3.2.0")

    //Coroutine
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.2.0-alpha05")

    //Room
    val roomVersion = "2.4.2"

    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    // -- Test
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    testImplementation("io.mockk:mockk:1.12.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.1.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("io.mockk:mockk-android:1.12.0")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
}

fun isLinuxOrMacOs(): Boolean {
    val osName = System.getProperty("os.name").toLowerCase()
    return osName.contains("linux") || osName.contains("mac os") || osName.contains("macos")
}

project.afterEvaluate {
    this.tasks.getByName("preBuild").dependsOn("formatKotlin", "lintKotlin")
    this.tasks.getByName("preBuild").mustRunAfter("formatKotlin")
    this.tasks.getByName("clean").dependsOn("installGitHooks")
    this.tasks.getByName("assemble").dependsOn("installGitHooks")
    tasks.create("executeValidations") {
        group = "verification"
        dependsOn("detekt")
        dependsOn("testDebugUnitTestCoverageVerification")
        dependsOn("lintKotlin")
    }
    tasks.create("copyGitHooks", Copy::class.java) {
        description = "Copies the git hooks from team-props/git-hooks to the .git folder."
        from("${rootDir}/team-props/git-hooks") {
            include("**/*.sh")
            rename("(.*).sh", "$1")
        }
        into("${rootDir}/.git/hooks")
    }
    tasks.create("installGitHooks", Exec::class.java) {
        description = "Installs the pre-commit git hooks from team-props/git-hooks."
        group = "git hooks"
        workingDir = rootDir
        commandLine = listOf("chmod")
        args("-R", "+x", ".git/hooks/")
        dependsOn("copyGitHooks")
        onlyIf { isLinuxOrMacOs() }
        doLast {
            logger.info("---- Git hook installed successfully.")
        }
    }
}