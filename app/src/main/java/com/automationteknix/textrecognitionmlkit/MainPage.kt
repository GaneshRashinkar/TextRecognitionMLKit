package com.automationteknix.textrecognitionmlkit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import com.automationteknix.textrecognitionmlkit.databinding.ActivityMainPageBinding

class MainPage : AppCompatActivity() {
    lateinit var mBinding: ActivityMainPageBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityMainPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val toolbar=mBinding.toolbar
        getSupportActionBar()?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        setSupportActionBar(toolbar.root)
        toggle=ActionBarDrawerToggle(this,mBinding.root, R.string.open,R.string.close)
        toggle.drawerArrowDrawable.color=resources.getColor(R.color.white)
        setContentView(mBinding.root)
        mBinding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        mBinding.btnNext.setOnClickListener {
            startActivity(Intent(this,EnterTextActivity::class.java))
        }
    }

    //toggle was not working so i implemented this method
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}