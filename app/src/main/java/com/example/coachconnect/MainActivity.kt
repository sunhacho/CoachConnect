package com.example.coachconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val home_ic = findViewById<ImageView>(R.id.home_ic)
        val search_ic = findViewById<ImageView>(R.id.search_ic)
        val happy_ic = findViewById<ImageView>(R.id.happy_ic)
        val profile_ic = findViewById<ImageView>(R.id.profile_ic)


        // 하단 바 클릭 시

        happy_ic.setOnClickListener {
            val intent = Intent(this, GoalsettingActivity::class.java)
            startActivity(intent)
        }
        profile_ic.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}