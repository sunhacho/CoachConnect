package com.example.coachconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private val RESULT_CODE_BOOKING = 1 // 예약 결과 코드

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val trainerName = intent.getStringExtra("trainerName")
        val selectedTimeArray= intent.getStringArrayExtra("selectedTimes")
        val selectedDate = intent.getStringExtra("selectedDate")
        val search_ic = findViewById<ImageView>(R.id.search_ic)
        val happy_ic = findViewById<ImageView>(R.id.happy_ic)
        val profile_ic = findViewById<ImageView>(R.id.profile_ic)

         //오전과 오후를 구분하여 표시할 변수
        val timePeriod: String = when {
            selectedTimeArray?.getOrNull(0)?.contains("am", ignoreCase = true) == true -> "오전"
            selectedTimeArray?.getOrNull(0)?.contains("pm", ignoreCase = true) == true -> "오후"
            else -> "" // 오전/오후가 아닌 경우
        }

        // 예약 정보를 표시할 TextView 찾아서 텍스트 설정
        val bkInfo1 = findViewById<TextView>(R.id.bkInfo1)
        val textToShow = "$trainerName 트레이너\n $selectedDate\n $timePeriod ${selectedTimeArray?.joinToString(", ")}"
        Log.d("MainActivity", "Text to show: $textToShow")
        bkInfo1.text = textToShow
        updateReservationUI(selectedDate, selectedTimeArray ?: emptyArray())

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


    private fun updateReservationUI(selectedDate: String?, selectedTimeArray: Array<String?>) {
        val bkInfo1 = findViewById<TextView>(R.id.bkInfo1)
        val boxIc = findViewById<ImageView>(R.id.box_ic)
        val boxIc2 = findViewById<ImageView>(R.id.box_ic2)

        if (selectedDate.isNullOrBlank() || selectedTimeArray.isNullOrEmpty()) {
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

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == RESULT_CODE_BOOKING && resultCode == RESULT_OK) {
//            // Booking 액티비티에서 전달받은 예약 정보
//            val trainerName = intent.getStringExtra("trainerName")
//            val selectedTimeArray: Array<String?>? = intent.getStringArrayExtra("selectedTimes")
//            val selectedDate = intent.getStringExtra("selectedDate")
//
//            // 오전과 오후를 구분하여 표시할 변수
//            val timePeriod: String = when {
//                selectedTimeArray?.getOrNull(0)?.contains("am", ignoreCase = true) == true -> "오전"
//                selectedTimeArray?.getOrNull(0)?.contains("pm", ignoreCase = true) == true -> "오후"
//                else -> "" // 오전/오후가 아닌 경우
//            }
//            // 예약 정보를 표시할 TextView 찾아서 텍스트 설정
//            val bkInfo1 = findViewById<TextView>(R.id.bkInfo1)
//            bkInfo1.text = "$trainerName 트레이너\n $selectedDate\n $timePeriod ${selectedTimeArray?.joinToString(", ")}"
//            updateReservationUI(selectedDate, selectedTimeArray ?: emptyArray())
//        }
//    }
}