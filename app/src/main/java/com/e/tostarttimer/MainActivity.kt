package com.e.tostarttimer


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ikovac.timepickerwithseconds.MyTimePickerDialog
import com.ikovac.timepickerwithseconds.TimePicker
import com.sfyc.ctpv.CountTimeProgressView

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity() {


    var sumOfTime: Int = 0
    var timerTask: Timer? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setProgressBar()
        setTimePicker()

    }

    fun setTimePicker() {
        val timePicker = MyTimePickerDialog(
            this,
            MyTimePickerDialog.OnTimeSetListener() { timePicker: TimePicker, hoursOfDay: Int, minute: Int, seconds: Int ->
                sumOfTime = hoursOfDay * 60 * 60 + minute * 60 + seconds
                storeTime(sumOfTime)

                countTimeProgressView.countTime = sumOfTime.toLong() * 1000L
                startTimer(hoursOfDay, minute, seconds)
                countTimeProgressView.startCountTimeAnimation()
            },
            Calendar.HOUR_OF_DAY,
            Calendar.MINUTE,
            Calendar.SECOND,
            true
        )


        timePicker.show()

    }

    fun setProgressBar() {
        with(countTimeProgressView) {
            startAngle = 0f
            countTime = 6000L
            borderWidth = 7f
            borderBottomColor = Color.GRAY
            borderDrawColor = Color.BLACK
            backgroundColorCenter = Color.WHITE
            markBallFlag = true
            markBallWidth = 9f
            markBallColor = Color.BLUE
            titleCenterText = "Time Is Gold"
            titleCenterTextColor = Color.BLACK
            titleCenterTextSize = 80f
            addOnEndListener(object : CountTimeProgressView.OnEndListener {
                override fun onAnimationEnd() {
                    Toast.makeText(this@MainActivity, "Time is End", Toast.LENGTH_SHORT).show()
                }

                override fun onClick(overageTime: Long) {
                    if (countTimeProgressView.isRunning) {
                        countTimeProgressView.cancelCountTimeAnimation()
                        Log.e("overageTime", "overageTime = " + overageTime)
                    } else {
                        countTimeProgressView.startCountTimeAnimation()
                    }
                }
            })
        }
        countTimeProgressView.startCountTimeAnimation()

    }

    fun startTimer(hours: Int, minutes: Int, seconds: Int) {

        var seconds = seconds
        var minutes = minutes
        var hours = hours

        timerTask = timer(period = 1000) {

            seconds--
            if (seconds < 0) {
                minutes--
                seconds = 59
            }
            if (minutes < 0) {
                hours--
                minutes = 59
            }

            var timerSeconds = seconds.toString()
            var timerMinutes = minutes.toString()
            var timerHours = hours.toString()


            if (seconds < 10) {
                timerSeconds = "0$seconds"
            }
            if (minutes < 10) {
                timerMinutes = "0$minutes"
            }
            if (hours < 10) {
                timerHours = "0$hours"
            }
            runOnUiThread {

                timeText.setText("$timerHours : $timerMinutes : $timerSeconds")
            }
        }

        btn_reset.setOnClickListener() {
            timerTask!!.cancel()
            sumOfTime = 0
            setTimePicker()
        }

    }


    fun storeTime(Time: Int): Int {
        sumOfTime = Time
        Toast.makeText(this@MainActivity, "${sumOfTime}은 storeTime에서 호출됨", Toast.LENGTH_SHORT)
            .show()
        return sumOfTime
    }
}



