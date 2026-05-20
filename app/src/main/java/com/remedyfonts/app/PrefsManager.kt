package com.remedyfonts.app

import android.content.Context
import android.content.SharedPreferences

object PrefsManager {
    private const val PREFS_NAME = "remedy_prefs"
    private const val KEY_UNLOCKED_FONTS = "unlocked_fonts"
    private const val KEY_UNLOCKED_CATEGORIES = "unlocked_categories"

    private fun getPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun isFontUnlocked(context: Context, fontName: String): Boolean {
        return getPrefs(context).getStringSet(KEY_UNLOCKED_FONTS, emptySet())?.contains(fontName) == true
    }

    fun unlockFont(context: Context, fontName: String) {
        val prefs = getPrefs(context)
        val unlocked = prefs.getStringSet(KEY_UNLOCKED_FONTS, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        unlocked.add(fontName)
        prefs.edit().putStringSet(KEY_UNLOCKED_FONTS, unlocked).apply()
    }

    fun isCategoryUnlocked(context: Context, category: String): Boolean {
        return getPrefs(context).getStringSet(KEY_UNLOCKED_CATEGORIES, emptySet())?.contains(category) == true
    }

    fun unlockCategory(context: Context, category: String) {
        val prefs = getPrefs(context)
        val unlocked = prefs.getStringSet(KEY_UNLOCKED_CATEGORIES, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        unlocked.add(category)
        prefs.edit().putStringSet(KEY_UNLOCKED_CATEGORIES, unlocked).apply()
    }
}
