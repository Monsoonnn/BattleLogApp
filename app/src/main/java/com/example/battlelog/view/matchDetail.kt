package com.example.battlelog.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
fun MatchDetailPreview(){
    BatteLogTheme {
        MatchDetail()
    }
}


@Composable
fun MatchDetail(
    navController: NavController? = null,
    isWin: Boolean? = true,
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
                    .background(
                        if (isWin!!) winColor else loseColor
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { navController?.navigateUp() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.fachevronleft),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = Color.White
                    )
                }
                Text(
                    text = (
                            if (isWin) "WIN" else "LOSE"
                            ),
                    modifier = Modifier.fillMaxWidth().offset(x = -15.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Team win
            MatchDetailSection(resultText = "WIN (red)", isTeamWin = true)

            // Team lose
            MatchDetailSection(resultText = "LOSE (blue)", isTeamWin = false)
        }
    }
}

@Composable
fun MatchDetailSection(
    resultText: String,
    isTeamWin: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .then(
                Modifier.topBorder(
                    2.dp,
                    if (!isTeamWin) loseColor else Color.Transparent
                )
            )
            .padding(start = 15.dp, end = 15.dp)

    ) {
        Text(
            text = resultText,
            style = MaterialTheme.typography.titleMedium,
            color = if (isTeamWin) winColor else loseColor,
            modifier = Modifier.padding(top = 10.dp)
        )
        // Data team
        List(5) {
            MatchDetailItem(summonerName = "thanhlienminh", isTeamWin = isTeamWin)
        }
    }
}

@Composable
fun MatchDetailItem(summonerName: String, isTeamWin: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .background(Color.White)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avt
        Image(
            painter = painterResource(R.drawable.samira),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )

        Spacer(Modifier.width(5.dp))
        // Phép bổ trợ từ profile
        SummonerSpells()

        //Bảng ngọc từ profile

        SummonerRunes()

        // Tên summoner + tag + kda + kp
        Column(modifier = Modifier.weight(0.8f)) {
            Text(text = "$summonerName #VN2", style = MaterialTheme.typography.bodySmall)
            Row {
                Text(text = "10 / 2 / 8", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                Spacer(Modifier.width(3.dp))
                Text(text = "9:00", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            }
        }

        Spacer(Modifier.width(5.dp))
        // Item từ profile
        ItemRow()
    }
}