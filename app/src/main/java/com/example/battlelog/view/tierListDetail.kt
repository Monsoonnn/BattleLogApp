package com.example.battlelog.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.battlelog.R
import com.example.battlelog.ui.theme.bottomBorder
import kotlin.random.Random


@Preview(showBackground = true)
@Composable
fun TierListChampion(
    navController: NavController? = null
) {
    var selectedPage by remember { mutableStateOf("TOP") }
    val pages = listOf("TOP", "JUG", "MID", "ADC", "SUP")

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
                        text = "Xếp hạng tướng",
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                // Tabs for pages in topBar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    pages.forEach { page ->
                        Text(
                            text = page.uppercase(),
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable { selectedPage = page }
                                .then(
                                    if (selectedPage == page) {
                                        Modifier.bottomBorder(1.dp, MaterialTheme.colorScheme.primary)
                                    } else {
                                        Modifier
                                    }
                                )
                                .padding(10.dp),
                            color = if (selectedPage == page) Color.Black else colorResource(R.color.gray_998F8F)
                        )
                    }
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
            TierListDetail(selectedPage = selectedPage)
        }
    }
}

@Composable
fun TierListDetail(
    selectedPage: String,
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                listOf(
                    Pair("#", 0.1f),
                    Pair("Tier", 0.12f),
                    Pair("Champ", 0.35f),
                    Pair("Win%", 0.15f),
                    Pair("Pick%", 0.15f),
                    Pair("Ban%", 0.15f)
                ).forEach { (header, weight) ->
                    Text(
                        text = header,
                        modifier = Modifier.weight(weight),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Fake Data using GPT
            val dummyData = when (selectedPage) {
                "TOP" -> List(25) { index ->
                    Pair(
                        listOf("S", "A", "B").random(),
                        "Top Champion ${('A'..'Z').random()}"
                    )
                }
                "JUG" -> List(25) { index ->
                    Pair(
                        listOf("S", "A", "B", "C").random(),
                        "Jug Champion ${('A'..'Z').random()}"
                    )
                }
                "MID" -> List(25) { index ->
                    Pair(
                        listOf("S", "A", "B", "C", "D").random(),
                        "Mid Champion ${('A'..'Z').random()}"
                    )
                }
                "ADC" -> List(25) { index ->
                    Pair(
                        listOf("A", "B", "C", "D").random(),
                        "ADC Champion ${('A'..'Z').random()}"
                    )
                }
                "SUP" -> List(25) { index ->
                    Pair(
                        listOf("A", "B", "C", "D").random(),
                        "Sup Champion ${('A'..'Z').random()}"
                    )
                }
                else -> emptyList()
            }

            dummyData.forEachIndexed { index, entry ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = (index + 1).toString(), modifier = Modifier.weight(0.1f), style = MaterialTheme.typography.labelSmall)

                    Box(
                        modifier = Modifier
                            .weight(0.1f)
                            .offset(x = -10.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.samira),
                            contentDescription = null,
                            modifier = Modifier
                                .size(35.dp)
                                .align(Alignment.Center)
                                .offset(x = 3.dp)
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .background(color = MaterialTheme.colorScheme.primary)
                                .padding(horizontal = 2.dp)
                        ) {
                            Text(
                                text = entry.first,
                                color = Color.White,
                                style = MaterialTheme.typography.labelSmall,
                            )
                        }

                    }

                    Text(text = entry.second, modifier = Modifier.weight(0.3f))
                    Text(
                        text = "${Random.nextInt(40, 61)}%",
                        modifier = Modifier.weight(0.133f)
                    ) // Random Win%
                    Text(
                        text = "${Random.nextInt(15, 31)}%",
                        modifier = Modifier.weight(0.133f)
                    ) // Random Pick%
                    Text(
                        text = "${Random.nextInt(5, 16)}%",
                        modifier = Modifier.weight(0.134f)
                    ) // Random Ban%
                }
            }
        }
    }
}
