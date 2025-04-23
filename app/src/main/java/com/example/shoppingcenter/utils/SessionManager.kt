package com.example.shoppingcenter.utils

import android.content.Context


class SessionManager(context: Context) {

    private val sharedPref = context.getSharedPreferences("shopping_session", Context.MODE_PRIVATE)

    fun getFavorites() : MutableSet<String> {
        return sharedPref.getStringSet("WHICH_LIST", mutableSetOf())!!
    }

    fun setFavorites(whichList: MutableSet<String>) {
        val editor = sharedPref.edit()
        editor.putStringSet("WHICH_LIST", whichList)
        editor.apply()
    }

    fun addFavorite(id: String) {
        val whichList = getFavorites()
        whichList.add(id)
        setFavorites(whichList)
    }

    fun removeFavorite(id: String) {
        val whichList = getFavorites()
        whichList.remove(id)
        setFavorites(whichList)
    }

    fun isFavorite(id: String): Boolean {
        return getFavorites().contains(id)
    }
}