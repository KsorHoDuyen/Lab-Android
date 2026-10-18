package com.example.lab03_recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab03_recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userList = listOf(
            User("Nguyễn Văn A", "ana@example.com"),
            User("Trần Thị B", "btb@example.com"),
            User("Lê Văn C", "cvc@example.com"),
            User("Phạm Thị D", "dpd@example.com"),
            User("Hoàng Văn E", "eve@example.com")
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = UserAdapter(userList)
    }
}