package com.example.channels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val testRepository = TestRepository()
    private val _data = MutableStateFlow(0)
    val data: StateFlow<Int> get() = _data


    fun channelTest(){
        val channel = Channel<Int>()

        viewModelScope.launch {
            channel.consumeEach {
                _data.value = it
                Log.e("TAG",it.toString())
            }
        }

        viewModelScope.launch {
            viewModelScope.launch {
                delay(600)
                var task = _data.value
                channel.send(task+1)
            }

            viewModelScope.launch {
                delay(1000)
                var task = _data.value
                channel.send(task+1)
            }

            testRepository.backgroundTask(channel,_data)
        }
    }
}