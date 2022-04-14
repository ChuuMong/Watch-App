package com.chuumong.watch.ui.main

import androidx.lifecycle.viewModelScope
import com.chuumong.watch.ui.BaseViewModel
import com.chuumong.watch.ui.ScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import java.text.SimpleDateFormat
import java.util.*


class MainViewModel : BaseViewModel<MainState, MainSideEffect>() {

    override fun createInitialState() = MainState(ScreenState.Loading, null, null)

    fun getTime() = intent {
        viewModelScope.launch {
            repeat(Int.MAX_VALUE) {
                reduce {
                    state.copy(
                        screenState = ScreenState.Success,
                        data = SimpleDateFormat(
                            "yyyy.MM.dd HH:mm:ss",
                            Locale.getDefault()
                        ).format(Date())
                    )
                }

                delay(10)
            }
        }
    }
}