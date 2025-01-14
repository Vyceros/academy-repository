package com.example.baseandroidproject.fragments

import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.R
import com.example.baseandroidproject.data.Card
import com.example.baseandroidproject.data.CardType
import com.example.baseandroidproject.data.CardViewModel
import com.example.baseandroidproject.databinding.FragmentNewCardBinding
import com.example.baseandroidproject.utils.setExpiryDate
import com.example.baseandroidproject.utils.validateCardNumber
import com.example.baseandroidproject.utils.validateCvv
import com.example.baseandroidproject.utils.validateName


class NewCardFragment : BaseFragment<FragmentNewCardBinding>(FragmentNewCardBinding::inflate) {

    private var expiryDate = setExpiryDate()

    private val viewModel: CardViewModel by activityViewModels()



    override fun setup() {
        listeners()
        initializeCard()
        liveChangeData()
    }

    private fun listeners() {
        binding.btnGoBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.rgCardType.setOnCheckedChangeListener { _, _ ->
            changeCardBackground()
        }

        binding.btnAddCard.setOnClickListener {
            initializeCard()
        }

    }

    //Change background of the card based on which radio button is selected
    private fun changeCardBackground() {
        with(binding) {
            when {
                rbVisa.isChecked -> {
                    ivCard.ivCard.setImageResource(R.drawable.visacard)
                }

                rbMastercard.isChecked -> {
                    ivCard.ivCard.setImageResource(R.drawable.mastercard)
                }
            }
        }
    }

    // Live update text as user types it
    private fun liveChangeData() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.ivCard.tvHolderName.text = s?.toString()
            }

        })

        binding.etCardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.ivCard.tvCardNumber.text = s?.toString()
            }

        })

        binding.etExpiryDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.ivCard.tvValidDate.text = s?.toString()
            }

        })
    }

    private fun initializeCard() {
        binding.etExpiryDate.setText(expiryDate)
        if (validateInput()) {
            val card = Card(
                name = binding.etName.text.toString(),
                cardNumber = binding.etCardNumber.text.toString(),
                expiryDate = binding.etExpiryDate.text.toString(),
                cvv = binding.etCvv.text.toString(),
                cardType = with(binding) {
                    when {
                        rbVisa.isChecked -> CardType.Visa
                        rbMastercard.isChecked -> CardType.Mastercard
                        else -> CardType.Mastercard
                    }
                }
            )
            viewModel.addCard(card)
            findNavController().popBackStack()
        }


    }

    private fun validateInput(): Boolean {
        if (!validateCardNumber(binding.etCardNumber.text.toString())) {
            binding.etCardNumber.error = "Required, 16 characters"
            return false
        }

        if (!validateName(binding.etName.text.toString())) {
            binding.etName.error = "Required, only letters"
            return false
        }

        if (!validateCvv(binding.etCvv.text.toString())) {
            binding.etCvv.error = "Required, 3 digits"
            return false
        }

        return true
    }

}