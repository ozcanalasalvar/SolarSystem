package com.ozcan.alasalvar.solarsystemapp.presentation.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ozcan.alasalvar.solarsystemapp.R
import com.ozcan.alasalvar.solarsystemapp.presentation.component.FeatureCard
import com.ozcan.alasalvar.solarsystemapp.data.Planets

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(position: Int, navController: NavController) {

    val planet = Planets.planets[position]

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {


            GlideImage(
                model = planet.image,
                contentDescription = "planet image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(350.dp)
                    .fillMaxWidth()
                    .align(Alignment.End)
                    .offset(x = 90.dp)
            )

            Text(
                text = planet.name,
                fontSize = 35.sp,
                color = MaterialTheme.colors.surface,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(alignment = Alignment.Start)
            )


            Text(
                text = stringResource(id = R.string.solar_system),
                fontSize = 13.sp,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 2.dp)
            )

            Text(
                text = planet.description,
                color = MaterialTheme.colors.surface,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 5.dp, top = 30.dp),
                lineHeight = 25.sp
            )


            FeatureCard(
                imageVector = ImageVector.vectorResource(id = R.drawable.velocity),
                name = "Velocity",
                value = planet.velocity,
                unit = "km/s",
                modifier = Modifier.padding(top = 30.dp)
            )

            FeatureCard(
                imageVector = ImageVector.vectorResource(id = R.drawable.location),
                name = "Velocity",
                value = planet.distance,
                unit = "million kilometers",
                modifier = Modifier.padding(top = 15.dp, bottom = 50.dp)
            )
        }

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.back_arrow),
            contentDescription = "Some icon",
            tint = MaterialTheme.colors.surface,
            modifier = Modifier
                .padding(top = 30.dp)
                .size(34.dp)
                .clickable {
                    navController.popBackStack()
                },
        )
    }


}