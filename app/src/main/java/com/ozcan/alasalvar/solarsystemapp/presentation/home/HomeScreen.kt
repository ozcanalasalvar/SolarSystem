package com.ozcan.alasalvar.solarsystemapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.ozcan.alasalvar.solarsystemapp.R
import com.ozcan.alasalvar.solarsystemapp.presentation.component.PlanetSlider
import com.ozcan.alasalvar.solarsystemapp.domain.model.Planet
import com.ozcan.alasalvar.solarsystemapp.presentation.Screen
import com.ozcan.alasalvar.solarsystemapp.presentation.withArgs


@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState

    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.success.isNotEmpty()) {
            HomeContent(uiState.success) { position ->
                navController.navigate(Screen.Details.withArgs(position))
            }
        }

        if (uiState.loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(100.dp), color = MaterialTheme.colors.surface
            )
        }

        uiState.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }


    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeContent(planets: List<Planet>, onClick: (Int) -> Unit) {

    val planetsInfo = remember { mutableStateOf("") }

    val pagerState = rememberPagerState(initialPage = 2)

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            val description = planets[page].description
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
                .padding(top = 30.dp)
        )

        PlanetSlider(
            planets, pagerState,
            modifier = Modifier
                //.height(450.dp)
                .fillMaxHeight(0.6f)
                .padding(0.dp, 30.dp, 0.dp, 70.dp)
                .constrainAs(slider) {
                    top.linkTo(header.bottom)
                    bottom.linkTo(info.top)
                },
            onClick = { position ->
                onClick(position)
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
                .padding(start = 15.dp, end = 15.dp, bottom = 20.dp)
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
            text = stringResource(id = R.string.explore),
            color = MaterialTheme.colors.surface,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 15.dp)
        )

        Text(
            text = stringResource(id = R.string.solar_system),
            color = MaterialTheme.colors.onSurface,
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}

@Composable
fun TextPlanetInfo(info: String, modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.snippet),
            color = MaterialTheme.colors.surface,
            fontSize = 19.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 10.dp)
        )


        Row(
            modifier = Modifier
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
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(start = 15.dp, bottom = 5.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}