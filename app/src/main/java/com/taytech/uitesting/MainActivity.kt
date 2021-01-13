package com.taytech.uitesting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_next_activity.setOnClickListener {

            val intent = Intent(this, SecondActivity::class.java);
            startActivity(intent);
        }
    }
}