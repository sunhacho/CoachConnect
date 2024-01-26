package com.example.coachconnect
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        Log.d("com.example.coachconnect.LoginActivity", "onCreate called")


        // 로그인 버튼 클릭 시
        val loginButton: Button = findViewById(R.id.buttonLogin)
        loginButton.setOnClickListener {
            handleLogin()
        }


        // TextView(회원가입) 클릭 시
        val textViewSignUp: TextView = findViewById(R.id.textView2)
        textViewSignUp.setOnClickListener {
            // 회원가입 액티비티로 이동
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    // 회원가입 정보 저장
    private fun saveUserInfo(username: String, password: String) {
        val sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()
    }

    // 로그인 시 정보 비교
    private fun checkLogin(username: String, password: String): Boolean {
        val sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")

        // 저장된 정보와 입력된 정보를 비교
        return username == savedUsername && password == savedPassword
    }

    // 로그인 버튼 클릭 시 호출
    private fun handleLogin() {
        val username = findViewById<EditText>(R.id.editTextEmail).text.toString()
        val password = findViewById<EditText>(R.id.editTextEmail3).text.toString()

        if (checkLogin(username, password)) {
            // 로그인 성공
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // 현재 LoginActivity 종료

        } else {
            // 로그인 실패
            // 여기에 로그인 실패 시 수행할 동작을 추가하세요.
            Log.d("LoginActivity", "Login failed")

            // 실패 메시지를 화면에 표시
            val loginMessageTextView: TextView = findViewById(R.id.loginMessageTextView)
            loginMessageTextView.text = "로그인 정보가 일치하지 않습니다."
            loginMessageTextView.visibility = View.VISIBLE
        }
    }
}


