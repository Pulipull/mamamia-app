package com.example.myapplication.data

import android.content.Context
import android.content.SharedPreferences

class ProfileRepository(private val context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("restaurant_prefs", Context.MODE_PRIVATE)

    fun getRestaurantName(): String = prefs.getString("name", "Mamamia") ?: "Mamamia"
    fun getAddress(): String = prefs.getString("address", "Viale del Corso No. 22, Jakarta") ?: ""
    fun getDescription(): String = prefs.getString("description", "Pionir Italian Street Food yang menyajikan hidangan cepat saji berkualitas dengan bahan-bahan impor terbaik dari Italia.") ?: ""
    fun getOpenHours(): String = prefs.getString("open_hours", "10:00 - 22:00") ?: ""

    fun isDarkMode(): Boolean = prefs.getBoolean("dark_mode", false)
    fun setDarkMode(enabled: Boolean) {
        prefs.edit().putBoolean("dark_mode", enabled).apply()
    }

    fun saveProfile(name: String, address: String, description: String, openHours: String) {
        prefs.edit().apply {
            putString("name", name)
            putString("address", address)
            putString("description", description)
            putString("open_hours", openHours)
            apply()
        }
    }
}