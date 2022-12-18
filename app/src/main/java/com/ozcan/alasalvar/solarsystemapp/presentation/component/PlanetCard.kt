package com.ozcan.alasalvar.solarsystemapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ozcan.alasalvar.solarsystemapp.domain.model.Planet

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlanetCard(modifier: Modifier = Modifier, planet: Planet) {

    Surface(modifier = modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .fillMaxHeight(1f)
                .background(Color(planet.color))
        ) {

            Text(
                text = "${planet.position}",
                color = Color.White,
                fontSize = 250.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .alpha(0.5f)
                    .offset(5.dp, (35).dp)
            )


            GlideImage(
                model = planet.image,
                contentDescription = "planet image",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .height(220.dp)
                    .width(220.dp)
                    .align(Alignment.TopEnd)
            )


            Text(
                text = planet.name,
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.surface,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 15.dp, end = 15.dp)
            )

        }


    }
}