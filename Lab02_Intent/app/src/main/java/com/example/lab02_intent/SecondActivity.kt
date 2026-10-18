package com.example.lab02_intent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab02_intent.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedData = intent.getStringExtra("EXTRA_DATA")
        if (!receivedData.isNullOrEmpty()) {
            binding.tvResult.text = "Dữ liệu nhận được: $receivedData"
        }
    }
}