package com.example.coachconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView


class TrainerList : AppCompatActivity() {
    lateinit var dbHelper: DBHelper
    lateinit var sqlitedb : SQLiteDatabase
    lateinit var layout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trainer_list)

        dbHelper = DBHelper(this)

        displayTrainers()
    }

    private fun displayTrainers() {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_TRAINERS}", null)

        val dataList: ArrayList<String> = ArrayList()

        while (cursor.moveToNext()) {
            val trainerName = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_NAME))
            val trainerEducation =
                cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_EDUCATION))
            val trainerQualification =
                cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_QUALIFICATION))
            val trainerHashtag =
                cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_HASHTAG))
            val trainerLocation =
                cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_LOCATION))

            // 내부 트레이너 목록을 가져와서 dataList에 추가합니다.
            dataList.add("Name: $trainerName, Education: $trainerEducation, Qualification: $trainerQualification, Hashtag: $trainerHashtag, Location: $trainerLocation")
        }

        cursor.close()
        db.close()
        val listView: ListView = findViewById(R.id.listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
        listView.adapter = adapter

        val home_ic = findViewById<ImageView>(R.id.home_ic)
        val happy_ic = findViewById<ImageView>(R.id.happy_ic)
        val profile_ic = findViewById<ImageView>(R.id.profile_ic)


        // 하단 바 클릭 시

        home_ic.setOnClickListener {
            // 효과 (아이콘 축소)
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                val intent = Intent(this, MainActivity::class.java)
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
}
