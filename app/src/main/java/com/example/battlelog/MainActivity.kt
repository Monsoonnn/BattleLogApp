package com.example.battlelog

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.battlelog.ui.theme.BatteLogTheme
import com.example.battlelog.view.HomeHeader

import com.noahkohrs.riot.api.RiotApi
import com.noahkohrs.riot.api.values.Platform
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BatteLogTheme {
                HomeHeader(modifier = Modifier)
            }
        }
        // Fetch the summoner name after setting content
        viewModel.fetchSummonerName()
    }
}

// Updated ViewModel with LiveData
class MainViewModel : ViewModel() {
    private val riotApi = RiotApi(apiKey = "RGAPI-ae502d22-fde3-450c-bb1f-59517efbea18", Platform.VN2)
    private val dnsMonPuuid: String? = null
    private val _summonerName = MutableLiveData<String>()
    val summonerName: LiveData<String> = _summonerName

    fun fetchSummonerName() {
        viewModelScope.launch {
            try {
                val puuid = withContext(Dispatchers.IO) {
                    riotApi.account.getAccountByRiotId(DNS_MON.name, DNS_MON.tag).puuid
                }

                val name = withContext(Dispatchers.IO) {
                    riotApi.lol.summoner.getSummonerByPuuid(puuid).summonerLevel.toString()
                }

                _summonerName.value = name
            } catch (e: Exception) {
                // Handle exceptions such as network errors
                Log.e("MainViewModel", "An error occurred while fetching the summoner name: ${e.localizedMessage}")

                _summonerName.value = "Error"
            }
        }
    }
}

// Update the Player data class to prevent conflict
private data class CustomPlayer(val name: String, val tag: String)
private val DNS_MON = CustomPlayer("Mon", "Mon31")

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", // use the name parameter obtained from MainActivity
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BatteLogTheme {
        Greeting("")
    }
}

