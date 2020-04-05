package com.greynoize.base.ui.base

interface Location {
    class Browse(val url: String) : Location
    class Screen(val fragment: BaseFragment<*>): Location
}