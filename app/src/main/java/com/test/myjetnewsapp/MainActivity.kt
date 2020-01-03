package com.test.myjetnewsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import com.test.myjetnewsapp.ui.MyJetNewsApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJetNewsApp()
        }
    }
}
