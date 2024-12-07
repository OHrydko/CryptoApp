plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

android {
    compileSdk = Version.COMPILE_SDK
    namespace = "com.crypto.resources"

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
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE_COMPILER
    }

     compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(Lib.KOTLIN_STDLIB)
    implementation(Lib.MATERIAL)

//    implementation(Lib.COMPOSE_ACTIVITY)
    implementation(Lib.COMPOSE_MATERIAL)
//    implementation(Lib.COMPOSE_ANIMATION)
//    implementation(Lib.COMPOSE_UI_TOOLING)
//    implementation(Lib.COMPOSE_LIFECYCLE_VIEWMODEL)

    implementation(Lib.HILT_ANDROID)

    implementation(Lib.TIMBER)
}