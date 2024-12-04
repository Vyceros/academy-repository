package com.example.fourthassignment

import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import com.example.fourthassignment.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val validations = InputValidations()

        binding.btnSave.setOnClickListener {
            if (!validations.validateAllInputs()) {
                Toast.makeText(baseContext, R.string.empty_input_warning, Toast.LENGTH_SHORT).show()
            }
            if (!validations.isEmailValid()) {

                Toast.makeText(baseContext, R.string.email_is_invalid, Toast.LENGTH_SHORT).show()
            }

            if (validations.isUserNameValid()) {
                Toast.makeText(baseContext, R.string.user_name_must_be_at_least_10_letters, Toast.LENGTH_SHORT).show()
            }

        }
        binding.btnClear.setOnLongClickListener {
            extracted()
            Toast.makeText(baseContext, "Clearing everything", Toast.LENGTH_SHORT).show()
            true
        }

    }

}

private fun extracted() {
    for (component in listOfComponents) {
        component.text.let { text ->
            text?.clear()
        }
    }
}







