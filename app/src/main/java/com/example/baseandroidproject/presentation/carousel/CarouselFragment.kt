package com.example.baseandroidproject.presentation.carousel

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.baseandroidproject.data.resource.Resource
import com.example.baseandroidproject.databinding.FragmentCarouselBinding
import com.example.baseandroidproject.presentation.base.BaseFragment
import com.example.baseandroidproject.presentation.carousel.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class CarouselFragment : BaseFragment<FragmentCarouselBinding>(FragmentCarouselBinding::inflate) {

    private val viewModel: CarouselViewModel by viewModels()
    private lateinit var carouselAdapter: ViewPagerAdapter
    override fun setup() {
        setupViewPager()
        observe()
    }


    private fun setupViewPager() {
        carouselAdapter = ViewPagerAdapter()

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }



        binding.viewPager.apply {
            adapter = carouselAdapter
            setPageTransformer(transformer)
            offscreenPageLimit = 3
            clipToPadding=false
            clipChildren=false
            setPadding(100,0,100,0)
        }
    }


        private fun observe() {
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.images.collect { images ->
                        when (images) {
                            is Resource.Error -> binding.progressBar.isVisible = false
                            is Resource.Loading -> {
                                binding.progressBar.isVisible = true
                            }

                            is Resource.Success -> {
                                images.data?.let {
                                    carouselAdapter.submitList(it)
                                }
                                binding.progressBar.isVisible = false
                            }
                        }
                    }
                }
            }
        }

    }
