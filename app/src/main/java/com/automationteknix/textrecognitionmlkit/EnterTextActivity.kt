package com.automationteknix.textrecognitionmlkit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.automationteknix.textrecognitionmlkit.databinding.ActivityEnterTextBinding

class EnterTextActivity : AppCompatActivity() {
    lateinit var mBinding:ActivityEnterTextBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding= ActivityEnterTextBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.btnClickPhoto.setOnClickListener{
            val text=mBinding.etInputText.text.toString()
            val intent=Intent(this,MainActivity::class.java)
            intent.putExtra("text",text)
            startActivity(intent)
        }
    }
}