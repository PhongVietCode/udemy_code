// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val nav_version = "2.7.1"

    repositories {
        google()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")

    }

}


@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    id("com.google.gms.google-services") version "4.3.15" apply false

}
true // Needed to make the Suppress annotation work for the plugins block