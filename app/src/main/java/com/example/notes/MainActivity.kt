package com.example.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.viewpager)

        val adapter = Adapter(supportFragmentManager)
        viewPager.adapter = adapter

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }
}
