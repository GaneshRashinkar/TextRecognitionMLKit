package com.automationteknix.textrecognitionmlkit

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.automationteknix.textrecognitionmlkit.databinding.ActivityMainBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    val IMAGE_REQUEST=1
    var text="";
    lateinit var mBinding:ActivityMainBinding

    // TODO: to use image picker library
    val imagePicker=registerForActivityResult(ActivityResultContracts.StartActivityForResult(),{
        result->
        val uri=result.data?.data
        mBinding.imagePreview.setImageURI(uri)
        uri.let {
            val bitmap=MediaStore.Images.Media.getBitmap(contentResolver,it)
            runTextRecognition(bitmap)
        }
    })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //todo layoutInflater converts xml file into view
        mBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        text=intent?.getStringExtra("text")?:"";
        Log.e("input text",text)
        
        //todo immediately open camera to click image
        ImagePicker.Companion.with(this)
            //.compress(1024) //final image size will be less than 1024
            .maxResultSize(540,540) //maximum hight or width will 1080
            .createIntent {
                    intent->
                imagePicker.launch(intent)
            }
        mBinding.btnClickPhoto.setOnClickListener {
            ImagePicker.Companion.with(this)
                //.compress(1024) //final image size will be less than 1024
                .maxResultSize(540,540) //maximum hight or width will 1080
                .createIntent {
                    intent->
                    imagePicker.launch(intent)
                }
//          second way
           // getContent.launch("image/*")

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==IMAGE_REQUEST){
            val uri=data?.data
            mBinding.imagePreview.setImageURI(uri)
            mBinding.tvResult.movementMethod=ScrollingMovementMethod()
        }
    }
    fun runTextRecognition(bitmap:Bitmap){
        val inputImage=InputImage.fromBitmap(bitmap,0)
        val textRecognizer=TextRecognition.getClient()
        textRecognizer.process(inputImage)
            .addOnSuccessListener {
                val textBlocks=it.textBlocks
                it.text.toString()

//                if(textBlocks.size==0)
//                    Toast.makeText(this, "No Text Found", Toast.LENGTH_SHORT).show()
//                for(block in textBlocks){
//                   val lines=block.lines
                    var count=findCount(text,it.text)
                    mBinding.tvResult.setText("\'$text\' is occured $count times")

//               }

            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }

    fun findCount(pattern:String, input:String):Int{
        var count=0
        var pattern=Pattern.compile(pattern)
        var matcher=pattern.matcher(input)
        while (matcher.find()){
            count++
        }
        return count
    }
}