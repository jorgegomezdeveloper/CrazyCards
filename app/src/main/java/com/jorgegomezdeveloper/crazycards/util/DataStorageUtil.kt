package com.jorgegomezdeveloper.crazycards.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

/**
 * Class for to save data in a file.
 */
class DataStorageUtil(context: Context, FILE_PREFERENCE: String = "filePreferences") {

    private var sharedPreferences: SharedPreferences? =
        context.getSharedPreferences(FILE_PREFERENCE, Activity.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPreferences!!.edit()

    /**
     * START GET FUNCTIONS
     */
    fun getPreferencesString(key: String?, defaultValue: String?): String? {
        return this.sharedPreferences!!.getString(key, defaultValue)
    }

    fun getPreferencesInt(key: String?, defaultValue: Int): Int {
        return this.sharedPreferences!!.getInt(key, defaultValue)
    }

    fun getPreferencesFloat(key: String?, defaultValue: Float): Float {
        return this.sharedPreferences!!.getFloat(key, defaultValue)
    }

    fun getPreferencesLong(key: String?, defaultValue: Long): Long {
        return this.sharedPreferences!!.getLong(key, defaultValue)
    }

    fun getPreferencesBoolean(key: String?, defaultValue: Boolean): Boolean {
        return this.sharedPreferences!!.getBoolean(key, defaultValue)
    }

    /**
     * START PUT FUNCTIONS
     */
    fun putPreferenceString(key: String?, value: String?) {
        this.editor.putString(key, value)
        this.editor.commit()
    }

    fun putPreferenceInt(key: String?, value: Int) {
        this.editor.putInt(key, value)
        this.editor.commit()
    }

    fun putPreferenceLong(key: String?, value: Long) {
        this.editor.putLong(key, value)
        this.editor.commit()
    }

    fun putPreferenceBoolean(key: String?, value: Boolean) {
        this.editor.putBoolean(key, value)
        this.editor.commit()
    }

    /**
     * START DELETE FUNCTIONS
     */
    fun deletePreferenceValue(key: String?) {
        editor.remove(key)
        editor.commit()
    }

    fun removeAllData() {
        editor.clear()
        editor.commit()
    }
}