package com.example.coachconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView


class TrainerList : AppCompatActivity() {
    lateinit var dbHelper: DBHelper
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var layout: ListView
    val dataList: ArrayList<String> = ArrayList()
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trainer_list)

        dbHelper = DBHelper(this)

        // 트레이너 추가
        val tr1 = Trainer(
            id = 1,
            name = "김민서 트레이너",
            education = "서울여자대학교 체육학과 학사 졸업\n" +
                    "서울여자대학교 체육학과 석사 재학 중",
            qualification = "생활체육지도자 자격증 1급 (건강운동관리사)\n" +
                    "생활체육지도자 자격증 3급 (보디빌딩, 에어로빅)",
            hashtag = "#ESTJ #난이도중상 #호랑이트레이너",
            location = "에이블짐 노원본점"
        )
        if (!dbHelper.isTrainerExists(tr1.name)) {
            dbHelper.addTrainer(tr1)
        }
        val tr2 = Trainer(
            id = 2,
            name = "정인규 트레이너",
            education = "한국체육대학교 운동건강관리학과 졸업",
            qualification = "생활스포츠지도자2급 보디빌딩\n" +
                    "body1st 체형분석운동지도자 basic\n" +
                    "Body1st 체형분석운동지도자 advance",
            hashtag = "#ENFP #난이도상 #교정전문트레이너",
            location = "에이블짐 공릉점"
        )
        if (!dbHelper.isTrainerExists(tr2.name)) {
            dbHelper.addTrainer(tr2)
        }
        val tr3 = Trainer(
            id = 3,
            name = "이정은 트레이너",
            education = "한국체육대학교 사회체육학과 졸업",
            qualification = "생활 스포츠 지도자 2급 (보디빌딩)\n" +
                    "유아 아동 체육 지도자 1급",
            hashtag = "#ENFJ #난이도하 #학생전문트레이너",
            location = "에이블짐 공릉점"
        )
        if (!dbHelper.isTrainerExists(tr3.name)) {
            dbHelper.addTrainer(tr3)
        }
        val tr4 = Trainer(
            id = 4,
            name = "임예슬 트레이너",
            education = "고려대학교 체육교육과 졸업",
            qualification = "대한운동사협회 정회원\n" +
                    "소도구 트레이닝 수료",
            hashtag = "#ISTJ #난이도중 #다이어트전문트레이너",
            location = "에이블짐 노원본점"
        )
        if (!dbHelper.isTrainerExists(tr4.name)) {
            dbHelper.addTrainer(tr4)
        }
        val tr5 = Trainer(
            id = 5,
            name = "이승준 트레이너",
            education = "한국체육대학교 노인체육복지학과 졸업",
            qualification = "기관생명윤리 위원회 연구자 교육 이수\n" +
                    "생활체육지도자 보디빌딩 3급",
            hashtag = "#ISTP #난이도최하 #기초체력향상트레이너",
            location = "에이블짐 공릉점"
        )
        if (!dbHelper.isTrainerExists(tr5.name)) {
            dbHelper.addTrainer(tr5)
        }
        val tr6 = Trainer(
            id = 6,
            name = "김연미 트레이너",
            education = "단국대학교 대학원 스포츠 재활 석사",
            qualification = "스포츠 재활 임상 경력 15년차\n" +
                    "생활 스포츠 지도자 2급",
            hashtag = "#INFJ #난이도중상 #재활전문트레이너",
            location = "에이블짐 노원본점"
        )
        if (!dbHelper.isTrainerExists(tr6.name)) {
            dbHelper.addTrainer(tr6)
        }
        val tr7 = Trainer(
            id = 7,
            name = "강승연 트레이너",
            education = "서울대학교 체육학과 졸업",
            qualification = "산전산후 여성 전문 트레이너 수료\n" +
                    "bastm technique 수료",
            hashtag = "#ISFP #난이도중하 #여성전문트레이너",
            location = "에이블짐 공릉점"
        )
        if (!dbHelper.isTrainerExists(tr7.name)) {
            dbHelper.addTrainer(tr7)
        }
        val tr8 = Trainer(
            id = 8,
            name = "이건욱 트레이너",
            education = "한국체육대학교 태권도학과 졸업",
            qualification = "태권도 국가대표 출신\n" +
                    "생활체육지도자 보디빌딩 3급",
            hashtag = "#ENFJ #난이도상 #바디프로필전문트레이너",
            location = "에이블짐 노원본점"
        )
        if (!dbHelper.isTrainerExists(tr8.name)) {
            dbHelper.addTrainer(tr8)
        }

        val listView: ListView = findViewById(R.id.listView)
        adapter =
            object : ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val rowView = super.getView(position, convertView, parent)

                    // 여백 조절
                    rowView.setPadding(16, 23, 16, 23)

                    return rowView
                }
            }

        listView.adapter = adapter
        displayTrainers()

        // 리스트뷰 클릭 이벤트 설정
        listView.setOnItemClickListener { _, _, position, _ ->
            val clickedItemData = dataList[position]
            val intent = Intent(this, TrainerInfo::class.java)
            // 각 데이터를 개별적으로 가져오기
            val trainerName = clickedItemData.substringBefore("\n")
            val trainerHashtag = clickedItemData.substringAfter("\n").substringBefore("\n")
            val trainerLocation = clickedItemData.substringAfterLast("📍")

            // 데이터를 TrainerInfo 액티비티로 전달
            intent.putExtra("trainerName", trainerName)
            intent.putExtra("trainerHashtag", trainerHashtag)
            intent.putExtra("trainerLocation", trainerLocation)


            // 데이터베이스에서 education과 qualification 정보 읽어오기
            val dbHelper = DBHelper(this)
            val db = dbHelper.readableDatabase
            val cursor = db.rawQuery("SELECT ${DBHelper.KEY_EDUCATION}, ${DBHelper.KEY_QUALIFICATION} FROM ${DBHelper.TABLE_TRAINERS} WHERE ${DBHelper.KEY_NAME} = ?", arrayOf(trainerName))

            if (cursor.moveToFirst()) {
                val education = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_EDUCATION))
                val qualification = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_QUALIFICATION))

                // TrainerInfo 액티비티로 education과 qualification 정보 전달
                intent.putExtra("trainerEducation", education)
                intent.putExtra("trainerQualification", qualification)
            }

            cursor.close()
            db.close()

            startActivity(intent)
        }

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


    private fun displayTrainers() {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_TRAINERS}", null)
        // 이전 데이터 모두 지우기
        dataList.clear()

        while (cursor.moveToNext()) {
            val trainerName = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_NAME))
            val trainerHashtag =
                cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_HASHTAG))
            val trainerLocation =
                cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_LOCATION))

            // 중복 데이터 방지를 위해 데이터가 없을 때만 추가
            val newData = "$trainerName \n $trainerHashtag \n 📍$trainerLocation"
            if (!dataList.contains(newData)) {
                dataList.add(newData)
            }
        }
        cursor.close()
        db.close()

        adapter.notifyDataSetChanged()
    }
}



