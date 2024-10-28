package com.example.battlelog.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.battlelog.R
import com.example.battlelog.ui.theme.BatteLogTheme
import com.example.battlelog.ui.theme.bottomBorder
import com.example.battlelog.ui.theme.loseColor
import com.example.battlelog.ui.theme.rakingTitleColor
import com.example.battlelog.ui.theme.tagGrayColor
import com.example.battlelog.ui.theme.topBorder
import com.example.battlelog.ui.theme.winColor

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    BatteLogTheme {
        ProfileDetail()
    }
}


@Composable
fun ProfileDetail(
    navController: NavController? = null
    // Truyền data vào đây
){
    Scaffold (
        topBar = {
            profileHeader(navController)
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(
                    rememberScrollState()
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            profileRanking()
            MatchHistory( navController = navController)
        }

    }
}

// AVT NAME TAG SUMMONER
@Composable
fun profileHeader(
    navController: NavController?
){
    Column (
        modifier = Modifier

            .fillMaxWidth()
            // Profile champion art
            .paint(
                painterResource(R.drawable.quin_profile),
                contentScale = ContentScale.FillBounds
            ),

    ){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            IconButton(
                onClick = {
                    navController?.navigate(Routes.summoner_Search)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.fachevronleft),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp),
                    tint = Color.White

                )

            }
            IconButton(
                modifier = Modifier.padding( end = 10.dp),
                onClick = {
                   // Yêu thích
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.faheartcircleplus),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp),
                    tint = Color.White
                )

            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 15.dp, end = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            // Avt
            Image(
                painter = painterResource(R.drawable.fk2024icon),
                contentDescription = null,
                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(5.dp))
            )
            // Profile Name + Tag
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ){
                Text(
                    text = "thanhlienminh",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "#VN2",
                    color = tagGrayColor,
                    style = MaterialTheme.typography.titleSmall
                )
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 15.dp, end = 15.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
           // Update button
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF42A5F5)  // Blue color for Update button
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(35.dp)
            ) {
                Text(text = "Update", color = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))

            //Ingame button
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE0E0E0)  // Grey color for Ingame button
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(35.dp)
            ) {
                Text(text = "Ingame", color = Color.Gray)
            }
        }


    }
}




@Composable
fun profileRanking(
    // Truyền data về ranking ở đây
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){

        // Rank đơn đôi

        Box(
            modifier = Modifier
                .border(width = 1.5.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
                .padding(top = 8.dp, start = 8.dp, bottom = 8.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(0.475f),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(
                    painter = painterResource(R.drawable.season2023_diamond),
                    contentDescription = null,
                    modifier = Modifier.size(45.dp)
                )
                Column (
                    modifier = Modifier
                        .padding(start = 5.dp),
                    verticalArrangement = Arrangement.Center,
                    ){
                    Text(
                        text = stringResource(R.string.rank_solo),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        modifier = Modifier
                            .background(color = rakingTitleColor)
                            .padding(2.dp)
                    )
                    Text(
                        text = "Diamond 4",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "58LP",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                    )
                    Text(
                        text = "40Wíns 40Loses",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                    )
                }
            }
        }

        // Rank linh hoạt

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.5.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
                .padding(8.dp),
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(
                    painter = painterResource(R.drawable.season2023_diamond),
                    contentDescription = null,
                    modifier = Modifier.size(45.dp)
                )
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp),
                    verticalArrangement = Arrangement.Center,

                    ){
                    Text(
                        text = stringResource(R.string.rank_flex),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        modifier = Modifier
                            .background(color = rakingTitleColor)
                            .padding(2.dp)
                    )
                    Text(
                        text = "Diamond 4",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "58LP",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                    )
                    Text(
                        text = "40Wíns 40Loses",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                    )
                }
            }
        }

    }
}

