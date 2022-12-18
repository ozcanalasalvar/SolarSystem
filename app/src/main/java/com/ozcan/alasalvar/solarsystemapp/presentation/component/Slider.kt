package com.ozcan.alasalvar.solarsystemapp.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.lerp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.ozcan.alasalvar.solarsystemapp.domain.model.Planet
import kotlin.math.absoluteValue


@ExperimentalPagerApi
@Composable
fun PlanetSlider(
    planets: List<Planet>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {


//    LaunchedEffect(Unit) {
//        while (true) {
//            yield()
//            delay(2000)
//            pagerState.animateScrollToPage(
//                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
//                animationSpec = tween(600)
//            )
//        }
//    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        count = planets.size,
        contentPadding = PaddingValues(end = 60.dp, start = 60.dp),
    ) { page ->

        Card(modifier = Modifier
            .graphicsLayer {
                // Calculate the absolute offset for the current page from the
                // scroll position. We use the absolute value which allows us to mirror
                // any effects for both directions
                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                // We animate the scaleX + scaleY, between 85% and 100%
                lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }

                // We animate the alpha, between 50% and 100%
                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            }
            .fillMaxWidth()
            .padding(5.dp, 0.dp, 0.dp, 0.dp)
            .clickable {
                onClick(page)
            },
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(3.dp, Color.White),
            elevation = 10.dp
        ) {
            PlanetCard(planet = planets[page])
        }


    }


}

