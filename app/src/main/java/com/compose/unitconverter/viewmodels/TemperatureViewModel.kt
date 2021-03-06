package com.compose.unitconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.compose.unitconverter.R
import java.lang.NumberFormatException

class TemperatureViewModel : ViewModel() {
    private val _scale: MutableLiveData<Int> = MutableLiveData(R.string.celsius)

    val scale: LiveData<Int>
        get() = _scale

    fun setScale(value: Int) {
        _scale.value = value
    }

    private val _temperature: MutableLiveData<String> = MutableLiveData("")

    val temperature: LiveData<String>
        get() = _temperature

    fun getTemperatureAsFloat(): Float = (_temperature.value?: "")?.let { value ->
            return try {
                value.toFloat()
            } catch (e: NumberFormatException) {
                Float.NaN
            }
        }


    fun setTemperature(value: String) {
        _temperature.value = value
    }

    fun convert() =
        getTemperatureAsFloat().let { value ->
            if (!value.isNaN())
                if (_scale.value == R.string.celsius)
                    (value * 1.8F) + 32F
                else
                    (value - 32F) / 1.8F
            else
                Float.NaN
        }


}