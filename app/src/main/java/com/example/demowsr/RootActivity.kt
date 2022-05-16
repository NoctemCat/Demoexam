package com.example.demowsr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demowsr.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().
            setReorderingAllowed(true).
            setCustomAnimations(android.R.anim.fade_in , android.R.anim.fade_out).
            replace(binding.rootFragment.id, LaunchScreenFragment.newInstance()).
            commit();
    }
}