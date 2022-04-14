package com.chuumong.watch.ui.main

import com.chuumong.watch.ui.ScreenState


data class MainState(
    val screenState: ScreenState,
    val data: String?,
    val error: Throwable?
)