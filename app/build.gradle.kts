plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_PARCELIZE)
    id(Plugins.KAPT)
    id(Plugins.HILT)
    id(Plugins.NAVIGATION)
}

android {
    namespace = "com.crypto.cryptoapp"

    compileSdk = Version.COMPILE_SDK

    defaultConfig {
        minSdk = Version.MIN_SDK
        targetSdk = Version.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        named(BuildTypes.DEBUG) {}

        named(BuildTypes.RELEASE) {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE_COMPILER
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

    kapt {
        javacOptions {
            // These options are normally set automatically via the Hilt Gradle plugin, but we
            // set them manually to workaround a bug in the Kotlin 1.5.20
            option("-Adagger.fastInit=ENABLED")
            option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
        }
    }

}

dependencies {
    implementation(Lib.KOTLIN_STDLIB)
    implementation(Lib.APPCOMPAT)
    implementation(Lib.MATERIAL)
    implementation(Lib.CONSTRAINTLAYOUT)

    //ktx
    implementation(Lib.CORE_KTX)
    implementation(Lib.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Lib.LIFECYCLE_RUNTIME_KTX)
    implementation(Lib.ACTIVITY_KTX)
    implementation(Lib.FRAGMENT_KTX)
    //DI
    implementation(Lib.HILT_ANDROID)
    kapt(Lib.HILT_ANDROID_COMPILER)

    implementation(Lib.COMPOSE_ACTIVITY)
    implementation(Lib.COMPOSE_MATERIAL)
    implementation(Lib.COMPOSE_ANIMATION)
    implementation(Lib.COMPOSE_UI_TOOLING)
    implementation(Lib.COMPOSE_LIFECYCLE_VIEWMODEL)
    implementation(Lib.CONSTRAINTLAYOUT_COMPOSE)
    implementation(Lib.COMPOSE_RUNTIME)

    implementation(Lib.NAVIGATION_FRAGMENT_KTX)
    implementation(Lib.NAVIGATION_UI_KTX)

    implementation(Lib.TIMBER)
    implementation(Lib.THREETENABP)

    implementation(Lib.VIEWBINDING_PROPERTY_DELEGATE_NO_REFLECTION)

    implementation(Lib.COIL)

    implementation(project(Modules.DOMAIN_MODELS))
    implementation(project(Modules.USECASES))
    implementation(project(Modules.RESOURCES))
    implementation(project(Modules.BASE))

    testImplementation(Lib.TRUTH)
    // Required -- JUnit 4 framework
    testImplementation(Lib.JUNIT)
    // Optional -- Robolectric environment
    testImplementation(Lib.TEST_CORE)
    // Optional -- Mockito framework
    testImplementation(Lib.MOCKITO_CORE)
    testImplementation(Lib.MOCKITO_INLINE)

}