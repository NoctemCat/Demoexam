package com.example.demowsr

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.FragmentActivity

@SuppressLint("CustomSplashScreen")
class LaunchScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launch_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val prBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        prBar.progress = 100
        Handler(Looper.getMainLooper()).postDelayed({
            val manager = (view.context as FragmentActivity).supportFragmentManager
            manager.beginTransaction()
                .setCustomAnimations(androidx.appcompat.R.anim.abc_slide_in_bottom , androidx.appcompat.R.anim.abc_slide_out_top)
                .replace(R.id.root_fragment, OnBoardingFragment.newInstance())
                .commit()
        },2000)
    }

    companion object {
        fun newInstance() =
            LaunchScreenFragment()
    }
}


//val executor = Executors.newSingleThreadExecutor()
//val handler = Handler(Looper.getMainLooper())
//
//executor.execute {
//
//    handler.post {
//
//    }
//}