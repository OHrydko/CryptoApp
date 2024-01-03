// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Version.HILT}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Version.NAVIGATION}")
        classpath("com.google.gms:google-services:4.3.15")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}