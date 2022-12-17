package com.ozcan.alasalvar.solarsystemapp.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FeatureCard(
    imageVector: ImageVector,
    name: String,
    value: String,
    unit: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(end = 30.dp), shape = RoundedCornerShape(20.dp),
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(Modifier.fillMaxSize()) {

            Icon(
                modifier = Modifier
                    .size(34.dp)
                    .align(Alignment.CenterVertically)
                    .padding(start = 15.dp),
                imageVector = imageVector,// ImageVector.vectorResource(id = R.drawable.velocity)
                contentDescription = "Some icon",
                tint = MaterialTheme.colors.surface,
            )


            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = name,
                    fontSize = 13.sp,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                )
                Text(
                    text = buildAnnotatedString {
                        append(value)
                        append(" ")
                        append(unit)
                    },
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                        .padding(top = 5.dp)
                )
            }
        }
    }
}