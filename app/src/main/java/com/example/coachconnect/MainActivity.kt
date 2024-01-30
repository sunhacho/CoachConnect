package com.example.coachconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
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
        bkInfo1.text = "예약 일자: $selectedDate\n예약 시간: $timePeriod $selectedTime"
        // 예약 정보가 있는지 확인하고 UI 업데이트
        updateReservationUI(selectedDate, selectedTime)
    }
    private fun updateReservationUI(selectedDate: String?, selectedTime: String?) {
        val bkInfo1 = findViewById<TextView>(R.id.bkInfo1)
        val boxIc = findViewById<ImageView>(R.id.box_ic)
        val boxIc2 = findViewById<ImageView>(R.id.box_ic2)

        if (selectedDate.isNullOrBlank() || selectedTime.isNullOrBlank()) {
            // 예약 정보가 없는 경우
            bkInfo1.text = "예약되어있는 운동이 없습니다"
            bkInfo1.visibility = View.VISIBLE
            boxIc.visibility = View.VISIBLE
            boxIc2.visibility = View.GONE
        } else {
            // 예약 정보가 있는 경우
            bkInfo1.visibility = View.VISIBLE
            boxIc.visibility = View.GONE
            boxIc2.visibility = View.VISIBLE
        }


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