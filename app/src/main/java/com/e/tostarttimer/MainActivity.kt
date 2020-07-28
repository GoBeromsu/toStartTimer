package com.e.tostarttimer

import android.animation.TimeInterpolator
import android.content.Context
import android.graphics.Color
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

        timeProgressBar.apply {
            progressMax = 100f
            setProgressWithAnimation(sumOfTime.toFloat(), 1000)
            progressBarWidth = 5f
            backgroundProgressBarWidth = 7f
            progressBarColor = Color.BLUE
            progressMax = 100f
        }

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


        var countDownTime = sumOfTime
        var percentage: Float = 100.0f
        timerTask = timer(period = 1000) {

            countDownTime--

            runOnUiThread {
//                timeText.setText("$countDownTime")

            }
        }
    }


    fun storeTime(Time: Int): Int {
        sumOfTime = Time
        Toast.makeText(this@MainActivity, "${sumOfTime}은 storeTime에서 호출됨", Toast.LENGTH_SHORT)
            .show()
        return sumOfTime
    }


}


