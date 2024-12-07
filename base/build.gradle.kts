plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_PARCELIZE)
}

android {
    compileSdk = Version.COMPILE_SDK
    namespace = "com.crypto.base"

    defaultConfig {
        minSdk = Version.MIN_SDK
        targetSdk = Version.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE_COMPILER
    }

    buildTypes {
        getByName(BuildTypes.DEBUG) {

        }
        getByName(BuildTypes.RELEASE) {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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
    implementation(Lib.ANDROIDX_ANNOTATION)
    implementation(Lib.CORE_KTX)
    implementation(Lib.APPCOMPAT)
    implementation(Lib.MATERIAL)
    implementation(Lib.FRAGMENT_KTX)
    implementation(Lib.KOTLINX_COROUTINES_ANDROID)
    implementation(Lib.BIOMETRIC)
    implementation(Lib.TIMBER)
    implementation(Lib.VIEWBINDING_PROPERTY_DELEGATE_NO_REFLECTION)
    implementation(Lib.NAVIGATION_FRAGMENT_KTX)
    implementation(Lib.NAVIGATION_UI_KTX)
    implementation(Lib.THREETENABP)

    implementation(Lib.COMPOSE_ACTIVITY)
    implementation(Lib.COMPOSE_MATERIAL)
    implementation(Lib.COMPOSE_ANIMATION)
    implementation(Lib.COMPOSE_UI_TOOLING)
    implementation(Lib.COMPOSE_LIFECYCLE_VIEWMODEL)
    implementation(Lib.CONSTRAINTLAYOUT_COMPOSE)

    implementation(project(Modules.RESOURCES))
    implementation(project(Modules.DOMAIN_MODELS))
}