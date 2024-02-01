package com.example.coachconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private val RESULT_CODE_BOOKING = 1 // 예약 결과 코드

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SharedPreferences에서 예약 정보 불러오기
        val sharedPreferences = getSharedPreferences("booking_info", MODE_PRIVATE)
        val selectedDate = sharedPreferences.getString("selectedDate", "")
        val selectedTime = sharedPreferences.getString("selectedTime", "")

        // 오전과 오후를 구분하여 표시할 변수
        val timePeriod: String = when {
            selectedTime?.contains("am", ignoreCase = true) == true -> "오전"
            selectedTime?.contains("pm", ignoreCase = true) == true -> "오후"
            else -> "" // 오전/오후가 아닌 경우
        }

        // 예약 정보를 표시할 TextView 찾아서 텍스트 설정
        val bkInfo1 = findViewById<TextView>(R.id.bkInfo1)
        bkInfo1.text = "\n $selectedDate\n $timePeriod $selectedTime"
        // 예약 정보가 있는지 확인하고 UI 업데이트
        updateReservationUI(selectedDate, selectedTime)

        val search_ic = findViewById<ImageView>(R.id.search_ic)
        val happy_ic = findViewById<ImageView>(R.id.happy_ic)
        val profile_ic = findViewById<ImageView>(R.id.profile_ic)

        // 하단 바 클릭 시

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
    }


    private fun updateReservationUI(selectedDate: String?, selectedTime: String?) {
        val bkInfo1 = findViewById<TextView>(R.id.bkInfo1)
        val boxIc = findViewById<ImageView>(R.id.box_ic)
        val boxIc2 = findViewById<ImageView>(R.id.box_ic2)

        if (selectedDate.isNullOrBlank() || selectedTime.isNullOrBlank()) {
            // 예약 정보가 없는 경우
            bkInfo1.visibility = View.GONE
            boxIc.visibility = View.VISIBLE
            boxIc2.visibility = View.GONE
        } else {
            // 예약 정보가 있는 경우
            bkInfo1.visibility = View.VISIBLE
            boxIc.visibility = View.GONE
            boxIc2.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_CODE_BOOKING && resultCode == RESULT_OK) {
            // Booking 액티비티에서 전달받은 예약 정보
            val trainerName = data?.getStringExtra("trainerName")
            val selectedTimes = data?.getStringArrayExtra("selectedTimes")
            val selectedDate = data?.getStringExtra("selectedDate")

            // 예약 정보를 표시할 TextView 찾아서 텍스트 설정
            val bkInfo1 = findViewById<TextView>(R.id.bkInfo1)
        }
    }
}