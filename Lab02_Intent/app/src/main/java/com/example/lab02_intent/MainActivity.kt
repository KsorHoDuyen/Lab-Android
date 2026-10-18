package com.example.lab02_intent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab02_intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
            val textToSend = binding.edtData.text.toString()

            // Tạo Intent để chuyển từ MainActivity sang SecondActivity và truyền dữ liệu
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("EXTRA_DATA", textToSend)
            }
            startActivity(intent)
        }
    }
}