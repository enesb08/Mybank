package com.enesbaskaya.mybank.util

import android.content.Context
import android.content.SharedPreferences

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javax.inject.Inject


class LocalDataManager @Inject constructor(context: Context) {

    var pref: SharedPreferences? = null
    private var mGson: Gson? = null

    var mContext: Context? = null


    init {

        mContext = context
        pref = mContext!!.getSharedPreferences(mContext?.packageName, Context.MODE_PRIVATE)
        mGson = GsonBuilder().disableHtmlEscaping().create()


    }

    fun getLanguage(): String {
        return pref?.getString(KEY_LANGUAGE_CODE, "en").toString()
    }

    fun setLanguage(languageCode: String?) {
        pref?.edit()?.putString(KEY_LANGUAGE_CODE, languageCode)?.commit()
    }


    companion object {

        const val KEY_LANGUAGE_CODE = "mLanguageCode"


    }


}