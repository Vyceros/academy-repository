package com.example.baseandroidproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseandroidproject.R
import com.example.baseandroidproject.databinding.FragmentConfigurationBinding
import com.google.android.material.snackbar.Snackbar

class ConfigurationFragment : Fragment() {
    private var _binding: FragmentConfigurationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfigurationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStartGame.setOnClickListener {
            startGame()
        }
    }

    private fun startGame() {
        val size = binding.etBoardSize.text.toString().toIntOrNull()

        if (size != null && size > 0) {
            val bundle = Bundle().apply {
                putInt("gameSize", size)
            }
            val gameFragment = GameFragment().apply {
                arguments = bundle
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, gameFragment, "Game")
                .addToBackStack("Game")
                .commit()
        } else {
            Snackbar.make(binding.root, "enter a valid board size", Snackbar.LENGTH_SHORT).show()
        }
    }
}