package com.example.baseandroidproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baseandroidproject.R
import com.example.baseandroidproject.adapter.GameAdapter
import com.example.baseandroidproject.databinding.FragmentGameBinding
import com.google.android.material.snackbar.Snackbar

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var size = 3
    private var startPlayer = "X"
    private lateinit var boardCheckTracking: Array<Array<String?>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        size = arguments?.getInt("gameSize", 3) ?: 3
        boardCheckTracking = Array(size) { arrayOfNulls<String>(size) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        boardGameSetup()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun boardGameSetup() {
        val adapter = GameAdapter(size) { row, col, button ->
            onButtonClick(row, col, button)
        }

        with(binding.gameBoardRecyclerView) {
            layoutManager = GridLayoutManager(context, size)
            this.adapter = adapter

        }
    }

    private fun onButtonClick(row: Int, col: Int, button: ImageButton) {
        if (!button.isClickable) return

        if (startPlayer == "X") {
            button.setBackgroundResource(R.drawable.x)
            boardCheckTracking[row][col] = "X"
        } else {
            button.setBackgroundResource(R.drawable.o)
            boardCheckTracking[row][col] = "O"
        }

        button.isClickable = false

        if (checkWinner(row, col)) {
            showWinner(startPlayer)
        } else {
            startPlayer = if (startPlayer == "X") "O" else "X"
        }
    }

    private fun checkWinner(row: Int, col: Int): Boolean {
        if ((0 until size).all { boardCheckTracking[row][it] == startPlayer }) {
            return true
        }

        if ((0 until size).all { boardCheckTracking[it][col] == startPlayer }) {
            return true
        }

        if (row == col && (0 until size).all { boardCheckTracking[it][it] == startPlayer }) {
            return true
        }

        if (row + col == size - 1 && (0 until size).all { boardCheckTracking[it][size - it - 1] == startPlayer }) {
            return true
        }

        return false
    }

    private fun showWinner(winner: String) {
        Snackbar.make(binding.root, "$winner won", Snackbar.LENGTH_SHORT).show()
    }
}

