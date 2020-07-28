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

        with(countTimeProgressView) {
            startAngle = 0f
            countTime = 6000L
            textStyle = CountTimeProgressView.TextStyle.SECOND
            borderWidth = 4f
            borderBottomColor = Color.GRAY
            borderDrawColor = Color.RED
            backgroundColorCenter = Color.WHITE
            markBallFlag = true
            markBallWidth = 6f
            markBallColor = Color.GREEN
            titleCenterText = "jump（%s）s"
            titleCenterTextColor = Color.BLACK
            titleCenterTextSize = 16f
            addOnEndListener(object : CountTimeProgressView.OnEndListener {
                override fun onAnimationEnd() {
                    Toast.makeText(this@MainActivity, "时间到", Toast.LENGTH_SHORT).show()
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

        setTimePicker()


    }

    fun setTimePicker() {
        val timePicker = MyTimePickerDialog(
            this,
            MyTimePickerDialog.OnTimeSetListener() { timePicker: TimePicker, hoursOfDay: Int, minute: Int, seconds: Int ->
                sumOfTime = hoursOfDay * 60 * 60 + minute * 60 + seconds
                storeTime(sumOfTime)
//                startTimer()
                countTimeProgressView.countTime = sumOfTime.toLong() * 1000L
                countTimeProgressView.startCountTimeAnimation()
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



