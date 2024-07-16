package com.example.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TestRepository {

    fun backgroundTask(channel: Channel<Int>, _data: MutableStateFlow<Int>) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1500)
            channel.send(_data.value+1)
        }
    }
}