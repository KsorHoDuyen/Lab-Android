package com.example.lab01_formdangky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColors = lightColorScheme(
    primary = Color(0xFF0D9488),
    onPrimary = Color.White,
    secondary = Color(0xFF0F766E),
    surface = Color(0xFFF8FAFC),
    onSurface = Color(0xFF0F172A)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF2DD4BF),
    onPrimary = Color(0xFF00382F),
    secondary = Color(0xFF5EEAD4),
    surface = Color(0xFF0F172A),
    onSurface = Color(0xFFF1F5F9)
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
            CenterAlignedTopAppBar(
                title = { Text("Giao diện & Theme") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Chế độ tối (Dark Mode)", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "Bật hoặc tắt để đổi ColorScheme",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Switch(
                    checked = darkMode,
                    onCheckedChange = onDarkModeChange
                )
            }

            Text("Color Roles đang dùng", style = MaterialTheme.typography.titleSmall)
            Text("• Primary: nút / điểm nhấn chính", color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text("• Surface: nền màn hình / thẻ", color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text("• OnSurface: chữ trên nền Surface", color = MaterialTheme.colorScheme.onSurfaceVariant)

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Xem trước thẻ", style = MaterialTheme.typography.titleMedium)
                    Text("Thẻ dùng Material Theme; đổi Dark Mode sẽ đổi màu.")
                }
            }
        }
    }
}