package com.example.myapplication

import GameViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ActivityMainBinding

import com.example.myapplication.R

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.diceValues.observe(this, Observer { diceValues ->
            updateDiceImages(diceValues)
        })

        binding.startButton.setOnClickListener {
            viewModel.startRollingDice()
        }

        binding.stopButton.setOnClickListener {
            viewModel.stopRollingDice()
        }
    }

    private fun updateDiceImages(diceValues: List<Int>) {
        val diceImageViews = listOf(
            binding.dice1,
            binding.dice2,
            binding.dice3,
            binding.dice4,
            binding.dice5
        )

        diceValues.forEachIndexed { index, value ->
            diceImageViews[index].setImageResource(getDiceImage(value))
        }
    }

    private fun getDiceImage(diceValue: Int): Int {
        return when (diceValue) {
            1 -> R.drawable.one
            2 -> R.drawable.two
            3 -> R.drawable.three
            4 -> R.drawable.four
            5 -> R.drawable.five
            6 -> R.drawable.six
            else -> R.drawable.one
        }
    }
}
