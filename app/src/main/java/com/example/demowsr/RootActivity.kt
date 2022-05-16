package com.example.demowsr

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.demowsr.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Demowsr)
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().
            setReorderingAllowed(true).
            setCustomAnimations(android.R.anim.fade_in , android.R.anim.fade_out).
            replace(binding.rootFragment.id, LaunchScreenFragment.newInstance()).
            commit()
    }
}

fun startFragment(context: Context, fragment: Fragment){
    val manager = (context as FragmentActivity).supportFragmentManager
    manager.beginTransaction()
        .setReorderingAllowed(true)
        .setCustomAnimations(androidx.appcompat.R.anim.abc_slide_in_bottom, androidx.appcompat.R.anim.abc_slide_out_top)
        .replace(R.id.root_fragment, fragment)
        .commit()
}

fun etIsEmpty(et: EditText): Boolean{
    return et.text.toString().trim().isEmpty()
}

fun etShowError(et: EditText, text: String){
    et.requestFocus()
    et.error = text
}