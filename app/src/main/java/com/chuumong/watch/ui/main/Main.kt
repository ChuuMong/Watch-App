package com.chuumong.watch.ui.main

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.chuumong.watch.ui.ScreenState
import com.chuumong.watch.ui.common.LoadingView
import com.chuumong.watch.ui.theme.Typography


@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {

    viewModel.getTime()

    val stateFlow = viewModel.uiState()
    val state by stateFlow.collectAsState(initial = viewModel.createInitialState())
    when (state.screenState) {
        ScreenState.Loading -> {
            LoadingView(modifier = Modifier.fillMaxSize())
        }
        ScreenState.Success -> {
            state.data?.let {
                MainWatch(it)
            }
        }
        else -> {
            state.error?.let {
                Toast.makeText(LocalContext.current, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun MainWatch(time: String) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val timeText = createRef()

        Text(
            modifier = Modifier
                .constrainAs(timeText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            text = time,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            style = Typography.body2,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainWatch("11:11")
}
