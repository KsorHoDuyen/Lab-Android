package com.example.lab01_formdangky

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Model Sinh Viên cho Lab 3
data class Lab3Student(
    val id: Int,
    val name: String,
    val major: String,
    val email: String,
    val phone: String
)

class Lab3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Danh sách sinh viên mẫu
        val studentList = listOf(
            Lab3Student(1, "Nguyễn Văn An", "Công nghệ thông tin", "an.nguyen@example.com", "0123 456 789"),
            Lab3Student(2, "Trần Thị Bích", "Kế toán", "bich.tran@example.com", "0987 654 321"),
            Lab3Student(3, "Lê Hoàng Nam", "Quản trị kinh doanh", "nam.le@example.com", "0912 345 678")
        )

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Danh sách sinh viên (Lab 3)",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(studentList) { student ->
                                StudentItemCard(student = student, onClick = {
                                    // Chuyển sang màn hình Chi tiết bằng Intent
                                    val intent = Intent(this@Lab3Activity, Lab3DetailActivity::class.java).apply {
                                        putExtra(Lab3DetailActivity.EXTRA_STUDENT_ID, student.id)
                                        putExtra(Lab3DetailActivity.EXTRA_STUDENT_NAME, student.name)
                                        putExtra(Lab3DetailActivity.EXTRA_STUDENT_MAJOR, student.major)
                                        putExtra(Lab3DetailActivity.EXTRA_STUDENT_EMAIL, student.email)
                                        putExtra(Lab3DetailActivity.EXTRA_STUDENT_PHONE, student.phone)
                                    }
                                    startActivity(intent)
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StudentItemCard(student: Lab3Student, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = student.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = student.major, fontSize = 14.sp, color = MaterialTheme.colorScheme.secondary)
        }
    }
}