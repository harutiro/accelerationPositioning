package net.harutiro.accelerationpositioning

import android.hardware.Sensor
import android.hardware.SensorEvent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

@Stable
class MainViewModel : ViewModel() {
//    viewModelの変数が変わったら、状態をComposeに教えてくれる。
//    https://zenn.dev/ko2ic/articles/3601dea3d35013
    private val _sensor: MutableState<Double> = mutableStateOf(0.0)
    val sensor: MutableState<Double> = _sensor

    private val _str: MutableState<String> = mutableStateOf("")
    val str: MutableState<String> = _str

    private val _sum: MutableState<Double> = mutableStateOf(0.0)
    val sum: MutableState<Double> = _sum



    fun increaseCount() {
        _sensor.value++
    }

    fun sensorChanged(event:SensorEvent){
        var sensorX: Float
        var sensorY: Float
        var sensorZ: Float
        // Remove the gravity contribution with the high-pass filter.
        if (event.sensor.type === Sensor.TYPE_LINEAR_ACCELERATION) {
            sensorX = event.values[0]
            sensorY = event.values[1]
            sensorZ = event.values[2]
            val strTmp = """加速度センサー
                         X: $sensorX
                         Y: $sensorY
                         Z: $sensorZ"""
            _str.value = strTmp

            _sum.value = sum.value + sensorY


        }
    }


    fun sumClear(){
        _sum.value = 0.0
    }

    /**
     * nは積分区間の分割数
     */

//            integrateByTrapezoid(100, 1, 2 , { x -> x * x } )
    fun integrateByTrapezoid(division: Int ,a:Int , b:Int ,f:(Double) -> Double): Double {


        val step = (b - a) / division.toDouble()
        var sumV = 0.0

        for (i in 0..division) {
            val x = a + step * i
            sumV += (f(x) + f(x + step)) * step / 2
        }

        return sumV
    }


}