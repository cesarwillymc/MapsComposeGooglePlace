buildscript {
    repositories{
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
    }
}

allprojects {
    apply(plugin = "org.jmailen.kotlinter")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "com.github.ben-manes.versions")
    apply(plugin = "jacoco")
    detekt {
        config = files("${rootProject.projectDir}/config/detekt-config.yml")
        parallel = true
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version ("7.1.2") apply false
    id ("com.android.library") version ("7.1.2") apply false
    id ("org.jetbrains.kotlin.android" )version ("1.6.10") apply false
    id("org.jmailen.kotlinter") version "3.6.0"
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
    id("com.github.ben-manes.versions") version "0.39.0"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}