@Composable
fun MatchHistory(
    navController: NavController? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF0F0F0))
            .padding(top = 10.dp)
    ) {

        Text(
            text = stringResource(R.string.match_History),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 15.dp, end = 15.dp)
        )

        (1..10).forEach { index ->
            MatchHistoryItem(
                win = index % 2 == 0,
                kda = "10 / 2 / 8",
                kp = "K/P 50%",
                rank = "Xếp hạng đơn/đôi",
                timeAgo = "30 phút trước",
                navController = navController
            )
        }
    }
}

@Composable
fun MatchHistoryItem(
    win: Boolean,
    kda: String,
    kp: String,
    rank: String,
    timeAgo: String,
    navController: NavController? = null
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .topBorder(1.dp, Color.Gray)
            .bottomBorder(1.dp, Color.Gray)
            .clickable {
                navController?.navigate(Routes.matchDetail)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(1f)
                .background(if (win) winColor else loseColor)
                .padding(
                    if (win) {
                        PaddingValues(
                            start = 8.5.dp, end = 8.dp, top = 32.dp, bottom = 32.dp
                        )
                    } else {
                        PaddingValues(
                            start = 10.dp, end = 10.dp, top = 32.dp, bottom = 32.dp
                        )
                    }
                )

        ){
            Text(
                text = if(win) "W" else "L",
                style = MaterialTheme.typography.titleSmall,
                color = Color.White

            )
        }
        Row(
            modifier = Modifier.padding(8.dp),
        ) {
            Column {
                // Tướng, spell và kda điểm KP
                
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    // Champ
                    Image(
                        painter = painterResource(R.drawable.samira),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    //Spell
                    SummonerSpells()
                    // Runes
                    SummonerRunes()
                    // KP
                    Column {
                        Text(text = kda, style = MaterialTheme.typography.bodyMedium)
                        Text(text = kp, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                }

                // truyền data item vào đây

                ItemRow()
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.Start) {
                Text(text = rank, style = MaterialTheme.typography.bodyMedium)
                Text(text = timeAgo, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }

}

@Composable
fun ItemRow() {
    Row(
        modifier = Modifier.padding(top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Item
        repeat(6) { index ->
            Image(
                painter = painterResource(when (index) {
                    0 -> R.drawable.vo_cuc
                    1 -> R.drawable.vo_cuc
                    2 -> R.drawable.vo_cuc
                    3 -> R.drawable.loi_nhac
                    4 -> R.drawable.loi_nhac
                    5 -> R.drawable.vo_cuc
                    else -> R.drawable.item_null // nếu không có item
                }),
                contentDescription = null,
                modifier = Modifier.size(20.dp).clip(RoundedCornerShape(5.dp))
            )
        }
        Image(
            painter = painterResource(R.drawable.thau_kinh),
            contentDescription = null,
            modifier = Modifier.size(20.dp).clip(RoundedCornerShape(25.dp))
        )
    }
}

@Composable
fun SummonerSpells(

) {
    Column(
        modifier = Modifier.padding(end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // D
        Image(
            painter = painterResource(R.drawable.spell_flash),  // Replace with your spell image resource
            contentDescription = null,
            modifier = Modifier.size(15.dp).clip(RoundedCornerShape(12.dp))
        )
        // F
        Image(
            painter = painterResource(R.drawable.spell_ignite),  // Replace with your spell image resource
            contentDescription = null,
            modifier = Modifier.size(15.dp).clip(RoundedCornerShape(12.dp))
        )
    }
}

@Composable
fun SummonerRunes(

) {
    Column(
        modifier = Modifier.padding(end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Primary path
        Image(
            painter = painterResource(R.drawable.sstc),  // Replace with your spell image resource
            contentDescription = null,
            modifier = Modifier.size(18.dp).clip(RoundedCornerShape(12.dp))
        )
        // Secondary path
        Image(
            painter = painterResource(R.drawable.cam_hung),  // Replace with your spell image resource
            contentDescription = null,
            modifier = Modifier.size(10.dp).clip(RoundedCornerShape(12.dp))
        )
    }
}
