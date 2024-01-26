package com.example.coachconnect


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.coachconnect.R
import com.google.android.material.snackbar.Snackbar
import android.os.Handler

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        // com.example.coachconnect.SignUpActivity 초기화 코드 작성


        // ... 다른 코드
        // 회원가입 버튼 클릭 시 호출

        val signUpButton: Button = findViewById(R.id.buttonSignup)
        signUpButton.setOnClickListener {
            // 회원가입 처리
            handleSignUp()
        }
    }

    private fun handleSignUp() {
        val usernameEditText: EditText = findViewById(R.id.editTextEmail)
        val passwordEditText: EditText = findViewById(R.id.editTextEmail3)

        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        // 아이디와 비밀번호 저장
        saveUserInfo(username, password)

        // 회원가입 완료 메시지 표시
        Snackbar.make(findViewById(R.id.buttonSignup), "회원가입이 완료되었습니다.", Snackbar.LENGTH_SHORT).show()

        // 일정 시간 후에 로그인 화면으로 이동
        Handler().postDelayed({
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
            finish() // 현재 액티비티 종료
        }, 3500) // 3.5초 후에 실행되도록 지연
    }


    private fun saveUserInfo(username: String, password: String) {
        val sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()
    }
}





