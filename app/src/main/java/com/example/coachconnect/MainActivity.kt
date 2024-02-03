package com.example.coachconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import java.text.SimpleDateFormat
import java.util.*
import android.widget.FrameLayout
import android.util.Log

class MainActivity : AppCompatActivity() {
    private val RESULT_CODE_BOOKING = 1 // 예약 결과 코드


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val trainerName = intent.getStringExtra("trainerName")
        val selectedTimeArray = intent.getStringArrayExtra("selectedTimes")
        val selectedDate = intent.getStringExtra("selectedDate")
        val trainerLocation = intent.getStringExtra("trainerLocation")
        val amPm = intent.getStringExtra("amPm")
        Log.d("MainDebug", "amPm in MainActivity: $amPm")

        // 예약 정보를 표시할 TextView 찾아서 텍스트 설정
        val bkInfo1 = findViewById<TextView>(R.id.bkInfo1)
        val formattedTime = selectedTimeArray?.joinToString(", ") { it ?: "" } ?: ""

        val displayText =
            "$trainerName 트레이너\n $selectedDate\n $amPm $formattedTime\n 📍 $trainerLocation"
        bkInfo1.text = displayText
        updateReservationUI(selectedDate, selectedTimeArray ?: emptyArray())

        // 하단 바 클릭 시
        val home_ic = findViewById<ImageView>(R.id.home_ic)
        val search_ic = findViewById<ImageView>(R.id.search_ic)
        val happy_ic = findViewById<ImageView>(R.id.happy_ic)
        val profile_ic = findViewById<ImageView>(R.id.profile_ic)

        search_ic.setOnClickListener {
            // 효과 (아이콘 축소)
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                val intent = Intent(this, TrainerList::class.java)
                startActivity(intent)
                // 효과 제거 (아이콘 확대)
                it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }.start()
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
    }

    private fun updateReservationUI(selectedDate: String?, selectedTimeArray: Array<String?>) {
        val bkInfo1 = findViewById<TextView>(R.id.bkInfo1)
        val boxIc = findViewById<ImageView>(R.id.box_ic)
        val boxIc2 = findViewById<ImageView>(R.id.box_ic2)
        val noText = findViewById<TextView>(R.id.no_text)

        if (selectedDate.isNullOrBlank() || selectedTimeArray.isNullOrEmpty()) {
            // 예약 정보가 없는 경우
            bkInfo1.visibility = View.GONE
            boxIc.visibility = View.VISIBLE
            boxIc2.visibility = View.GONE
            noText.visibility = View.VISIBLE
        } else {
            // 예약 정보가 있는 경우
            bkInfo1.visibility = View.VISIBLE
            boxIc.visibility = View.GONE
            boxIc2.visibility = View.VISIBLE
            noText.visibility = View.GONE
        }
    }
}
