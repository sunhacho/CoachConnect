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
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.util.Calendar

class Booking : AppCompatActivity() {

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
        btn9am.setOnClickListener { onButtonClick(btn9am) }
        btn10am.setOnClickListener { onButtonClick(btn10am) }
        btn11am.setOnClickListener { onButtonClick(btn11am) }
        btn12pm.setOnClickListener { onButtonClick(btn12pm) }
        btn1pm.setOnClickListener { onButtonClick(btn1pm) }
        btn2pm.setOnClickListener { onButtonClick(btn2pm) }
        btn3pm.setOnClickListener { onButtonClick(btn3pm) }
        btn4pm.setOnClickListener { onButtonClick(btn4pm) }
        btn5pm.setOnClickListener { onButtonClick(btn5pm) }
        btn6pm.setOnClickListener { onButtonClick(btn6pm) }
        btn7pm.setOnClickListener { onButtonClick(btn7pm) }
        btn8pm.setOnClickListener { onButtonClick(btn8pm) }
        btn9pm.setOnClickListener { onButtonClick(btn9pm) }
        btn10pm.setOnClickListener { onButtonClick(btn10pm) }

        // 예약하기 버튼에 클릭 리스너 등록
        btnBook2.setOnClickListener {
            // 토스트 메시지 표시
            showToast("예약이 완료되었습니다.")

            // MainActivity로 돌아가기
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        // 하단 바 클릭 시
        home_ic3.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        happy_ic3.setOnClickListener {
            val intent = Intent(this, GoalsettingActivity::class.java)
            startActivity(intent)
        }
        profile_ic3.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    // 캘린더 날짜 설정
    override fun onCreateDialog(id: Int): Dialog {

        // Date
        val dateLine = findViewById<EditText>(R.id.dateLine)
        dateLine.setOnClickListener {
            showDialog(DIALOG_DATE)
        }

        val calendar = Calendar.getInstance()

        return when (id) {
            DIALOG_DATE -> {
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


    // 각 버튼 클릭 시 호출되는 함수
    private fun onButtonClick(button: Button) {
        // 클릭된 버튼에 대한 스타일 적용
        setButtonStyle(button, clickedStyle)
    }

    // 버튼에 스타일 적용하는 함수
    private fun setButtonStyle(button: Button, style: GradientDrawable) {
        button.background = style
    }

    // 토스트 메시지를 표시하는 함수
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
