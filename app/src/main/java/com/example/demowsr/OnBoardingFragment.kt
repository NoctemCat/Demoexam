package com.example.demowsr

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.demowsr.databinding.FragmentOnBoardingBinding
import kotlin.math.abs

class OnBoardingFragment : Fragment() {
    private lateinit var binding :FragmentOnBoardingBinding

    private lateinit var obBoardingCollectionAdapter : OnBoardingCollectionAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        obBoardingCollectionAdapter = OnBoardingCollectionAdapter(this)
        viewPager = view.findViewById(R.id.on_boarding_pager)
        viewPager.adapter = obBoardingCollectionAdapter

        viewPager.registerOnPageChangeCallback(OnPageChanged())

        binding.ivBoarding1.setOnClickListener {
            viewPager.currentItem = 0
        }

        binding.ivBoarding2.setOnClickListener {
            viewPager.currentItem = 1
        }
    }

    inner class OnPageChanged : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            if(position == 0){
                binding.ivBoarding1.setImageResource(R.drawable.circle_active)
                binding.ivBoarding2.setImageResource(R.drawable.circle)
            }else{
                binding.ivBoarding1.setImageResource(R.drawable.circle)
                binding.ivBoarding2.setImageResource(R.drawable.circle_active)
            }
        }
    }

    companion object {
        fun newInstance() =
            OnBoardingFragment()
    }
}


class OnBoardingCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 2;
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = if (position == 0){
            OnBoardingScreen1Fragment.newInstance()
        }else{
            OnBoardingScreen2Fragment.newInstance()
        }
        return fragment
    }
}

class OnBoardingScreen1Fragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_on_boarding_screen_1, container, false)
    }

    companion object {
        fun newInstance() =
            OnBoardingScreen1Fragment()
    }
}

class OnBoardingScreen2Fragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_on_boarding_screen_2, container, false)
    }

    companion object {
        fun newInstance() =
            OnBoardingScreen2Fragment()
    }
}