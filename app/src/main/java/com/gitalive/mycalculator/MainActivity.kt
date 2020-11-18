package com.gitalive.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gitalive.mycalculator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myViewModel = MyViewModel(application)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.data = myViewModel
    }
}
