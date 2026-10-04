# Lafyuu - Modern E-Commerce Android Application

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green.svg" alt="Platform">
  <img src="https://img.shields.io/badge/Language-Kotlin-orange.svg" alt="Kotlin">
  <img src="https://img.shields.io/badge/UI-Jetpack%20Compose-blue.svg" alt="Jetpack Compose">
  <img src="https://img.shields.io/badge/Architecture-MVVM-brightgreen.svg" alt="Architecture">
</p>

**Lafyuu** is a modern, feature-rich e-commerce Android application built with native Android development tools and Jetpack Compose. It provides a seamless shopping experience with a clean UI, secure authentication flows, flash sales, product categorization, and interactive rating systems.

---

## 🚀 Features

- **User Authentication:** Secure registration and login screens with input validation.
- **Dynamic Home Screen:** Features Flash Sale banners, category navigation, and product grids.
- **Interactive UI Components:** Custom-built `AuthTextField`, `ProductCard`, `StarRating`, and `PrimaryButton`.
- **State Management:** Reactive UI updates powered by ViewModel and `StateFlow`.
- **Dependency Injection:** Clean and scalable architecture managed using **Hilt**.
- **Modern Design System:** Built entirely with **Jetpack Compose** and **Material 3**, supporting custom theme colors.

- ## 📱 Screenshots

| Register Screen | Login Screen | Home Screen | Product Details |
| :---: | :---: | :---: | :---: |
| <img src="screenshots/register.png" width="200" alt="Register Screen"> | <img src="screenshots/login.png" width="200" alt="Login Screen"> | <img src="screenshots/home.png" width="200" alt="Home Screen"> | <img src="screenshots/detail.png" width="200" alt="Product Details"> |

---

## 🛠️ Tech Stack & Architecture

- **Language:** [Kotlin](https://kotlinlang.org/)
- **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose) & Material 3
- **Architecture Pattern:** MVVM (Model-View-ViewModel)
- **Dependency Injection:** [Hilt](https://dagger.dev/hilt/)
- **Asynchronous Programming:** Kotlin Coroutines & Flow
- **Navigation:** Jetpack Navigation Component

---

## 📂 Project Structure

```text
com.example.lafyuu_projectfinal/
│
├── model/           # Data models (Product, Category, etc.)
├── screen/          # Composable screens (Register, Login, Home, etc.)
│   ├── components/  # Reusable UI widgets (AuthTextField, StarRating, ProductGrid)
│   └── register/    # Register screen & ViewModel
└── ui/theme/        # Color palette, typography, and shapes
