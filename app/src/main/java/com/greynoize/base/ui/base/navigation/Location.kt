package com.greynoize.base.ui.base.navigation

interface Location {
    class Browse(val url: String) : Location
    class Screen(val id: Int): Location
}