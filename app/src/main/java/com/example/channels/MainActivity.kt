package com.example.channels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.channels.ui.theme.ChannelsTheme

class MainActivity : ComponentActivity() {
    private val myViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChannelsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(viewModel = myViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier,viewModel: MainViewModel) {
    val count by viewModel.data.collectAsState()
    Column {
        if (count != 3){
            Text(
                text = "Hello $count!",
                modifier = modifier
            )
        }else{
            Text(
                text = "Tasks end",
                modifier = modifier
            )
        }

        Button(onClick = { viewModel.channelTest() }, enabled = count != 3) {
            Text(text = "Start remote tasks")
        }
    }
}
