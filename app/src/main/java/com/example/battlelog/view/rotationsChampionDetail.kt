package com.example.battlelog.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.battlelog.R
import com.example.battlelog.ui.theme.BatteLogTheme


@Preview(showBackground = true)
@Composable
fun PreviewPage(){
    BatteLogTheme {
        rotationsChampionDetail()
    }
}



@Composable
fun rotationsChampionDetail(
    navController: NavController? = null,
){
    Scaffold(
        topBar = {
            Column {
                Row(
                    modifier = Modifier.padding(top = 50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    IconButton(
                        onClick = {
                            navController?.navigate(Routes.home)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.angle_left_solid),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(
                        text = "Tướng xoay tua",
                        style = MaterialTheme.typography.titleLarge
                    )
                }


            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            SplashAllFreeChampion(Modifier)
        }
    }
}

@Composable
fun SplashAllFreeChampion(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(top = 20.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        dummySplashChampionData.chunked(2).forEach { pair ->
            Row(
                modifier = modifier
                    .fillMaxWidth().padding(start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SplashRotationChamp(champion = pair[0]) // Draw first champion
                if (pair.size > 1) {
                    SplashRotationChamp(champion = pair[1]) // Draw second champion if present
                }
            }
        }
    }
}


@Composable
fun SplashRotationChamp(
    champion: Champion
) {
    Column(
        modifier = Modifier.widthIn(min= 90.dp, max = 180.dp).padding(bottom = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(id = champion.imageResId),
            contentDescription = null,
            modifier = Modifier
                .widthIn(min= 80.dp, max = 170.dp)
                .clip(RoundedCornerShape(5.dp)),
            alignment = Alignment.TopStart,
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Tướng miễn phí",
            color = colorResource(R.color.orange_FF7223),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
        )
        Text(text = champion.name, style = MaterialTheme.typography.bodyMedium)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text(
                text = "${champion.fullName}",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

val dummySplashChampionData = listOf(
    Champion("Aatrox", R.drawable.aatrox_splash_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_splash_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_splash_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_splash_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_splash_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_splash_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_splash_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_splash_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_splash_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_splash_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_splash_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_splash_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_splash_0, "Quỷ kiếm Darkin"),
    )