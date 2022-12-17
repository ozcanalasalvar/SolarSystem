package com.ozcan.alasalvar.solarsystemapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.ozcan.alasalvar.solarsystemapp.R
import com.ozcan.alasalvar.solarsystemapp.component.PlanetSlider
import com.ozcan.alasalvar.solarsystemapp.data.Planets

@OptIn(ExperimentalPagerApi::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen() {

    val planets = remember { mutableStateOf(Planets.planets) }

    val planetsInfo = remember { mutableStateOf("") }

    val pagerState = rememberPagerState(initialPage = 2)

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            val description = planets.value[page].description
            planetsInfo.value = description
        }
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (header, slider, info, indicator) = createRefs()

        HomeHeader(
            modifier = Modifier
                .constrainAs(header) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .padding(top = 50.dp)
        )

        PlanetSlider(
            planets.value, pagerState,
            modifier = Modifier
                //.height(450.dp)
                .fillMaxHeight(0.6f)
                .padding(0.dp, 40.dp, 0.dp, 70.dp)
                .constrainAs(slider) {
                    top.linkTo(header.bottom)
                    bottom.linkTo(info.top)
                }
        )



        TextPlanetInfo(
            planetsInfo.value,
            modifier = Modifier
                .constrainAs(info) {
                    bottom.linkTo(indicator.top, 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 30.dp, end = 30.dp))


        //Horizontal dot indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .constrainAs(indicator) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            activeColor = MaterialTheme.colors.surface
        )

    }
}


@Composable
fun HomeHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.welcome_aboard),
            color = MaterialTheme.colors.surface,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 15.dp)
        )

        Text(
            text = stringResource(id = R.string.see_what),
            color = MaterialTheme.colors.onSurface,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 15.dp, top = 10.dp)
        )
    }
}

@Composable
fun TextPlanetInfo(info: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier
                .width(2.dp)
                .background(MaterialTheme.colors.onSurface)
                .fillMaxSize()
        )


        Text(
            text = info,
            color = MaterialTheme.colors.onSurface,
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 15.dp, bottom = 5.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}