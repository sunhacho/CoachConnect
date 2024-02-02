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

class MainActivity : AppCompatActivity() {
    private val RESULT_CODE_BOOKING = 1 // 예약 결과 코드

    private fun parseYourTime(timeString: String?): Date? {
        return try {
            val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
            formatter.parse(timeString)
        } catch (e: Exception) {
            null
        }
    }

    private val someActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // 처리할 내용
                val trainerName = result.data?.getStringExtra("trainerName")
                val selectedTimeArray: Array<String?>? =
                    result.data?.getStringArrayExtra("selectedTimes")
                val selectedDate = result.data?.getStringExtra("selectedDate")

                // 오전과 오후를 구분하여 표시할 변수
                val timePeriod: String = when {
                    selectedTimeArray?.getOrNull(0)
                        ?.contains("am", ignoreCase = true) == true -> "오전"
                    selectedTimeArray?.getOrNull(0)
                        ?.contains("pm", ignoreCase = true) == true -> "오후"
                    else -> "" // 오전/오후가 아닌 경우
                }

                // 예약 정보를 표시할 TextView 찾아서 텍스트 설정
                val bkInfo1 = findViewById<TextView>(R.id.bkInfo1)
                val formattedTime =
                    selectedTimeArray?.joinToString(", ") { it ?: "" } ?: ""
                val displayText =
                    "$trainerName 트레이너\n $selectedDate\n ${if (timePeriod.isNotBlank()) "$timePeriod " else ""}$formattedTime"
                bkInfo1.text = displayText
                updateReservationUI(selectedDate, selectedTimeArray ?: emptyArray())

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val trainerName = intent.getStringExtra("trainerName")
        val selectedTimeArray = intent.getStringArrayExtra("selectedTimes")
        val selectedDate = intent.getStringExtra("selectedDate")

        //오전과 오후를 구분하여 표시할 변수
        val timePeriod: String = when {
            selectedTimeArray?.getOrNull(0)
                ?.contains("am", ignoreCase = true) == true -> "오전"
            selectedTimeArray?.getOrNull(0)
                ?.contains("pm", ignoreCase = true) == true -> "오후"
            else -> "" // 오전/오후가 아닌 경우
        }

        // 예약 정보를 표시할 TextView 찾아서 텍스트 설정
        val bkInfo1 = findViewById<TextView>(R.id.bkInfo1)
        val formattedTime = selectedTimeArray?.joinToString(", ") { it ?: "" } ?: ""

        val displayText =
            "$trainerName 트레이너\n $selectedDate\n ${if (timePeriod.isNotBlank()) "$timePeriod " else ""}$formattedTime"
        bkInfo1.text = displayText
        updateReservationUI(selectedDate, selectedTimeArray ?: emptyArray())

        // 하단 바 클릭 시
        val search_ic = findViewById<ImageView>(R.id.search_ic)
        search_ic.setOnClickListener {
            // 효과 (아이콘 축소)
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                someActivityResultLauncher.launch(Intent(this, TrainerList::class.java))
                // 효과 제거 (아이콘 확대)
                it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }
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
}
