package com.example.schedule.schedule


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.schedule.R
import com.example.schedule.app.getDate
import kotlinx.android.synthetic.main.fragment_schedule.*

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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message = arguments?.getLong(KEY_TEXT) ?: 0


        date.text = message.getDate()

        Log.d("WTF", "$message")
    }

}



