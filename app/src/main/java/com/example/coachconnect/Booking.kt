package com.example.coachconnect

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.util.Calendar

class Booking : AppCompatActivity() {
    // 시간 버튼
    private var clickedTime: String = ""

    // 선택된 시간을 저장하는 리스트
    private val selectedTimeArray: MutableList<String> = mutableListOf()

    // 각 버튼 클릭 시 호출되는 함수
    private fun onTimeButtonClick(button: Button) {
        // 선택된 버튼의 텍스트(시간)를 가져와서 저장
        clickedTime = button.text.toString()
        // 이미 선택된 시간인지 확인
        if (selectedTimeArray.contains(clickedTime)) {
            // 이미 선택된 경우, 선택을 해제하고 스타일을 원래대로 변경
            selectedTimeArray.remove(clickedTime)
            setButtonStyle(button, defaultStyle)
        } else {
            // 선택되지 않은 경우, 최대 두 개까지만 선택 가능
            if (selectedTimeArray.size < 2) {
                selectedTimeArray.add(clickedTime)
                setButtonStyle(button, clickedStyle)
            } else {
                showToast("최대 두 개의 시간만 선택 가능합니다.")
            }
        }
    }
    private fun getAmPmFromButtonId(buttonId: Int): String {
        return when {
            buttonId.toString().contains("am", ignoreCase = true) -> "오전"
            buttonId.toString().contains("pm", ignoreCase = true) -> "오후"
            else -> ""
        }
    }

    // 버튼에 스타일 적용하는 함수
    private fun setButtonStyle(button: Button, style: GradientDrawable) {
        button.background = style
    }

    // 토스트 메시지를 표시하는 함수
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    companion object {
        const val DIALOG_DATE = 1
    }

    // 버튼 기본 스타일 설정
    private val defaultStyle by lazy {
        getDrawable(R.drawable.rounded_btn3) as GradientDrawable
    }

    // 클릭된 상태의 스타일 설정
    private val clickedStyle by lazy {
        getDrawable(R.drawable.rounded_btn2) as GradientDrawable
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.booking)

        val home_ic3 = findViewById<ImageView>(R.id.home_ic3)
        val search_ic3 = findViewById<ImageView>(R.id.search_ic3)
        val happy_ic3 = findViewById<ImageView>(R.id.happy_ic3)
        val profile_ic3 = findViewById<ImageView>(R.id.profile_ic3)
        val trName2 = findViewById<TextView>(R.id.trName2)

        val trainerName = intent.getStringExtra("trainerName")
        val trainerLocation = intent.getStringExtra("trainerLocation")

        trName2.text = trainerName

        val btn9am = findViewById<Button>(R.id.btn9am)
        val btn10am = findViewById<Button>(R.id.btn10am)
        val btn11am = findViewById<Button>(R.id.btn11am)
        val btn12pm = findViewById<Button>(R.id.btn12pm)
        val btn1pm = findViewById<Button>(R.id.btn1pm)
        val btn2pm = findViewById<Button>(R.id.btn2pm)
        val btn3pm = findViewById<Button>(R.id.btn3pm)
        val btn4pm = findViewById<Button>(R.id.btn4pm)
        val btn5pm = findViewById<Button>(R.id.btn5pm)
        val btn6pm = findViewById<Button>(R.id.btn6pm)
        val btn7pm = findViewById<Button>(R.id.btn7pm)
        val btn8pm = findViewById<Button>(R.id.btn8pm)
        val btn9pm = findViewById<Button>(R.id.btn9pm)
        val btn10pm = findViewById<Button>(R.id.btn10pm)
        val btnBook2 = findViewById<Button>(R.id.btnBook2)

        // 각 버튼에 초기 스타일 적용
        setButtonStyle(btn9am, defaultStyle)
        setButtonStyle(btn10am, defaultStyle)
        setButtonStyle(btn11am, defaultStyle)
        setButtonStyle(btn12pm, defaultStyle)
        setButtonStyle(btn1pm, defaultStyle)
        setButtonStyle(btn2pm, defaultStyle)
        setButtonStyle(btn3pm, defaultStyle)
        setButtonStyle(btn4pm, defaultStyle)
        setButtonStyle(btn5pm, defaultStyle)
        setButtonStyle(btn6pm, defaultStyle)
        setButtonStyle(btn7pm, defaultStyle)
        setButtonStyle(btn8pm, defaultStyle)
        setButtonStyle(btn9pm, defaultStyle)
        setButtonStyle(btn10pm, defaultStyle)

