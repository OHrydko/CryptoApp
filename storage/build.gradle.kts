plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KAPT)
}

android {
    compileSdk = Version.COMPILE_SDK
    namespace = "com.crypto.storage"

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

    implementation(Lib.ROOM_RUNTIME)
    implementation(Lib.ROOM_KTX)
    implementation(Lib.GSON)
    kapt(Lib.ROOM_COMPILER)

    implementation(Lib.DATASTORE)

    implementation(project(Modules.DATASOURCES))
    implementation(project(Modules.DOMAIN_MODELS))
}