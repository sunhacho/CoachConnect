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
import com.example.coachconnect.databinding.ProfileBinding
import java.util.Calendar


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ProfileBinding
    private val sharedPreferencesKey = "profile_data"

    private var selectedBirthday: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Intent에서 사용자 이름 값 가져오기
        val userName = intent.getStringExtra("userName")
        val imageButton1Selected = intent.getBooleanExtra("imageButton1Selected", false)
        val imageButton2Selected = intent.getBooleanExtra("imageButton2Selected", false)
        val nameDate = intent.getStringExtra("nameDate")
        val birthdayDate = intent.getStringExtra("birthdayDate")
        val heightData = intent.getStringExtra("heightData")
        val nowWeightData = intent.getStringExtra("nowWeightData")
        val targetWeightData = intent.getStringExtra("targetWeightData")

        // 값이 null이 아니면 EditText에 표시
        userName?.let {
            binding.userName.setText(it)
        }
        nameDate?.let {
            binding.nameDate.setText(it)
        }
        birthdayDate?.let {
            binding.birthdayDate.setText(it)
        }
        heightData?.let {
            binding.heightData.setText(it)
        }
        nowWeightData?.let {
            binding.nowWeightData.setText(it)
        }
        targetWeightData?.let {
            binding.targetWeightData.setText(it)
        }

        // 이미지 버튼 선택 여부 설정
        binding.imageButton1.isSelected = imageButton1Selected
        binding.imageButton2.isSelected = imageButton2Selected

        // 기존 저장된 값 불러오기
        val sharedPreferences = getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)

        val storedName = sharedPreferences.getString("userName", "")
        binding.userName.setText(storedName)

        val storedImage1Selected = sharedPreferences.getBoolean("imageButton1Selected", false)
        binding.imageButton1.isSelected = storedImage1Selected
        val storedImage2Selected = sharedPreferences.getBoolean("imageButton2Selected", false)
        binding.imageButton2.isSelected = storedImage2Selected

        val storedNameDate = sharedPreferences.getString("nameDate", "")
        binding.nameDate.setText(storedNameDate)

        val storedBirthdayDate = sharedPreferences.getString("birthdayDate", "")
        binding.birthdayDate.setText(storedBirthdayDate)

        val storedHeightData = sharedPreferences.getString("heightData", "")
        binding.heightData.setText(storedHeightData)

        val storedNowWeightData = sharedPreferences.getString("nowWeightData", "")
        binding.nowWeightData.setText(storedNowWeightData)

        val storedTargetWeightData = sharedPreferences.getString("targetWeightData", "")
        binding.targetWeightData.setText(storedTargetWeightData)



        val home_ic = findViewById<ImageView>(R.id.home_ic)
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

        home_ic.setOnClickListener {
            // 효과 (아이콘 축소)
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                // 효과 제거 (아이콘 확대)
                it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }
        }

        binding.imageButton1.setOnClickListener {
            // 이미지 버튼1이 선택되어 있으면 선택 해제, 아니면 선택
            binding.imageButton1.isSelected = !binding.imageButton1.isSelected

            // 이미지 버튼2는 선택 해제
            binding.imageButton2.isSelected = false
        }

        binding.imageButton2.setOnClickListener {
            // 이미지 버튼2가 선택되어 있으면 선택 해제, 아니면 선택
            binding.imageButton2.isSelected = !binding.imageButton2.isSelected

            // 이미지 버튼1은 선택 해제
            binding.imageButton1.isSelected = false
        }

        //생일 데이터
        binding.birthdayDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val currentYear = cal.get(Calendar.YEAR)
            val currentMonth = cal.get(Calendar.MONTH)
            val currentDay = cal.get(Calendar.DAY_OF_MONTH)

            val data = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                val newDate = "$year/${month + 1}/$day"
                if (newDate != selectedBirthday) {
                    binding.birthdayDate.setText(newDate)
                    selectedBirthday = newDate
                } else {
                    // 동일한 날짜를 선택하면 삭제
                    binding.birthdayDate.setText("")
                    selectedBirthday = null
                }
            }

            DatePickerDialog(
                this, data, currentYear, currentMonth, currentDay
            ).show()
        }


        // "확인" 버튼 클릭 이벤트 처리
        findViewById<Button>(R.id.button3)?.setOnClickListener {
            // 입력된 값을 SharedPreferences에 저장
            val editor = sharedPreferences.edit()
            editor.putString("userName", binding.userName.text.toString())
            editor.putBoolean("imageButton1Selected", binding.imageButton1.isSelected)
            editor.putBoolean("imageButton2Selected", binding.imageButton2.isSelected)
            editor.putString("nameDate", binding.nameDate.text.toString())
            editor.putString("birthdayDate", binding.birthdayDate.text.toString())

            // 입력값이 있는 경우에만 "CM" 또는 "KG" 추가
            binding.heightData.text.toString().takeIf { it.isNotBlank() }?.let {
                val newHeight = it.replace("CM", "") + "CM"
                editor.putString("heightData", newHeight)
            } ?: editor.remove("heightData") // 입력값이 없으면 해당 키 제거

            binding.nowWeightData.text.toString().takeIf { it.isNotBlank() }?.let {
                val newNowWeight = it.replace("KG", "") + "KG"
                editor.putString("nowWeightData", newNowWeight)
            } ?: editor.remove("nowWeightData") // 입력값이 없으면 해당 키 제거

            binding.targetWeightData.text.toString().takeIf { it.isNotBlank() }?.let {
                val newTargetWeight = it.replace("KG", "") + "KG"
                editor.putString("targetWeightData", newTargetWeight)
            } ?: editor.remove("targetWeightData") // 입력값이 없으면 해당 키 제거


            editor.apply()
            // 저장 완료 메시지
            Toast.makeText(this, "프로필 정보 저장", Toast.LENGTH_SHORT).show()
            // 사용자명이 비어 있을 경우에 대한 메시지 표시 또는 로직 추가
            Toast.makeText(this, "사용자명을 입력하세요.", Toast.LENGTH_SHORT).show()
        }
    }
}