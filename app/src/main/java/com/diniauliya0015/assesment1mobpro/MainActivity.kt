package com.diniauliya0015.assesment1mobpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.diniauliya0015.assesment1mobpro.navigation.SetupNavGraph
import com.diniauliya0015.assesment1mobpro.ui.theme.Assesment1MobproTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assesment1MobproTheme {
                SetupNavGraph()
            }
        }
    }
}

