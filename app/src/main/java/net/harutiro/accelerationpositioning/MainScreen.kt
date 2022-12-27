package net.harutiro.accelerationpositioning

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import net.harutiro.accelerationpositioning.ui.theme.AccelerationPositioningTheme

@Composable
fun SensorHome(viewModel: MainViewModel) {

    AccelerationPositioningTheme {
        Column() {
            Button(
                onClick = {

                },
            ) {
                Text("取得開始")
            }

            Button(
                onClick = {
                    viewModel.sumClear()
                },
            ) {
                Text("リセット")
            }

            Text(viewModel.str.value)

            Text( "加速度" + viewModel.sum.value.toString())
        }
    }
}