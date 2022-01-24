package com.perficient.chartpoc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.perficient.chartpoc.databinding.ActivityMainBinding
import com.perficient.chartpoc.source.MockSource

class MainActivity : AppCompatActivity() {
    private lateinit var biding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding.root)
        biding.timeFrameChart.setChart(MockSource.loadData())
    }


}