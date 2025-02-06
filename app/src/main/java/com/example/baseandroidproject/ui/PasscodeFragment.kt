package com.example.baseandroidproject.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baseandroidproject.R
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.databinding.FragmentPasscodeBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PasscodeFragment : BaseFragment<FragmentPasscodeBinding>(FragmentPasscodeBinding::inflate) {
    private val viewModel by viewModels<PasscodeViewModel>()

    private val dialPadAdapter by lazy {
        DialPadAdapter(
            buttons = viewModel.generateDialPad(),
            onItemClick = { type -> viewModel.handleDialPadClick(type.type) }
        )
    }

    override fun setup() {
        setupRecyclerView()
        observe()
    }

    private fun setupRecyclerView() {
        binding.recycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = dialPadAdapter
        }
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.passCodeState.collectLatest { state ->
                    updatePasscodeDisplay(state.currentInput.size)

                    if (state.isFilledOut) {
                        Snackbar.make(binding.root,"FILLED", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun updatePasscodeDisplay(inputSize: Int) {
        with(binding) {
            ivPasscodeFirst.setImageResource(getCircleResource(inputSize >= 1))
            ivPasscodeSecond.setImageResource(getCircleResource(inputSize >= 2))
            ivPasscodeThird.setImageResource(getCircleResource(inputSize >= 3))
            ivPasscodeFourth.setImageResource(getCircleResource(inputSize >= 4))
        }
    }

    private fun getCircleResource(isFilled: Boolean): Int {
        return if (isFilled) {
            R.drawable.passcode_circle_filled
        } else {
            R.drawable.passcode_circle
        }
    }


}