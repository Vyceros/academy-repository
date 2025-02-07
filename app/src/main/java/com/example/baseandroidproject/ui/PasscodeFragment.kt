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
                    updatePasscodeCircles(state.currentInput.size)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.authenticateState.collectLatest { result ->
                    handleResult(result)
                }
            }
        }
    }

    private fun handleResult(result: Resource) {
        when (result) {
            is Resource.Default -> {}
            is Resource.Success -> {
                Snackbar.make(binding.root, result.message, Snackbar.LENGTH_SHORT).show()
            }

            is Resource.Error -> {
                Snackbar.make(binding.root, result.message, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun updatePasscodeCircles(inputSize: Int) {
        with(binding) {
            ivPasscodeFirst.setImageResource(changePasscodeColor(inputSize >= 1))
            ivPasscodeSecond.setImageResource(changePasscodeColor(inputSize >= 2))
            ivPasscodeThird.setImageResource(changePasscodeColor(inputSize >= 3))
            ivPasscodeFourth.setImageResource(changePasscodeColor(inputSize >= 4))
        }
    }

    private fun changePasscodeColor(isFilled: Boolean): Int {
        return if (isFilled) {
            R.drawable.passcode_circle_filled
        } else {
            R.drawable.passcode_circle
        }
    }


}