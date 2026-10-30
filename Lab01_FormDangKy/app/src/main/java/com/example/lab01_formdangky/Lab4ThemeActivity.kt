package com.example.lab01_formdangky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColors = lightColorScheme(
    primary = Color(0xFF0D9488),
    onPrimary = Color.White,
    secondary = Color(0xFF0F766E),
    surface = Color(0xFFF8FAFC),
    onSurface = Color(0xFF0F172A),
    surfaceVariant = Color(0xFFE2E8F0),
    onSurfaceVariant = Color(0xFF475569)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF2DD4BF),
    onPrimary = Color(0xFF00382F),
    secondary = Color(0xFF5EEAD4),
    surface = Color(0xFF1E293B),
    onSurface = Color(0xFFF1F5F9),
    surfaceVariant = Color(0xFF0F172A),
    onSurfaceVariant = Color(0xFF94A3B8)
)

class Lab4ThemeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab4AppRoot()
        }
    }
}

@Composable
fun Lab4AppRoot() {
    val systemDark = isSystemInDarkTheme()
    var darkMode by remember { mutableStateOf(systemDark) }

    MaterialTheme(
        colorScheme = if (darkMode) DarkColors else LightColors
    ) {
        ThemeDemoScreen(darkMode = darkMode, onDarkModeChange = { darkMode = it })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeDemoScreen(darkMode: Boolean, onDarkModeChange: (Boolean) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Giao diện & Theme") },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // 1. Card chuyển đổi Dark Mode
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Icon(
                        imageVector = Icons.Default.Home, // Hoặc icon mặt trăng tương đương
                        contentDescription = "Moon",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Chế độ tối (Dark Mode)", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "Sử dụng giao diện tối để tiết kiệm pin và giảm mỏi mắt.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = darkMode,
                        onCheckedChange = onDarkModeChange
                    )
                }
            }

            // 2. MÀU SẮC (ROLES)
            Text("MÀU SẮC (ROLES)", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AssistChip(
                    onClick = {},
                    label = { Text("Primary") },
                    leadingIcon = { Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(16.dp)) }
                )
                AssistChip(onClick = {}, label = { Text("Secondary") })
                AssistChip(onClick = {}, label = { Text("Surface") })
            }

            // 3. XEM TRƯỚC THẺ
            Text("XEM TRƯỚC THẺ", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Tiêu đề thẻ", style = MaterialTheme.typography.titleMedium)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Đây là nội dung mẫu của thẻ.\nNó giúp bạn xem trước giao diện.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("HÀNH ĐỘNG")
                    }
                }
            }

            // 4. Chú thích dưới cùng
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Các thay đổi sẽ được áp dụng ngay lập tức trên toàn ứng dụng.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}