package com.example.coachconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class TrainerInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trainer_info)

        val back = findViewById<ImageButton>(R.id.back)
        val btnBook = findViewById<Button>(R.id.btnBook)
        val home_ic3 = findViewById<ImageView>(R.id.home_ic3)
        val search_ic3 = findViewById<ImageView>(R.id.search_ic3)
        val happy_ic3 = findViewById<ImageView>(R.id.happy_ic3)
        val profile_ic3 = findViewById<ImageView>(R.id.profile_ic3)

        // 예약하기 클릭 시
        btnBook.setOnClickListener {
            // booking 액티비티로 이동
            val intent = Intent(this, Booking::class.java)
            startActivity(intent)
        }

        // back 버튼 클릭 시
        //back.setOnClickListener {
        // 트레이너 목록 액티비티로 이동
        // val intent = Intent(this, 트레이너 목록 페이지::class.java)
        // startActivity(intent)
        //}

        // 하단 바 클릭 시
        home_ic3.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
        }

    }
}