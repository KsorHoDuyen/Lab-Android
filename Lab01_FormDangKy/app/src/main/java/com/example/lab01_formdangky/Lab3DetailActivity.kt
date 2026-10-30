package com.example.lab01_formdangky

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Lab3DetailActivity : ComponentActivity() {
    companion object {
        const val EXTRA_STUDENT_ID = "extra_student_id"
        const val EXTRA_STUDENT_NAME = "extra_student_name"
        const val EXTRA_STUDENT_MAJOR = "extra_student_major"
        const val EXTRA_STUDENT_EMAIL = "extra_student_email"
        const val EXTRA_STUDENT_PHONE = "extra_student_phone"
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Nhận dữ liệu truyền từ Lab3Activity
        val name = intent.getStringExtra(EXTRA_STUDENT_NAME) ?: "Chưa có tên"
        val major = intent.getStringExtra(EXTRA_STUDENT_MAJOR) ?: "Chưa có ngành"
        val email = intent.getStringExtra(EXTRA_STUDENT_EMAIL) ?: "Chưa có email"
        val phone = intent.getStringExtra(EXTRA_STUDENT_PHONE) ?: "Chưa có phone"

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        // Thanh tiêu đề TopBar có nút Back quay lại list
                        TopAppBar(
                            title = { Text("Chi tiết sinh viên") },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                                }
                            }
                        )

                        // Nội dung chi tiết
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))

                            // Avatar chữ cái đầu
                            Box(
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = name.firstOrNull()?.toString() ?: "A",
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Tên và Ngành
                            Text(text = name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                            Text(text = major, fontSize = 16.sp, color = Color.Gray)

                            Spacer(modifier = Modifier.height(32.dp))

                            // Thẻ thông tin Email & Phone
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(text = "Email", fontSize = 12.sp, color = Color.Gray)
                                    Text(text = email, fontSize = 16.sp, fontWeight = FontWeight.Medium)

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Text(text = "Phone", fontSize = 12.sp, color = Color.Gray)
                                    Text(text = phone, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                                }
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            // Nút Gọi điện và Gửi email
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Button(
                                    onClick = {
                                        Toast.makeText(this@Lab3DetailActivity, "Đang gọi: $phone", Toast.LENGTH_SHORT).show()
                                    },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(Icons.Default.Phone, contentDescription = null)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Gọi điện")
                                }

                                OutlinedButton(
                                    onClick = {
                                        Toast.makeText(this@Lab3DetailActivity, "Gửi email tới: $email", Toast.LENGTH_SHORT).show()
                                    },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(Icons.Default.Email, contentDescription = null)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Gửi email")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}