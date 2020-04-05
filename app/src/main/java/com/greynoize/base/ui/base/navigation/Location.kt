package com.greynoize.base.ui.base.navigation

import com.greynoize.base.ui.base.BaseFragment

interface Location {
    class Browse(val url: String) : Location
    class Screen(val id: Int): Location
}