plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

android {
    compileSdk = Version.COMPILE_SDK

    defaultConfig {
        minSdk = Version.MIN_SDK
        targetSdk = Version.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName(BuildTypes.DEBUG) {

        }
        getByName(BuildTypes.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // To avoid the compile error: "Cannot inline bytecode built with JVM target 1.8
    // into bytecode that is being built with JVM target 1.6"
    kotlinOptions {
        val options = this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options.jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Lib.KOTLIN_STDLIB)
    implementation(Lib.KOTLINX_COROUTINES_ANDROID)

    implementation(Lib.HILT_ANDROID)

    implementation(Lib.THREETENABP)

    implementation(project(Modules.DOMAIN_MODELS))
    implementation(project(Modules.DATASOURCES))
    implementation(project(Modules.REPOSITORIES))
}