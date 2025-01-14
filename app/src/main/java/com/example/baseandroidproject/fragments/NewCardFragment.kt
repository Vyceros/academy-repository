package com.example.baseandroidproject.fragments

import android.text.Editable
import android.text.TextWatcher
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.R
import com.example.baseandroidproject.databinding.FragmentNewCardBinding


class NewCardFragment : BaseFragment<FragmentNewCardBinding>(FragmentNewCardBinding::inflate) {


    override fun setup() {
        listeners()
        liveChangeData()
    }

    private fun listeners() {
        binding.btnGoBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.rgCardType.setOnCheckedChangeListener { _, _ ->
            changeCardBackground()
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

        binding.etExpiryDate.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.ivCard.tvValidDate.text = s?.toString()
            }

        })
    }

}