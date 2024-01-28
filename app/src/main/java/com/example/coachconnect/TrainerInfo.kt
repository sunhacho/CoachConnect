package com.example.coachconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class TrainerInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trainer_info)

        val back = findViewById<ImageButton>(R.id.back)
        val btnBook = findViewById<Button>(R.id.btnBook)
        val btnMessage = findViewById<Button>(R.id.btnMessage)

        // 예약하기 클릭 시
        btnBook.setOnClickListener {
            // 회원가입 액티비티로 이동
            val intent = Intent(this, Booking::class.java)
            startActivity(intent)

            // back 버튼 클릭 시
            //back.setOnClickListener {
            // 트레이너 목록 액티비티로 이동
            // val intent = Intent(this, 트레이너 목록 페이지::class.java)
            // startActivity(intent)
        }
    }
}