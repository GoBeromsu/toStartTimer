package com.e.tostarttimer

import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.ikovac.timepickerwithseconds.MyTimePickerDialog
import com.ikovac.timepickerwithseconds.TimePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.util.*


class MainActivity : AppCompatActivity() {

    // 1차 목표
    // string 입력하면 카운트 다운하기
    // 2차 목표
    // 깔끔하게 다이얼로그로 입력하게 바꾸기
    // 3차 목표
    // 그래픽으로 카운트 다운 표시하기

    val Timer: CountDownTimer? = null

    @RequiresApi(Build.VERSION_CODES.O)
    var now = LocalDate.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val timePicker = MyTimePickerDialog(
            this,
            MyTimePickerDialog.OnTimeSetListener() { timePicker: TimePicker, hoursOfDay: Int, minute: Int, seconds: Int ->

                timeText.setText(
                     String.format("%02d", hoursOfDay) +
                            ":" + String.format("%02d", minute) +
                            ":" + String.format("%02d", seconds)
                );
            },
            Calendar.HOUR_OF_DAY,
            Calendar.MINUTE,
            Calendar.SECOND,
            true
        )

        timePicker.show()

    }

    private fun startTimer() {

    }

    private fun stopTimer() {

    }

    private fun drawTimer() {}
}