        // 각 버튼에 클릭 리스너 등록
        btn9am.setOnClickListener { onTimeButtonClick(btn9am) }
        btn10am.setOnClickListener { onTimeButtonClick(btn10am) }
        btn11am.setOnClickListener { onTimeButtonClick(btn11am) }
        btn12pm.setOnClickListener { onTimeButtonClick(btn12pm) }
        btn1pm.setOnClickListener { onTimeButtonClick(btn1pm) }
        btn2pm.setOnClickListener { onTimeButtonClick(btn2pm) }
        btn3pm.setOnClickListener { onTimeButtonClick(btn3pm) }
        btn4pm.setOnClickListener { onTimeButtonClick(btn4pm) }
        btn5pm.setOnClickListener { onTimeButtonClick(btn5pm) }
        btn6pm.setOnClickListener { onTimeButtonClick(btn6pm) }
        btn7pm.setOnClickListener { onTimeButtonClick(btn7pm) }
        btn8pm.setOnClickListener { onTimeButtonClick(btn8pm) }
        btn9pm.setOnClickListener { onTimeButtonClick(btn9pm) }
        btn10pm.setOnClickListener { onTimeButtonClick(btn10pm) }

        // 날짜
        val dateLine = findViewById<EditText>(R.id.dateLine)
        dateLine.setOnClickListener {
            showDialog(DIALOG_DATE)
        }

        // 예약하기 버튼에 클릭 리스너 등록
        btnBook2.setOnClickListener {

            // 토스트 메시지 표시
            showToast("예약이 완료되었습니다.")

            /// 예약 정보를 MainActivity로 전달하기 위한 Intent를 생성
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("trainerName", trainerName)  // 트레이너 이름 전달
            intent.putExtra("selectedTimes", selectedTimeArray.toTypedArray())  // 선택된 시간 배열 전달
            intent.putExtra("selectedDate", dateLine.text.toString())  // 선택된 날짜 전달
            intent.putExtra("trainerLocation", trainerLocation)  // 트레이너 위치 전달

            // 선택된 시간에 대한 오전/오후 정보를 전달
            for (time in selectedTimeArray) {
                val buttonId = resources.getIdentifier("btn${time.replace(":", "").toLowerCase()}", "id", packageName)
                val amPm = getAmPmFromButtonId(buttonId)
                intent.putExtra("amPm_$time", amPm)
            }

            // MainActivity로 이동
            startActivity(intent)
        }

        // 하단 바 클릭 시

        home_ic3.setOnClickListener {
            // 효과 (아이콘 축소)
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                // 효과 제거 (아이콘 확대)
                it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }.start()
        }
        search_ic3.setOnClickListener {
            // 효과 (아이콘 축소)
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                val intent = Intent(this, TrainerList::class.java)
                startActivity(intent)
                // 효과 제거 (아이콘 확대)
                it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }
        }
        happy_ic3.setOnClickListener {
            // 효과 (아이콘 축소)
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                val intent = Intent(this, GoalsettingActivity::class.java)
                startActivity(intent)
                // 효과 제거 (아이콘 확대)
                it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }.start()
        }
        profile_ic3.setOnClickListener {
            // 효과 (아이콘 축소)
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                // 효과 제거 (아이콘 확대)
                it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }.start()
        }
    }

    // 캘린더 날짜 설정
    override fun onCreateDialog(id: Int): Dialog {


        val calendar = Calendar.getInstance()

        return when (id) {
            DIALOG_DATE -> {
                // Date
                val dateLine = findViewById<EditText>(R.id.dateLine)
                dateLine.setOnClickListener {
                    showDialog(DIALOG_DATE)
                }
                val datePickerDialog = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                        val selectedDate = "$year 년 ${(month + 1)}월 $dayOfMonth 일"
                        dateLine.setText(selectedDate) // 선택된 날짜를 EditText에 설정
                        showToast("$selectedDate 을 선택했습니다")
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )

                // 현재 날짜 이전의 날짜는 선택 불가능하도록 설정
                datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000

                datePickerDialog
            }
            else -> super.onCreateDialog(id)
        }
    }
}

