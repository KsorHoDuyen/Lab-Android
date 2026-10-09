package com.example.lab02_danhsachsinhvien

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Data Model
data class Student(val id: Int, val name: String, val major: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                StudentListScreen()
            }
        }
    }
}

// 2. Màn hình danh sách sinh viên
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen() {
    val context = LocalContext.current

    // Danh sách sinh viên mẫu
    val fullList = remember {
        listOf(
            Student(1, "Nguyễn Văn An", "Công nghệ thông tin"),
            Student(2, "Trần Thị Bích", "Công nghệ thông tin"),
            Student(3, "Lê Hoàng Minh", "Công nghệ thông tin")
        )
    }

    var searchQuery by remember { mutableStateOf("") }

    // Lọc danh sách theo từ khóa tìm kiếm
    val filteredList = fullList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh sách sinh viên") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Thanh tìm kiếm
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Tìm kiếm sinh viên") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Danh sách LazyColumn
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredList, key = { it.id }) { student ->
                    StudentCard(
                        student = student,
                        onDetailClick = {
                            Toast.makeText(context, student.name, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

// 3. Card thông tin từng sinh viên
@Composable
fun StudentCard(student: Student, onDetailClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar chữ cái đầu
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.primary, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = student.name.firstOrNull()?.toString() ?: "",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Thông tin tên và ngành
            Column(modifier = Modifier.weight(1f)) {
                Text(text = student.name, style = MaterialTheme.typography.titleMedium)
                Text(text = student.major, style = MaterialTheme.typography.bodyMedium)
            }

            // Nút Xem chi tiết
            OutlinedButton(onClick = onDetailClick) {
                Text("Xem chi tiết")
            }
        }
    }
}