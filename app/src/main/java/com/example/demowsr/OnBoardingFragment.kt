package com.example.demowsr

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.demowsr.databinding.FragmentOnBoardingBinding
import com.example.demowsr.databinding.FragmentOnBoardingScreen2Binding

class OnBoardingFragment : Fragment() {
    private lateinit var binding :FragmentOnBoardingBinding

    private lateinit var obBoardingCollectionAdapter : OnBoardingCollectionAdapter
    private lateinit var viewPager: ViewPager2

    private var startPage:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            startPage = it.getInt("page")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        obBoardingCollectionAdapter = OnBoardingCollectionAdapter(this)
        viewPager = view.findViewById(R.id.on_boarding_pager)
        viewPager.adapter = obBoardingCollectionAdapter
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
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
        })

        viewPager.post {
            viewPager.currentItem = startPage
        }

        binding.ivBoarding1.setOnClickListener { viewPager.currentItem = 0 }
        binding.ivBoarding2.setOnClickListener { viewPager.currentItem = 1 }
    }

    companion object {
        fun newInstance(page: Int) =
            OnBoardingFragment().apply {
                arguments = Bundle().apply {
                    putInt("page", page)
                }
            }
    }
}

class OnBoardingCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 2
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
    private lateinit var binding: FragmentOnBoardingScreen2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingScreen2Binding.inflate(layoutInflater)

        if(!isNetworkAvailable(binding.root.context)){
            binding.tvScipAuth.visibility = View.VISIBLE
        }

        binding.tvScipAuth.setOnClickListener {
            startFragment(it.context, MainScreenFragment.newInstance())
        }
        binding.signInButton.setOnClickListener {
            startFragment(it.context, SignInFragment.newInstance())
        }
        binding.signUpButton.setOnClickListener {
            startFragment(it.context, SignUpFragment.newInstance())
        }

        return binding.root
    }

    companion object {
        fun newInstance() =
            OnBoardingScreen2Fragment()
    }
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) ?: return false
    return when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}
