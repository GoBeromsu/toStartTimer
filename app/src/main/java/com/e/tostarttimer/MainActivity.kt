package com.e.tostarttimer

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.ikovac.timepickerwithseconds.MyTimePickerDialog
import com.ikovac.timepickerwithseconds.TimePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.util.*
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity() {

    // 1차 목표
    // string 입력하면 카운트 다운하기
    // 2차 목표
    // 깔끔하게 다이얼로그로 입력하게 바꾸기
    // 3차 목표
    // 그래픽으로 카운트 다운 표시하기
    var sumOfTime: Int = 0
    var timerTask: Timer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTimer()
//
//        val timePicker = MyTimePickerDialog(
//            this,
//            MyTimePickerDialog.OnTimeSetListener() { timePicker: TimePicker, hoursOfDay: Int, minute: Int, seconds: Int ->
//
////                timeText.setText(
////                    String.format("%02d", hoursOfDay) +
////                            ":" + String.format("%02d", minute) +
////                            ":" + String.format("%02d", seconds)
////                )
//                sumOfTime = hoursOfDay * 60 * 60 + minute * 60 + seconds
//                var Timer = object : CountDownTimer(sumOfTime!!.toLong(), 100) {
//                    override fun onTick(millisUntilFinished: Long) {
//                        timeText.setText("${millisUntilFinished} 남았습니다")
//                    }
//
//                    override fun onFinish() {
//                        Toast.makeText(this@MainActivity, "CountDown Finished.", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//                Timer.start()
//                startTimer()
//            },
//            Calendar.HOUR_OF_DAY,
//            Calendar.MINUTE,
//            Calendar.SECOND,
//            true
//        )

//        timePicker.show()

    }

    fun setTimer() {
        val timePicker = MyTimePickerDialog(
            this,
            MyTimePickerDialog.OnTimeSetListener() { timePicker: TimePicker, hoursOfDay: Int, minute: Int, seconds: Int ->
                sumOfTime = hoursOfDay * 60 * 60 + minute * 60 + seconds
                storeTime(sumOfTime)
                startTimer()
            },
            Calendar.HOUR_OF_DAY,
            Calendar.MINUTE,
            Calendar.SECOND,
            true
        )
        timePicker.show()
    }

    fun startTimer() {

//        var Timer = object : CountDownTimer(sumOfTime.toLong(), 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                timeText.setText("${millisUntilFinished/1000} 남았습니다")
//            }
//
//            override fun onFinish() {
//                Toast.makeText(this@MainActivity, "CountDown Finished.", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//        Timer.start()
        var countDownTime = sumOfTime
        timerTask = timer(period = 1000) {

            countDownTime--

            runOnUiThread { timeText.setText("$countDownTime") }
        }
    }


    fun storeTime(Time: Int): Int {
        sumOfTime = Time
        Toast.makeText(this@MainActivity, "${sumOfTime}은 storeTime에서 호출됨", Toast.LENGTH_SHORT)
            .show()
        return sumOfTime
    }


}