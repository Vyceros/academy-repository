package com.example.baseandroidproject.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.data.Resource
import com.example.baseandroidproject.data.passcode.PasscodeCircle
import com.example.baseandroidproject.databinding.FragmentPasscodeBinding
import com.example.baseandroidproject.ui.dialpad.DialPadAdapter
import com.example.baseandroidproject.ui.passcode_circle.PasscodeCircleAdapter
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

    private val passcodeCircleAdapter by lazy {
        PasscodeCircleAdapter()
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

        binding.rvPasscodeCircles.apply {
            layoutManager = GridLayoutManager(requireContext(),4)
            adapter = passcodeCircleAdapter
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
        val circles = List(4) { position -> PasscodeCircle(fillState = position < inputSize) }
        passcodeCircleAdapter.submitList(circles)
    }

}