package com.example.baseandroidproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.ui.dialpad.DialPad
import com.example.baseandroidproject.ui.dialpad.DialPadType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PasscodeViewModel : ViewModel() {
    private val _passCodeState = MutableStateFlow(PasscodeState())
    val passCodeState = _passCodeState.asStateFlow()

    private val _authenticateState = MutableStateFlow<Resource>(Resource.Default)
    val authenticateState = _authenticateState.asStateFlow()

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
            DialPadType.FingerPrint -> return
        }
    }


    private fun checkForInput(input: String) {
        val currentInputState = _passCodeState.value

        _authenticateState.value = Resource.Default

        if (currentInputState.currentInput.size >= currentInputState.maxLength) {
            clearInput()
            return
        }

        val updatedInput = currentInputState.currentInput + input
        val isFilled = updatedInput.size == currentInputState.maxLength

        if (isFilled) {
            validatePasscode(updatedInput)
        } else {
            updatePasscodeState(updatedInput)
        }
    }

    private fun validatePasscode(input: List<String?>) {
        viewModelScope.launch {
            _authenticateState.value = Resource.Default

            val passcode = input.joinToString("")
            _authenticateState.value = if (passcode == PASSCODE) {
                Resource.Success("Success")
            } else {
                Resource.Error("Invalid passcode")
            }

            clearInput()
        }
    }

    private fun updatePasscodeState(input: List<String?>) {
        _passCodeState.value = _passCodeState.value.copy(
            currentInput = input,
            isFilledOut = false
        )
    }

    private fun removeLastInput() {
        val currentInputState = _passCodeState.value
        val updatedInput = currentInputState.currentInput.dropLast(1)
        updatePasscodeState(updatedInput)
        _authenticateState.value = Resource.Default
    }

    fun generateDialPad() = dialPadButtons

    private fun clearInput() {
        _passCodeState.value = PasscodeState()
    }
}
