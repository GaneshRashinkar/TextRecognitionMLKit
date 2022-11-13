package com.automationteknix.textrecognitionmlkit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.automationteknix.textrecognitionmlkit.databinding.ActivityMySplashBinding

class MySplashActivity : AppCompatActivity() {
    lateinit var mBinding:ActivityMySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityMySplashBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        var animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        mBinding.animText.animation = animation
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {

                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this@MySplashActivity, MainPage::class.java))
                    finish()
                }, 1000);

            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        });
    }
}