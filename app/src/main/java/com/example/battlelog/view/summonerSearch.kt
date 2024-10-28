package com.example.battlelog.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.battlelog.R
import com.example.battlelog.ui.theme.BatteLogTheme
import com.example.battlelog.ui.theme.bronzeColor


@Preview
@Composable
fun SummerSearchPreview(){

    var currentSearchKey by remember { mutableStateOf("") }

    BatteLogTheme {
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier.padding(top = 50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    IconButton(
                        onClick = {
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.angle_left_solid),
                            contentDescription = null,
                            modifier = Modifier
                                .size(32.dp)
                                .offset(x = 10.dp)
                        )
                    }
                    SearchBar(Modifier, stringResource(R.string.search_placeholder),
                        value = currentSearchKey,
                        onChange = {
                            currentSearchKey = it
                        }
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(
                        rememberScrollState()
                    ),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                if (currentSearchKey == "" ){
                    SearchRecent()
                } else {
                    SummonerFilter()
                }
            }

        }
    }
}


@Composable
fun SummonerSearch(
    navController: NavController,
){
    var currentSearchKey by remember { mutableStateOf("") }
    BatteLogTheme {
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier.padding(top = 50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    IconButton(
                        onClick = {
                            navController.navigate(Routes.home)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.angle_left_solid),
                            contentDescription = null,
                            modifier = Modifier
                                .size(32.dp)
                                .offset(x = 10.dp)
                        )
                    }
                    SearchBar(
                        Modifier,
                        stringResource(R.string.search_placeholder),
                        value = currentSearchKey,
                        onChange = {
                            currentSearchKey = it
                        })
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(
                        rememberScrollState()
                    ),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                if(currentSearchKey == "") {
                    SearchRecent(navController)
                } else {
                    SummonerFilter(navController)
                }
            }

        }
    }
}


@Composable
fun SummonerFilter(
    navController: NavController? = null
){
    val summonerList = remember { mutableStateListOf("Hide on bú", "Hide on bush", "Hít bú") }
    Column (
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
    ){
        summonerList.forEach { summoner ->
            if (navController != null) {
                summonerRecent(summoner, navController = navController, onDelete = {
                    summonerList.remove(summoner)
                })
            }
        }
    }
}
@Composable
fun SearchRecent(
    navController: NavController? = null
){
    val summonerRecentList = remember { mutableStateListOf("ThanhLienMinh", "Summoner2", "Summoner3") }
    Column (
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text="Tìm kiếm gần đây",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.alignByBaseline()
            )
            Text(
                text="Xóa lịch sử",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .alignByBaseline()
                    .clickable {

                    }
            )
        }
        summonerRecentList.forEach { summoner ->
            if (navController != null) {
                summonerRecent(summoner, navController = navController, onDelete = {
                    summonerRecentList.remove(summoner)
                })
            }
        }

    }


}


@Composable
fun summonerRecent(
    summoner: String,
    onDelete: () -> Unit,
    navController: NavController
){
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .clickable {

            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Box(
            modifier = Modifier.clickable {
                navController.navigate(Routes.profileDetail)
            }
        ){
            Image(
                painter = painterResource(R.drawable.fk2024icon),
                contentDescription = null,
                modifier = Modifier
                    .size(38.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 50.dp, end = 20.dp)
            )
            {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ){
                    Text(
                        text = summoner,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        text = "#VN2",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = "Bronze",
                    style = MaterialTheme.typography.bodySmall,
                    color = bronzeColor
                )
            }
        }
        IconButton(
            onClick = {
                onDelete()
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.xmark_solid),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }

}

//Test data