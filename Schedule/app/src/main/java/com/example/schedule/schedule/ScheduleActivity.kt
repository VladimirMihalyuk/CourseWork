package com.example.schedule.schedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.schedule.R
import com.example.schedule.app.ScheduleApplication
import com.example.schedule.firestore.Group
import com.example.schedule.gropPicker.GroupViewModel
import kotlinx.android.synthetic.main.activity_schedule.*
import javax.inject.Inject

val KEY = "KEY"

class ScheduleActivity : AppCompatActivity() {

    private lateinit var demoCollectionPagerAdapter: DemoCollectionPagerAdapter


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: ScheduleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        (application as ScheduleApplication).appComponent.inject(this)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ScheduleViewModel::class.java)

        val group = intent.getParcelableExtra<Group>(KEY)

        if(group != null){
            viewModel.setGroup(group)
        }


        viewModel.getSchedule()

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

