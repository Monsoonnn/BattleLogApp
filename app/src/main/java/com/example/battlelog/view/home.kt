package com.example.battlelog.view



import android.view.Surface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.battlelog.Greeting
import com.example.battlelog.R
import com.example.battlelog.ui.theme.BatteLogTheme


@Composable
fun HomeHeader(
    modifier: Modifier
){
    Row(
        modifier = Modifier.padding(top = 30.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
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
        Icon(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
        )
    }

}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    BatteLogTheme {
        Surface(
            modifier = Modifier.fillMaxSize().padding(10.dp)
        ){
            HomeHeader(modifier = Modifier)

        }
    }
}

@Composable
fun ServerChoose() {
    val servers = listOf("VN", "KR", "NA", "EUW", "BR", "EUN", "RU")
    var expanded by remember { mutableStateOf(false) }
    var selectedServer by remember { mutableStateOf(servers[0]) }

    Box(modifier = Modifier.padding(10.dp).clickable { expanded = true }
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

