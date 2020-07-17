package com.e.tostarttimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class MainActivity : AppCompatActivity() {

    // 1차 목표
    // string 입력하면 카운트 다운하기
    // 2차 목표
    // 깔끔하게 다이얼로그로 입력하게 바꾸기
    // 3차 목표
    // 그래픽으로 카운트 다운 표시하기

    val Timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun startTimer() {

    }

    private fun stopTimer() {

    }

    private fun drawTimer() {}
}