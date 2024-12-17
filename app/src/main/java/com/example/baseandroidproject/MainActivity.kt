package com.example.baseandroidproject


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidproject.userInfo.User
import com.example.baseandroidproject.userInfo.UserStorage
import com.example.baseandroidproject.databinding.ActivityMainBinding
import com.example.baseandroidproject.helper.changeDisplays
import com.example.baseandroidproject.helper.displaySnackbarMessage
import com.example.baseandroidproject.userInfo.validateAge
import com.example.baseandroidproject.userInfo.validateEmail
import com.example.baseandroidproject.userInfo.validateName


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userStorage: UserStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userStorage = UserStorage()
        init()
    }


    private fun init() {
        binding.btnAddUser.setOnClickListener {
            onRegisterUserButton()
        }

        binding.btnUpdateUser.setOnClickListener {
            onUpdateUserButton()
        }

        binding.btnUpdateUserScreen.setOnClickListener {
            updateUserLogic()
        }

        binding.btnGoBack.setOnClickListener {
            returnFromUpdateScreen()
        }

        binding.btnRemoveUserScreen.setOnClickListener {
            onRemoveUserButton()
        }

        binding.btnRemoveUser.setOnClickListener {
            removeUserLogic()
        }

        binding.btnRemoveGoBack.setOnClickListener {
            returnFromRemoveScreen()
        }
    }

    private fun onUpdateUserButton() {
        changeDisplays(binding.originalContainer, binding.updateContainer)
    }

    private fun updateUserLogic() {
        val firstName = binding.etUpdateFirstName.text.toString().trim()
        val lastName = binding.etUpdateLastName.text.toString().trim()
        val email = binding.etUpdateEmail.text.toString().trim()
        val age = binding.etUpdateAge.text.toString().trim()

        if (!validationChecks(
                firstName = firstName,
                lastName = lastName,
                email = email,
                age = age)) return

        val updateUser = User(
            firstName, lastName, email, age
        )

        if (userStorage.updateUser(binding.etUpdateUserSearch.text.toString(), updateUser)) {
            displaySnackbarMessage(
                binding.root,
                getString(R.string.user_updated_successfully_snackbar),
                true
            )
        } else {
            displaySnackbarMessage(
                binding.root,
                getString(R.string.user_update_failed_snackbar),
                false
            )
        }
    }

    private fun onRegisterUserButton() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val age = binding.etAge.text.toString().trim()

        if (!validationChecks(
                firstName = firstName,
                lastName = lastName,
                email = email,
                age = age
            )
        ) return

        val user = User(
            firstName, lastName, email, age
        )
        if (userStorage.addUser(user)) {
            displaySnackbarMessage(
                binding.root,
                getString(R.string.user_created_successfully_snackbar),
                true
            )
        } else {
            displaySnackbarMessage(
                binding.root,
                getString(R.string.failed_user_already_exists),
                false
            )

        }
        binding.tvCount.text = userStorage.updateCount()
    }

    private fun returnFromUpdateScreen() {
        // return from update screen to original screen
        changeDisplays(binding.updateContainer, binding.originalContainer)
    }

    private fun returnFromRemoveScreen() {
        // return from remove screen to original screen
        changeDisplays(binding.removeContainer, binding.originalContainer)
    }

    private fun onRemoveUserButton() {
        changeDisplays(binding.originalContainer, binding.removeContainer)
    }

    private fun removeUserLogic() {
        val email = binding.etRemoveUserSearch.text.toString()

        if (userStorage.removeUser(email)) {
            displaySnackbarMessage(binding.root, "User deleted", true)
        } else {
            displaySnackbarMessage(binding.root, "User doesn't exist", false)
        }

        binding.tvCount.text = userStorage.updateCount()
    }

    private fun validationChecks(
        firstName: String,
        lastName: String,
        email: String,
        age: String
    ): Boolean {
        when {
            !validateName(firstName, lastName) -> {
                displaySnackbarMessage(
                    binding.root,
                    getString(R.string.first_name_last_name_validation_error), false
                )
                return false
            }

            !validateEmail(email) -> {
                displaySnackbarMessage(
                    binding.root,
                    getString(R.string.wrong_format_enter_email), false
                )
                return false
            }

            !validateAge(age) -> {
                displaySnackbarMessage(
                    binding.root, getString(R.string.age_must_be_positive_number), false
                )
                return false
            }
        }
        return true
    }
}