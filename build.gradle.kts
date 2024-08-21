// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies{
        classpath(libs.androidx.androidx.navigation.safeargs.gradle.plugin)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.google.services)
    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}