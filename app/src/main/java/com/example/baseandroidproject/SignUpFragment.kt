package com.example.baseandroidproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.baseandroidproject.databinding.FragmentSignUpBinding
import com.example.baseandroidproject.users.User
import com.example.baseandroidproject.users.UserStorage
import com.google.android.material.snackbar.Snackbar

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.btnCreateUser.setOnClickListener {
            addUser()
        }
    }

    private fun addUser() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val birthday = binding.etBirthDate.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val address = binding.etAddress.text.toString().trim()



        if (firstName.isEmpty() || email.isEmpty() || lastName.isEmpty() || birthday.isEmpty() || address.isEmpty()) {
            Snackbar.make(
                binding.root,
                getString(R.string.fields_are_empty_snackbar), Snackbar.LENGTH_SHORT
            ).show()
        }

        val formatDate = formatDate(birthday)
        val user = User(
            id = 1,
            firstName, lastName, formatDate, address, email
        )

        val isCreated = UserStorage.addUser(user)

        if (isCreated) {
            Toast.makeText(requireContext(), "User created: $user", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "User already exists", Toast.LENGTH_LONG).show()
        }

    }

    private fun formatDate(date: String): String {
        if (date.all { it.isDigit() }) {
            val daysPlace = date.substring(0, 2)
            val monthPlace = date.substring(2, 4).toInt()
            val year = date.substring(4, 8)
            val monthStringRepresent = when (monthPlace) {
                1 -> "იანვარი"
                2 -> "თებერვალი"
                3 -> "მარტი"
                4 -> "აპრილი"
                5 -> "მაისი"
                6 -> "ივნისი"
                7 -> "ივლისი"
                8 -> "აგვისტო"
                9 -> "სექტემბერი"
                10 -> "ოქტომბერი"
                11 -> "ნოემბერი"
                12 -> "დეკემბერი"
                else -> {"Not valid format, enter DAY/MONTH/YEAR"}
            }
            return "$daysPlace $monthStringRepresent $year"
        }
        return ""

    }
}