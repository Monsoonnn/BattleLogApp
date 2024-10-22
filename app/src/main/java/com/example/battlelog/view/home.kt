package com.example.battlelog.view



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.battlelog.R
import com.example.battlelog.ui.theme.BatteLogTheme
import com.example.battlelog.ui.theme.bottomBorder
import com.example.battlelog.ui.theme.topBorder
import kotlin.random.Random


@Composable
fun TopBar(
    modifier: Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(R.drawable.lol_battle_log_nobg),
            contentDescription = null,
            modifier = modifier.size(100.dp).padding(15.dp)
        )
        Text(
            style = MaterialTheme.typography.titleLarge,
            text = stringResource(R.string.app_name),
        )
        ServerChoose()
    }
}


@Composable
fun BottomBar(
    modifier: Modifier,
    navController: NavController
){

    Row(
        modifier = modifier
            .fillMaxWidth()
            .topBorder(1.dp, MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){
        var selectedBox by remember { mutableStateOf("Home") }

        val defaultModifier = Modifier.size(90.dp).padding(10.dp)

        Box(
            modifier = defaultModifier.clickable { selectedBox = "Home" },
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(R.drawable.house_solid),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    colorFilter = if (selectedBox != "Home") androidx.compose.ui.graphics.ColorFilter.tint(
                        colorResource(R.color.gray_998F8F)) else null
                )
                Text(
                    text = stringResource(R.string.home),
                    style = MaterialTheme.typography.labelMedium,
                    color = if (selectedBox != "Home") MaterialTheme.colorScheme.primary else Color.Black

                )
            }
        }

        Box(
            modifier = defaultModifier.clickable { selectedBox = "Champions" },
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    navController.navigate("Champion_Search")
                }
            ) {
                Image(
                    painter = painterResource(R.drawable.champion),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    colorFilter = if (selectedBox != "Champions") androidx.compose.ui.graphics.ColorFilter.tint(
                        colorResource(R.color.gray_998F8F)) else null
                )
                Text(
                    text = stringResource(R.string.Champions),
                    style = MaterialTheme.typography.labelMedium,
                    color = if (selectedBox != "Champions")  MaterialTheme.colorScheme.primary else Color.Black
                )
            }
        }

        Box(
            modifier = defaultModifier.clickable { selectedBox = "Settings" },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(R.drawable.settings),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    colorFilter = if (selectedBox != "Settings") androidx.compose.ui.graphics.ColorFilter.tint(
                        colorResource(R.color.gray_998F8F)) else null
                )
                Text(
                    text = stringResource(R.string.settings),
                    style = MaterialTheme.typography.labelMedium,
                    color = if (selectedBox == "Settings")  MaterialTheme.colorScheme.primary else Color.Black
                )
            }
        }



    }
}


@Composable
fun ServerChoose() {
    val servers = listOf("VN", "KR", "NA", "EUW", "BR", "EUN", "RU")
    var expanded by remember { mutableStateOf(false) }
    var selectedServer by remember { mutableStateOf(servers[0]) }

    Box(
        modifier = Modifier.padding(10.dp).clickable { expanded = true },
    ) {
        Row (
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = selectedServer,
                modifier = Modifier
                    .padding(10.dp)
                    .background(
                        color = colorResource(R.color.EEEDF3)
                    )
                    .border(1.dp, MaterialTheme.colorScheme.onPrimary)
                    .padding(10.dp)
            )
            Icon(
                painter = painterResource(R.drawable.angle_down_solid),
                contentDescription = null,
                modifier = Modifier.size(14.dp,14.dp)

            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(0.2f) // Adjusts the width of the DropdownMenu
        ) {
            servers.forEach { server ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = server,
                        )
                    },
                    onClick = {
                        selectedServer = server
                        expanded = false
                    },
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
        }

    }
}

@Composable
fun TierList(
    modifier: Modifier
){
    var selectedPage by remember { mutableStateOf("TOP") }

    val pages = listOf("TOP", "JUG", "MID", "ADC", "SUP")

    Column {
        Text(
            text = "Xếp hạng tướng",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(10.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 0.dp, bottom = 0.dp),
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


        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
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

            val dummyData = List(5) { index ->
                Triple(
                    (index + 1).toString(),
                    listOf("S", "A", "B", "C", "D").random(),
                    "Champion ${('A'..'Z').random()}"
                )
            }

            dummyData.forEach { entry ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = entry.first, modifier = Modifier.weight(0.1f), style = MaterialTheme.typography.labelSmall)

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
                                text = entry.second,
                                color = Color.White,
                                style = MaterialTheme.typography.labelSmall,
                            )
                        }

                    }

                    Text(text = entry.third, modifier = Modifier.weight(0.3f))
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
            ElevatedButton (
                modifier = modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f),
                onClick = {

                },
                content = {
                    Text(
                        text = "Xem tất cả",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            )

        }

}

/*@Preview(showBackground = true)
@Composable
fun HomePreview() {
    BatteLogTheme {
        Scaffold(
            topBar = { TopBar(Modifier)},
            bottomBar = { BottomBar(Modifier, navController = NavController())},
        ){
            paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(
                        rememberScrollState() // Add vertical scrolling capability
                    ),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
               SearchBar(Modifier)
//                TierList(Modifier)
//                FreeChampions(Modifier)
            }

        }
    }

}*/

@Composable
fun FreeChampions(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        Text(
            text = "Tướng xoay tua",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(10.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(dummyChampions) { champion ->
                ChampionRotations(champion)
            }
        }
        ElevatedButton (
            modifier = modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.8f),
            onClick = {

            },
            content = {
                Text(
                    text = "Xem tất cả",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        )
    }
}

@Composable
fun ChampionRotations(
    champion: Champion
) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .padding(10.dp, )
    ) {
        Image(
            painter = painterResource(id = champion.imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            alignment = Alignment.TopStart
        )
        Text(
            text = "Tướng miễn phí",
            color = colorResource(R.color.orange_FF7223),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 4.dp)
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




// Dummy data model for skins
data class Champion(
    val name: String,
    val imageResId: Int,
    val fullName: String,
)

// Example data
val dummyChampions = listOf(
    Champion("Aatrox", R.drawable.aatrox_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_0, "Quỷ kiếm Darkin"),
    Champion("Aatrox", R.drawable.aatrox_0, "Quỷ kiếm Darkin"),


)