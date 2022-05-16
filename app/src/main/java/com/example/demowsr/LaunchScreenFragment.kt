package com.example.demowsr

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.Executors

class LaunchScreenFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launch_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({
            val manager = (view.context as FragmentActivity).supportFragmentManager
            manager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in , android.R.anim.fade_out)
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