package com.example.coachconnect

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.coachconnect.databinding.ActivityProfileBinding
import java.util.Calendar


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val sharedPreferencesKey = "profile_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        //val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        //생일 데이터
        binding.imageButton1.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                binding.imageButton1.isSelected = true
                binding.imageButton2.isSelected = false
            }
        })

        binding.imageButton2.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                binding.imageButton2.isSelected = true
                binding.imageButton1.isSelected = false
            }
        })
        binding.birthdayDate.setOnClickListener{
            val cal = Calendar.getInstance()
            val data = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                binding.birthdayDate.setText(year.toString() + "/" + (month + 1).toString() + "/" + day.toString())

            }
            DatePickerDialog(this, data, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }
        // "확인" 버튼 클릭 이벤트 처리
        findViewById<Button>(R.id.button3)?.setOnClickListener {
            Toast.makeText(this, "확인", Toast.LENGTH_SHORT).show()
        }
    }
}

