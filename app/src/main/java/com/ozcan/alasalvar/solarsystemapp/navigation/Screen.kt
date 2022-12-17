package com.ozcan.alasalvar.solarsystemapp.navigation

import androidx.annotation.StringRes
import com.ozcan.alasalvar.solarsystemapp.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("home", R.string.text_home)
    object Details : Screen("details", R.string.text_details)
}
