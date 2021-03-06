package com.e.tostarttimer


import android.graphics.Color
import android.media.MediaPlayer
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

    var mediaplayer : MediaPlayer?= null
    var sumOfTime: Int = 0
    var timerTask: Timer? = null
    val wiseSaingArray: Array<String> = arrayOf(
        """Nothing is as far away 
        |as one minute ago.
        """.trimMargin(),
        """ Regret for wasted time is 
        |more wasted time.
        """.trimMargin(),
        """So little time and 
        |so little to do.
         """.trimMargin(),
        """Time is an illusion. 
        |Lunchtime doubly so. """.trimMargin(),
        """Employ thy time well, 
        |if thou meanest to get leisure. """.trimMargin(),
        """Punctuality is the soul of business. 
        """.trimMargin(),
        """The future ain't what it used to be. 
        """.trimMargin(),
        """Doing a thing well is often a waste of time. 
        """.trimMargin()
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setProgressBar()
        startTimer()

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
            titleCenterText = "Time is Gold"
            titleCenterTextColor = Color.BLACK
            titleCenterTextSize = 17f

            addOnEndListener(object : CountTimeProgressView.OnEndListener {
                override fun onAnimationEnd() {
                    Toast.makeText(this@MainActivity, "Count Finished", Toast.LENGTH_SHORT).show()
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
            sumOfTime--
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
                if (seconds == 0 && minutes == 0 && hours == 0) {
                    timeText.setText("00:00:00")
                }
                timeText.setText("$timerHours : $timerMinutes : $timerSeconds")
            }
            if (sumOfTime == 0) {
                timerTask!!.cancel()
                timeText.setText("00 : 00 : 00")

            }
        }

        btn_reset.setOnClickListener() {
            timerTask!!.cancel()
            sumOfTime = 0
            countTimeProgressView.titleCenterText = wiseSaingArray[Random().nextInt(6)]
            setTimePicker()


        }

    }

    fun startTimer() {
        timeText.setText("00 : 00 : 00")

        btn_reset.setOnClickListener() {
            sumOfTime = 0
            countTimeProgressView.titleCenterText = wiseSaingArray[Random().nextInt(6)]
            setTimePicker()
            btn_reset.setText("Reset")

        }

    }

    fun storeTime(Time: Int): Int {
        sumOfTime = Time

        return sumOfTime
    }

}



