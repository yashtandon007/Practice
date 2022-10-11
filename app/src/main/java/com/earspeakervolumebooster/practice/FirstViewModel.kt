package com.earspeakervolumebooster.practice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


data class User(
    val status: String
)

class FirstViewModel : ViewModel() {

    private val _state = MutableStateFlow<List<User>>(emptyList())
    val state: StateFlow<List<User>> = _state.asStateFlow()


    init {
        var list = mutableListOf<User>()
        getData()
            .flatMapConcat {
                list.add(it)
                getData()
            }
            .launchIn(viewModelScope)


    }

}


fun getData():Flow<User>{

    return flow {
        emit(User(1))
    }
}

