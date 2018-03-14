package com.ekalips.skeleton.stuff.navigation

enum class Place{
    SPLASH
}


data class Navigate(val place: Place, val payload: Any? = null)
