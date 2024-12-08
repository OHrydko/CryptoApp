# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
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

# Keep all fragments in the package (optional, broader scope)
-keep class com.crypto.features.** { *; }
-dontwarn com.crypto.base.AppThemeKt
-dontwarn com.crypto.base.ui.ComposesKt
-dontwarn com.crypto.domain_models.Coin
-dontwarn com.crypto.domain_models.DataResult$Failure
-dontwarn com.crypto.domain_models.DataResult$Success
-dontwarn com.crypto.domain_models.DataResult
-dontwarn com.crypto.resources.AppPaddings
-dontwarn com.crypto.resources.ResProvider
-dontwarn com.crypto.resources.SharedFontSize
-dontwarn com.crypto.usecases.GetCoinsFromDBUseCase
-dontwarn com.crypto.usecases.GetListCoinsUseCase
-dontwarn java.lang.invoke.StringConcatFactory
-dontwarn com.crypto.domain_models.CoinDetails$Companion
-dontwarn com.crypto.domain_models.CoinDetails
-dontwarn com.crypto.domain_models.CoinImage
-dontwarn com.crypto.domain_models.Description
-dontwarn com.crypto.domain_models.MarketCap
-dontwarn com.crypto.domain_models.MarketData
-dontwarn com.crypto.resources.CoinDetailsPaddings
-dontwarn com.crypto.usecases.GetCoinDetailsUseCase