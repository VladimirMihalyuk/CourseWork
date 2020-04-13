package com.example.schedule.schedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.schedule.R
import kotlinx.android.synthetic.main.activity_schedule.*

val KEY = "KEY"

class ScheduleActivity : AppCompatActivity() {

    private lateinit var demoCollectionPagerAdapter: DemoCollectionPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        demoCollectionPagerAdapter = DemoCollectionPagerAdapter(supportFragmentManager)
        pager.adapter = demoCollectionPagerAdapter
    }
}




class DemoCollectionPagerAdapter(fm: FragmentManager)
    :  FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)  {

    private var count = 10000
    override fun getCount(): Int  = count

    private val time = System.currentTimeMillis()

    override fun getItem(i: Int): Fragment {
        val fragment = ScheduleFragment.newInstance(time + i * 86400000)
        return fragment
    }




}

private const val ARG_OBJECT = "object"

