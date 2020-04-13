package com.example.schedule.schedule


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.schedule.R
import com.example.schedule.app.ScheduleApplication
import com.example.schedule.app.getDate
import kotlinx.android.synthetic.main.fragment_schedule.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment() {


    companion object {
        private val KEY_TEXT = "special_text"

        fun newInstance(time: Long): ScheduleFragment {
            val fragment = ScheduleFragment()
            val args: Bundle = Bundle()
            args.putLong(KEY_TEXT, time)
            fragment.arguments = args
            return fragment
        }
    }


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message = arguments?.getLong(KEY_TEXT) ?: 0

        ((activity as ScheduleActivity).application as ScheduleApplication)
            .appComponent.inject(this)

        viewModel =
            ViewModelProvider((activity as ScheduleActivity), viewModelFactory)
                .get(ScheduleViewModel::class.java)

        viewModel.getLessons(message)

        date.text = message.getDate()
    }

}



