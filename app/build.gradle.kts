plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.NAVIGATION)
    id(Plugins.KAPT)
    id(Plugins.HILT)
    id(Plugins.GOOGLE_SERVICES)
}

android {
    compileSdk = Version.COMPILE_SDK
    namespace = "com.crypto.app"

    signingConfigs {
        create("release") {
            keyAlias = "release"
            keyPassword = "qwerty123"
            storeFile = file("/Users/ohrydko/StudioProjects/CryptoApp/Untitled")
            storePassword = "qwerty123"
        }
    }

    defaultConfig {
        applicationId = "com.crypto.app"

        minSdk = Version.MIN_SDK
        targetSdk = Version.TARGET_SDK

        versionCode = AppVersion.NUMBER
        versionName = AppVersion.NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        signingConfig = signingConfigs.getByName("release")
    }


    buildTypes {
        getByName(BuildTypes.DEBUG) {
            applicationIdSuffix = ".debug"

            buildConfigField(
                "String",
                "API_ENDPOINT",
                "\"https://api.coingecko.com/api/\""
            )
        }
        getByName(BuildTypes.RELEASE) {

            buildConfigField(
                "String",
                "API_ENDPOINT",
                "\"https://api.coingecko.com/api/\""
            )

            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
//
//    applicationVariants.forEach { variant ->
//        variant.outputs.forEach { output ->
//            output as com.android.build.gradle.internal.api.BaseVariantOutputImpl
////            if (output as com.android.build.gradle.internal.api.BaseVariantOutputImpl) {
//            val name =
//                "crypto(${AppVersion.NAME}:${AppVersion.NUMBER})-${variant.buildType.name}.apk"
//            output.outputFileName = name
////            }
//        }
//    }
//
//    applicationVariants.all(object : Action<com.android.build.gradle.api.ApplicationVariant> {
//        override fun execute(variant: com.android.build.gradle.api.ApplicationVariant) {
//            println("variant: ${variant}")
//            variant.outputs.forEach { output ->
//                output as com.android.build.gradle.internal.api.BaseVariantOutputImpl
////            if (output as com.android.build.gradle.internal.api.BaseVariantOutputImpl) {
//                val name =
//                    "crypto(${AppVersion.NAME}-${AppVersion.NUMBER})-${variant.buildType.name}.apk"
//                output.outputFileName = name
////            }
//            }
//        }
//    })

    buildFeatures {
        viewBinding = true
    }

     compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
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

    implementation(Lib.NAVIGATION_UI_KTX)
    implementation(Lib.NAVIGATION_FRAGMENT_KTX)

    implementation(Lib.VIEWBINDING_PROPERTY_DELEGATE_NO_REFLECTION)

    // network
    implementation(Lib.RETROFIT)
    implementation(Lib.RETROFIT_CONVERTER_GSON)
    implementation(Lib.GSON)
    implementation(Lib.OKHTTP3_LOGGING_INTERCEPTOR)

    implementation(Lib.TIMBER)
    implementation(Lib.THREETENABP)

    implementation(Lib.ROOM_RUNTIME)
    implementation(Lib.ROOM_KTX)
    kapt(Lib.ROOM_COMPILER)

    implementation(Lib.FIREBASE)
    implementation(Lib.WORK_MANAGER)
    implementation(Lib.WORK_MANAGER_HILT)
    kapt(Lib.WORK_MANAGER_HILT_COMPILER)

    implementation(project(Modules.DOMAIN_MODELS))

    implementation(project(Modules.NETWORK))

    implementation(project(Modules.USECASES))
    implementation(project(Modules.USECASES_IMPL))

    implementation(project(Modules.REPOSITORIES))
    implementation(project(Modules.REPOSITORIES_IMPL))
    implementation(project(Modules.DATASOURCES))
    implementation(project(Modules.STORAGE))

    implementation(project(Modules.FEATURES))
    implementation(project(Modules.RESOURCES))


}