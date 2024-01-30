package com.example.coachconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchImageView: ImageView = findViewById(R.id.search_ic)

        searchImageView.setOnClickListener {
            it.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).withEndAction {
                val intent = Intent(this, TrainerInfo::class.java)
                startActivity(intent)

                it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }
        }
        val goalsetting = findViewById<ImageView>(R.id.happy_ic)
        goalsetting.setOnClickListener{
            val intent = Intent(this, GoalsettingActivity::class.java)
            startActivity(intent)
        }

        val profile = findViewById<ImageView>(R.id.profile_ic)
        profile.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}