package com.example.schedule.gropPicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.schedule.app.ScheduleApplication
import javax.inject.Inject
import android.widget.ArrayAdapter
import com.example.schedule.app.getGroupString
import kotlinx.android.synthetic.main.activity_group.*








class GroupActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: GroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.schedule.R.layout.activity_group)

        (application as ScheduleApplication).appComponent.inject(this)

        viewModel =ViewModelProvider(this, viewModelFactory).get(GroupViewModel::class.java)



        viewModel.map.observe(this, Observer { map ->
            course.adapter = makeAdapter(map.keys.toList().map{"$it курс" })
            if(map.keys.firstOrNull() != null){
                group.adapter =
                    makeAdapter(map[map.keys.firstOrNull() ?: 0]?.
                        map { it.getGroupString()} ?: listOf())
            }
        })

        course.onItemSelectedListener = this

        viewModel.getGroups()
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        group.adapter = makeAdapter(viewModel.getGroupsForCourse(pos).
            map { it.getGroupString()})
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    private fun makeAdapter(list: List<String>): ArrayAdapter<String>{
        val arrayAdapter =
            ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                list)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return arrayAdapter
    }

    fun startLoading(){
        constraintLayout.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    fun stopLoading(){
        constraintLayout.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }
}
