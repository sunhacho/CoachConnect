package com.example.coachconnect

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coachconnect.databinding.GoalsettingBinding

class GoalsettingActivity : AppCompatActivity() {

    private lateinit var binding: GoalsettingBinding
    private val sharedPreferencesKey = "profile_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GoalsettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 체크박스 선택 여부 설정
        binding.checkBox1.isChecked = intent.getBooleanExtra("checkBox1Selected", false)
        binding.checkBox2.isChecked = intent.getBooleanExtra("checkBox2Selected", false)
        binding.checkBox3.isChecked = intent.getBooleanExtra("checkBox3Selected", false)
        binding.checkBox4.isChecked = intent.getBooleanExtra("checkBox4Selected", false)
        binding.checkBox21.isChecked = intent.getBooleanExtra("checkBox21Selected", false)
        binding.checkBox22.isChecked = intent.getBooleanExtra("checkBox22Selected", false)
        binding.checkBox23.isChecked = intent.getBooleanExtra("checkBox23Selected", false)
        binding.checkBox31.isChecked = intent.getBooleanExtra("checkBox31Selected", false)
        binding.checkBox32.isChecked = intent.getBooleanExtra("checkBox32Selected", false)
        binding.checkBox33.isChecked = intent.getBooleanExtra("checkBox33Selected", false)
        binding.checkBox34.isChecked = intent.getBooleanExtra("checkBox34Selected", false)
        binding.checkBox35.isChecked = intent.getBooleanExtra("checkBox35Selected", false)
        binding.checkBox36.isChecked = intent.getBooleanExtra("checkBox36Selected", false)
        binding.checkBox37.isChecked = intent.getBooleanExtra("checkBox37Selected", false)

        // 기존 저장된 값 불러오기
        val sharedPreferences = getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)

        binding.checkBox1.isChecked = sharedPreferences.getBoolean("checkBox1Selected", false)
        binding.checkBox2.isChecked = sharedPreferences.getBoolean("checkBox2Selected", false)
        binding.checkBox3.isChecked = sharedPreferences.getBoolean("checkBox3Selected", false)
        binding.checkBox4.isChecked = sharedPreferences.getBoolean("checkBox4Selected", false)
        binding.checkBox21.isChecked = sharedPreferences.getBoolean("checkBox21Selected", false)
        binding.checkBox22.isChecked = sharedPreferences.getBoolean("checkBox22Selected", false)
        binding.checkBox23.isChecked = sharedPreferences.getBoolean("checkBox23Selected", false)
        binding.checkBox31.isChecked = sharedPreferences.getBoolean("checkBox31Selected", false)
        binding.checkBox32.isChecked = sharedPreferences.getBoolean("checkBox32Selected", false)
        binding.checkBox33.isChecked = sharedPreferences.getBoolean("checkBox33Selected", false)
        binding.checkBox34.isChecked = sharedPreferences.getBoolean("checkBox34Selected", false)
        binding.checkBox35.isChecked = sharedPreferences.getBoolean("checkBox35Selected", false)
        binding.checkBox36.isChecked = sharedPreferences.getBoolean("checkBox36Selected", false)
        binding.checkBox37.isChecked = sharedPreferences.getBoolean("checkBox37Selected", false)

        val home = findViewById<ImageView>(R.id.home_ic)
        val search_ic = findViewById<ImageView>(R.id.search_ic)
        val happy_ic = findViewById<ImageView>(R.id.happy_ic)
        val profile_ic = findViewById<ImageView>(R.id.profile_ic)

        // 이미지 버튼 클릭시 해당 액티비티 전환
        search_ic.setOnClickListener {
            // 효과 (아이콘 축소)
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                val intent = Intent(this, TrainerList::class.java)
                startActivity(intent)
                // 효과 제거 (아이콘 확대)
                it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }
        }

        happy_ic.setOnClickListener {
            // 효과 (아이콘 축소)
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                val intent = Intent(this, GoalsettingActivity::class.java)
                startActivity(intent)
                // 효과 제거 (아이콘 확대)
                it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }.start()
        }

        profile_ic.setOnClickListener {
            // 효과 (아이콘 축소)
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                // 효과 제거 (아이콘 확대)
                it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }.start()
        }
        home.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }
        }

        // "확인" 버튼 클릭 이벤트 처리
        findViewById<Button>(R.id.button3)?.setOnClickListener {
            //SharedPreferences에 저장
            val editor = sharedPreferences.edit()
            editor.putBoolean("checkBox1Selected", binding.checkBox1.isChecked)
            editor.putBoolean("checkBox2Selected", binding.checkBox2.isChecked)
            editor.putBoolean("checkBox3Selected", binding.checkBox3.isChecked)
            editor.putBoolean("checkBox4Selected", binding.checkBox4.isChecked)
            editor.putBoolean("checkBox21Selected", binding.checkBox21.isChecked)
            editor.putBoolean("checkBox22Selected", binding.checkBox22.isChecked)
            editor.putBoolean("checkBox23Selected", binding.checkBox23.isChecked)
            editor.putBoolean("checkBox31Selected", binding.checkBox31.isChecked)
            editor.putBoolean("checkBox32Selected", binding.checkBox32.isChecked)
            editor.putBoolean("checkBox33Selected", binding.checkBox33.isChecked)
            editor.putBoolean("checkBox34Selected", binding.checkBox34.isChecked)
            editor.putBoolean("checkBox35Selected", binding.checkBox35.isChecked)
            editor.putBoolean("checkBox36Selected", binding.checkBox36.isChecked)
            editor.putBoolean("checkBox37Selected", binding.checkBox37.isChecked)

            editor.apply()

            Toast.makeText(this, "목표 설정 완료", Toast.LENGTH_SHORT).show()
        }
    }
}