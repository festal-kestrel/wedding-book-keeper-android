package com.example.wedding_book_keeper.presentation.config

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Prefs(context: Context) {
    private val PREF_NAME = "wedding_bookkeeper_preference"
    private val prefs = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    var token: String?
        get() = prefs.getString("token", null)
        set(value) {
            prefs.edit().putString("token", value).apply()
        }

    var weddingId: Long?
        get() = prefs.getLong("weddingId", 0)
        set(value) {
            prefs.edit().putLong("weddingId", value!!).apply()
        }

}
