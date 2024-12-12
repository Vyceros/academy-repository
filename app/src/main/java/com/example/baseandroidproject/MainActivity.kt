package com.example.baseandroidproject


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTextsToViews()


    }



    private fun setTextsToViews()
    {
        binding.tvAndesMountain.text = getString(R.string.andes_mountain)
        binding.tvSouthAmerica.text = getString(R.string.south_america_tv)

        binding.tvPrice.text = getString(R.string.tv_price_label)
        binding.tvPriceNumber.text = getString(R.string.tv_price_number_value)

        binding.tvOverview.text = getString(R.string.tv_overview_text)
        binding.tvDetails.text = getString(R.string.tv_details_text)

        binding.tvEightHours.text = getString(R.string.eight_hours_text)
        binding.tvCelsiusTemp.text = getString(R.string.tv_celsius_temp)
        binding.tvStars.text = getString(R.string.tv_stars_text)

        binding.tvDescription.text = getString(R.string.tv_description_text)
        binding.btnBookNow.text = getString(R.string.btn_book_now_text)
    }
}