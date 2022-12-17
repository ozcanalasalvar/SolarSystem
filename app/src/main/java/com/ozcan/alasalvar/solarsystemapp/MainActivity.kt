package com.ozcan.alasalvar.solarsystemapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ozcan.alasalvar.solarsystemapp.ui.theme.SolarSystemAppTheme
import com.ozcan.alasalvar.solarsystemapp.view.DetailScreen
import com.ozcan.alasalvar.solarsystemapp.view.HomeScreen
import com.ozcan.alasalvar.solarsystemapp.view.Navigation

@OptIn(ExperimentalPagerApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SolarSystemAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                    //HomeScreen()
                    //DetailScreen()
                }
            }
        }
    }
}
