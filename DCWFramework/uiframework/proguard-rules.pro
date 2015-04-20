# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/DaoCaoWu/Documents/Developer/adt-bundle-mac-x86_64-20140321/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-injars framework_debug.jar
-outjars framework.jar

-dontshrink

-libraryjars '.\libs\alt-rt.jar'
-libraryjars '.\libs\android.jar'

-optimizationpasses 3
-keepattributes Exceptions

-keepattributes InnerClasses,Signature,SourceFile,LineNumberTable,*Annotation*

-libraryjars  '..\libs\android-support-v4.jar'
-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment

-keep class cn.ninegame.genericframework.** {
    public <fields>;
    public <methods>;
}

# Keep - Applications. Keep all application classes, along with their 'main'
# methods.
-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}

# Also keep - Enumerations. Keep the special static methods that are required in
# enumeration classes.
-keepclassmembers enum  * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
