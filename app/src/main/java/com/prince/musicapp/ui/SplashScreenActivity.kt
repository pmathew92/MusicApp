package com.prince.musicapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View.VISIBLE
import android.view.WindowManager
import com.prince.musicapp.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            //            searchText.animate()
//                    .translationY(0f)
//                    .alpha(0.0f)
//                    .setListener(object : AnimatorListenerAdapter() {
//                        override fun onAnimationEnd(animation: Animator?) {
//                            super.onAnimationEnd(animation)
//                        }
//                    })
            searchText.visibility = VISIBLE

        }, 2000)

        searchText.setOnClickListener {
            startActivity(Intent(applicationContext, SearchActivity::class.java))
            finish()
        }
    }
}
