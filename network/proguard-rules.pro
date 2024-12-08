# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn java.lang.invoke.StringConcatFactory
-keep class com.crypto.network.** { *; }
-dontwarn com.crypto.domain_models.Coin
-dontwarn com.crypto.domain_models.CoinDetails
-dontwarn com.crypto.domain_models.CoinImage
-dontwarn com.crypto.domain_models.DataResult$Failure
-dontwarn com.crypto.domain_models.DataResult$Success
-dontwarn com.crypto.domain_models.DataResult
-dontwarn com.crypto.domain_models.Description
-dontwarn com.crypto.domain_models.MarketCap
-dontwarn com.crypto.domain_models.MarketData
-dontwarn java.lang.invoke.StringConcatFactory
-keep class * implements retrofit2.Call { *; }

# Keep Retrofit annotations (e.g., @GET, @POST)
-keepattributes RuntimeVisibleAnnotations

# Keep Retrofit API methods
-keepclassmembers class * {
    @retrofit2.http.* <methods>;
}

# Keep the Retrofit Response class
-keep class retrofit2.Response { *; }

# Keep OkHttp classes (if used with Retrofit)
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
# Keep Gson's field names for JSON serialization/deserialization
-keep class com.google.gson.** { *; }
-keepattributes Signature
-keepattributes *Annotation*

 # With R8 full mode generic signatures are stripped for classes that are not
 # kept. Suspend functions are wrapped in continuations where the type argument
 # is used.
 -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation { *; }