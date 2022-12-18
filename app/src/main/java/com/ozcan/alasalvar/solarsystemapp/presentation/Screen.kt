package com.ozcan.alasalvar.solarsystemapp.presentation

import androidx.annotation.StringRes
import com.ozcan.alasalvar.solarsystemapp.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("home", R.string.text_home)
    object Details : Screen("details", R.string.text_details)

}

fun Screen.withArgs(vararg args: Int): String {
    return buildString {
        append(route)
        args.forEach { arg ->
            append("/$arg")
        }
    }
}
