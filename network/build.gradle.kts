plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KAPT)
}

android {
    compileSdk = Version.COMPILE_SDK
    namespace = "com.crypto.network"

    defaultConfig {
        minSdk = Version.MIN_SDK
        targetSdk = Version.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName(BuildTypes.DEBUG) {
            buildConfigField("String", "AUTH_USERNAME", "\"serviceuser\"")
            buildConfigField("String", "AUTH_PASSWORD", "\"Diaspora1!\"")
        }
        getByName(BuildTypes.RELEASE) {
            buildConfigField("String", "AUTH_USERNAME", "\"serviceuser\"")
            buildConfigField("String", "AUTH_PASSWORD", "\"Diaspora1!\"")

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

    implementation(Lib.TIMBER)
    implementation(Lib.THREETENABP)

    implementation(Lib.HILT_ANDROID)

    implementation(Lib.KOTLINX_COROUTINES_ANDROID)

    implementation(Lib.RETROFIT)
    implementation(Lib.RETROFIT_CONVERTER_GSON)
    implementation(Lib.GSON)
    implementation(Lib.OKHTTP3_LOGGING_INTERCEPTOR)

    implementation(project(Modules.DOMAIN_MODELS))
    implementation(project(Modules.DATASOURCES))
}