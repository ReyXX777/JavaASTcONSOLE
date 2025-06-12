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
# --- Jetpack Compose ---
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# --- ViewModel + LiveData/State ---
-keep class androidx.lifecycle.ViewModel
-keep class androidx.lifecycle.** { *; }
-dontwarn androidx.lifecycle.**

# --- Kotlin (Coroutines, Reflection, Lambdas) ---
-keep class kotlin.** { *; }
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlin.**
-dontwarn kotlinx.coroutines.**

# Kotlin metadata
-keepclassmembers class ** {
    @kotlin.Metadata *;
}

# Keep code examples and AST node model (you don’t want them obfuscated)
-keep class com.example.compiler.utils.Example { *; }
-keep class com.example.compiler.utils.CodeExamples { *; }
-keep class com.example.compiler.utils.ASTNode { *; }
-keepclassmembers class com.example.compiler.utils.ASTNode {
    *;
}

# --- JSON parsing (if using kotlinx.serialization or Gson) ---
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

# --- Retrofit/Moshi (if applicable) ---
# -keep class com.squareup.moshi.** { *; }
# -keep @com.squareup.moshi.JsonClass class * { *; }

# --- Entry Points ---
-keepclassmembers class * {
    public <init>(...);
}

# --- General Android Rules ---
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-keepattributes Signature

-keep class com.example.compiler.data.network.** { *; }
-keepattributes Signature
-keepattributes *Annotation*
