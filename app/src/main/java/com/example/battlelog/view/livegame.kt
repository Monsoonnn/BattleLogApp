package com.example.battlelog.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.battlelog.R
import com.example.battlelog.ui.theme.BatteLogTheme
import com.example.battlelog.ui.theme.loseColor
import com.example.battlelog.ui.theme.topBorder
import com.example.battlelog.ui.theme.winColor

@Preview(showBackground = true)
@Composable
fun LiveGamePreview(){
    BatteLogTheme {
        LiveGameView()
    }
}




@Composable
fun LiveGameView(
    navController: NavController? = null
) {

    Scaffold(
        topBar = {

            Column(
                modifier = Modifier.padding(top = 50.dp, start = 10.dp, end = 10.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.angle_left_solid),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            navController?.navigateUp()
                        }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Live game",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 15.dp)
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

            // Blue
            LiveMatchDetailSection(team = "Blue", isTeamWin = true)

            // Red
            LiveMatchDetailSection(team = "Red", isTeamWin = false)

        }

    }
}

@Composable
fun LiveMatchDetailSection(
    team: String,
    isTeamWin: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .then(
                Modifier.topBorder(
                    3.dp,
                    if (!isTeamWin) loseColor else winColor
                )
            )
            .padding(start = 15.dp, end = 15.dp)

    ) {
        Text(
            text = team,
            style = MaterialTheme.typography.titleMedium,
            color = if (isTeamWin) winColor else loseColor,
            modifier = Modifier.padding(top = 10.dp)
        )
        // Data team
        List(5) {
            LiveDetailItem(summonerName = "thanhlienminh", tag = "VN2", isTeamWin = isTeamWin)
        }
    }
}

@Composable
fun LiveDetailItem(summonerName: String, tag: String, isTeamWin: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .background(Color.White)
            .padding(8.dp),
    ) {
        // Avt
        Image(
            painter = painterResource(R.drawable.samira),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )

        Spacer(Modifier.width(5.dp))
        // Phép bổ trợ
        SummonerSpells()

        //Bảng ngọc

        SummonerRunes()

        // Tên summoner + tag + kda + kp
        Column(modifier = Modifier.weight(0.8f)) {
            Text(text = "$summonerName #VN2", style = MaterialTheme.typography.bodySmall)
            Text(text = "#$tag", style = MaterialTheme.typography.bodySmall)
        }

        Spacer(Modifier.width(5.dp))
    }
}

