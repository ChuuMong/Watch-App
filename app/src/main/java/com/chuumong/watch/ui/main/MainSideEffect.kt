package com.chuumong.watch.ui.main


sealed class MainSideEffect {

    object TimeParsingError : MainSideEffect()
}