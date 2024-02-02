package com.example.coachconnect

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.example.coachconnect.databinding.GoalsettingBinding

class GoalsettingActivity : AppCompatActivity() {

    private lateinit var binding: GoalsettingBinding
    private val sharedPreferencesKey = "profile_data"

    // 각 그룹당 선택된 체크박스 ID를 저장할 변수
    private var selectedCheckBoxGroup1: Int? = null
    private var selectedCheckBoxGroup2: Int? = null

    // 각 그룹의 중복 체크 여부를 확인할 변수
    private var isCheckBoxGroup1Selected = false
    private var isCheckBoxGroup2Selected = false

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

        // 각 체크박스에 대한 리스너 설정
        binding.checkBox21.setOnCheckedChangeListener { _, isChecked ->
            handleCheckBoxChange(isChecked, binding.checkBox21, 1)
        }
        binding.checkBox22.setOnCheckedChangeListener { _, isChecked ->
            handleCheckBoxChange(isChecked, binding.checkBox22, 1)
        }
        binding.checkBox23.setOnCheckedChangeListener { _, isChecked ->
            handleCheckBoxChange(isChecked, binding.checkBox23, 1)
        }
        binding.checkBox31.setOnCheckedChangeListener { _, isChecked ->
            handleCheckBoxChange(isChecked, binding.checkBox31, 2)
        }
        binding.checkBox32.setOnCheckedChangeListener { _, isChecked ->
            handleCheckBoxChange(isChecked, binding.checkBox32, 2)
        }
        binding.checkBox33.setOnCheckedChangeListener { _, isChecked ->
            handleCheckBoxChange(isChecked, binding.checkBox33, 2)
        }
        binding.checkBox34.setOnCheckedChangeListener { _, isChecked ->
            handleCheckBoxChange(isChecked, binding.checkBox34, 2)
        }
        binding.checkBox35.setOnCheckedChangeListener { _, isChecked ->
            handleCheckBoxChange(isChecked, binding.checkBox35, 2)
        }
        binding.checkBox36.setOnCheckedChangeListener { _, isChecked ->
            handleCheckBoxChange(isChecked, binding.checkBox36, 2)
        }
        binding.checkBox37.setOnCheckedChangeListener { _, isChecked ->
            handleCheckBoxChange(isChecked, binding.checkBox37, 2)
        }

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
            // SharedPreferences에 저장
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

    // 체크박스의 상태가 변경될 때 호출되는 함수
    private fun handleCheckBoxChange(
        isChecked: Boolean,
        checkBox: CheckBox,
        group: Int
    ) {
        if (isChecked) {
            // 이미 선택된 경우 체크를 해제하고 메시지 표시
            if (group == 1 && selectedCheckBoxGroup1 != null) {
                checkBox.isChecked = false
                Toast.makeText(this, "이미 선택된 목표가 있습니다.", Toast.LENGTH_SHORT).show()
            } else if (group == 2 && selectedCheckBoxGroup2 != null) {
                checkBox.isChecked = false
                Toast.makeText(this, "이미 선택된 목표가 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                // 그룹에 따라 선택된 체크박스 ID 설정
                if (group == 1) {
                    selectedCheckBoxGroup1 = checkBox.id
                } else if (group == 2) {
                    selectedCheckBoxGroup2 = checkBox.id
                }

                // 그룹에 따라 중복 체크 여부 설정
                if (group == 1) {
                    isCheckBoxGroup1Selected = true
                } else if (group == 2) {
                    isCheckBoxGroup2Selected = true
                }
            }
        } else {
            // 체크 해제된 경우 선택된 체크박스 ID 초기화
            if (group == 1) {
                selectedCheckBoxGroup1 = null
            } else if (group == 2) {
                selectedCheckBoxGroup2 = null
            }

            // 그룹에 따라 중복 체크 여부 설정
            if (group == 1) {
                isCheckBoxGroup1Selected = false
            } else if (group == 2) {
                isCheckBoxGroup2Selected = false
            }
        }
    }
}
