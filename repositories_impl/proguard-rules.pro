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
#-renamesourcefileattribute SourceFile
-keep class com.crypto.repositoryimpl.** { *; }
-dontwarn com.crypto.data_source.LocalCoinDataSource
-dontwarn com.crypto.data_source.RemoteCoinDataSource
-dontwarn com.crypto.domain_models.DataResult$Failure
-dontwarn com.crypto.domain_models.DataResult$Success
-dontwarn com.crypto.domain_models.DataResult
-dontwarn com.crypto.repositories.CoinRepository