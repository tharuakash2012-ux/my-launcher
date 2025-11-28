# Hybrid Launcher (OnePlus-iOS style) - Starter Project

This is a starter Android launcher project (Kotlin). It implements:
- iOS-like paged home screens (no app drawer)
- iOS-style dock (blur placeholder)
- Custom Control Center view
- Swipe-up gesture to return home
- A custom LockScreenActivity (cosmetic; will not override secure system lock)
- Notch-aware layout hints (use WindowInsets in further improvements)

## Notes for Redmi 6 Pro (Android 9 / API 28)
- This starter uses minSdk 26 and should install on your Redmi 6 Pro running Android 9.
- For best visuals, test on your device and adjust paddings and icon sizes for the screen resolution.

## How to open
1. Open this folder in Android Studio (recommended Android Studio Flamingo or later).
2. Gradle will sync. Build and run on device (allow installation from unknown sources if needed).
3. When setting as Home app, choose "HybridLauncher".

## Next steps
- Implement RenderEffect blur (Android 12+) with fallbacks for older devices.
- Add shared element transitions for app open/close animations.
- Add an icon pack importer and custom wallpaper engine.


## Updates included in this package
- Blur effect util (uses RenderEffect on Android 12+ with fallback).
- Control Center activity with Wi‑Fi / Bluetooth buttons and brightness slider.
- Notch-safe WindowInsets handling for Redmi 6 Pro (1080x2280, 5.84") as referenced from official specs.
- Icon & dock size tuned for Redmi 6 Pro (larger icons and taller dock).
- LockScreenActivity improved to immersive full-screen.

## Device spec source
- Redmi 6 Pro — 1080 x 2280 px, 5.84 inches (sources: Xiaomi official site, Gadgets360).