package com.example.baseandroidproject.ui

import androidx.lifecycle.ViewModel
import com.example.baseandroidproject.ui.dialpad.DialPad
import com.example.baseandroidproject.ui.dialpad.DialPadType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PasscodeViewModel : ViewModel() {
    private val _passCodeState = MutableStateFlow(PasscodeState())
    val passCodeState = _passCodeState.asStateFlow()

    companion object {
        private const val PASSCODE = "0934"

        private val dialPadButtons = listOf(
            DialPad(1, DialPadType.Number("1")),
            DialPad(2, DialPadType.Number("2")),
            DialPad(3, DialPadType.Number("3")),
            DialPad(4, DialPadType.Number("4")),
            DialPad(5, DialPadType.Number("5")),
            DialPad(6, DialPadType.Number("6")),
            DialPad(7, DialPadType.Number("7")),
            DialPad(8, DialPadType.Number("8")),
            DialPad(9, DialPadType.Number("9")),
            DialPad(10, DialPadType.FingerPrint),
            DialPad(11, DialPadType.Number("0")),
            DialPad(12, DialPadType.Backspace)
        )
    }

    fun handleDialPadClick(type: DialPadType) {
        when (type) {
            is DialPadType.Number -> checkForInput(type.value)
            DialPadType.Backspace -> removeLastInput()
            DialPadType.FingerPrint -> handleBiometric()
        }
    }

    private fun handleBiometric() {
    }

    fun checkForInput(input: String?) {
        val currentInputState = _passCodeState.value
        if (currentInputState.currentInput.size >= currentInputState.maxLength) {
            return
        }

        val updateInput = currentInputState.currentInput + input
        val isFilled = updateInput.size == currentInputState.maxLength

        val isValid = if (isFilled) {
            if (updateInput.joinToString("") == PASSCODE) {
                true
            } else {
                clearInput()
                return
            }
        } else {
            false
        }
        _passCodeState.value = currentInputState.copy(
            currentInput = updateInput,
            isFilledOut = isValid
        )
    }

    private fun removeLastInput() {
        val currentInputState = _passCodeState.value
        val updateInput = currentInputState.currentInput.dropLast(1)
        _passCodeState.value = currentInputState.copy(
            currentInput = updateInput,
            isFilledOut = false
        )
    }

    fun generateDialPad() = dialPadButtons

    private fun checkCode(): Boolean {
        val passCode = _passCodeState.value.currentInput.joinToString("")
        return passCode == PASSCODE
    }

    private fun clearInput() {
        _passCodeState.value = PasscodeState()
    }
}