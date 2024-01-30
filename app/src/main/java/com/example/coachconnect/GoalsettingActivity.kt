package com.example.coachconnect

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.coachconnect.databinding.ActivityProfileBinding

class GoalsettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goalsetting)

        val searchImageView: ImageView = findViewById(R.id.search_ic)

        // 이미지 버튼 클릭시 해당 액티비티 전환
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
        val home = findViewById<ImageView>(R.id.home_ic)
        home.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // "확인" 버튼 클릭 이벤트 처리
        findViewById<Button>(R.id.button3)?.setOnClickListener {
            Toast.makeText(this, "목표 설정 완료", Toast.LENGTH_SHORT).show()
        }
    }
}

