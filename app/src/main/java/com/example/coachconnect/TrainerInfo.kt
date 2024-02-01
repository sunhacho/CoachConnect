package com.example.coachconnect

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.content.ContextCompat

class TrainerInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trainer_info)

        val back = findViewById<ImageButton>(R.id.back)
        val btnBook = findViewById<Button>(R.id.btnBook)
        val home_ic3 = findViewById<ImageView>(R.id.home_ic3)
        val search_ic3 = findViewById<ImageView>(R.id.search_ic3)
        val happy_ic3 = findViewById<ImageView>(R.id.happy_ic3)
        val profile_ic3 = findViewById<ImageView>(R.id.profile_ic3)
        val trName = findViewById<TextView>(R.id.trName)
        val hashtag = findViewById<TextView>(R.id.hashtag)
        val location = findViewById<TextView>(R.id.location)

        val trainerName = intent.getStringExtra("trainerName")
        val trainerName1 = trainerName?.substring(0, 3)
        val trainerHashtag = intent.getStringExtra("trainerHashtag")
        val trainerLocation = intent.getStringExtra("trainerLocation")

        // 데이터베이스에서 education과 qualification 정보 읽어오기
        val dbHelper = DBHelper(this)
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_TRAINERS} WHERE ${DBHelper.KEY_NAME} = ?", arrayOf(trainerName))
        if (cursor.moveToNext()) {
            val education = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_EDUCATION))
            val qualification = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_QUALIFICATION))

//          TrainerInfo 액티비티의 TextView에 데이터 설정
            val edu = findViewById<TextView>(R.id.edu)
            val quali = findViewById<TextView>(R.id.quali)
            edu.text = education
            quali.text = qualification
        }
        cursor.close()
        db.close()

        trName.text = trainerName1
        hashtag.text = trainerHashtag
        location.text = trainerLocation

        // 예약하기 클릭 시
        btnBook.setOnClickListener {
            // booking 액티비티로 이동
            val intent = Intent(this, Booking::class.java)
            startActivity(intent)
        }

        // back 버튼 클릭 시
        back.setOnClickListener {
            // 트레이너 목록 액티비티로 이동
            val intent = Intent(this, TrainerList::class.java)
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
            }
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
            }
        }
        profile_ic3.setOnClickListener {
            // 효과 (아이콘 축소)
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                // 효과 제거 (아이콘 확대)
                it.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }
        }
    }
}
