package com.example.schedule.gropPicker

import android.content.Intent
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
import com.example.schedule.schedule.KEY
import com.example.schedule.schedule.ScheduleActivity
import kotlinx.android.synthetic.main.activity_group.*










class GroupActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: GroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.schedule.R.layout.activity_group)

        (application as ScheduleApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(GroupViewModel::class.java)

        viewModel.group.observe(this, Observer {
            if(it != null){
                val intent = Intent(this, ScheduleActivity::class.java)
                intent.putExtra(KEY, it)
                viewModel.resetGroup()
                startActivity(intent)
            }
        })

        viewModel.map.observe(this, Observer { map ->
            course.adapter = makeAdapter(map.keys.toList().map{"$it курс" })
            if(map.keys.firstOrNull() != null){
                group.adapter =
                    makeAdapter(map[map.keys.firstOrNull() ?: 0]?.
                        map { it.getGroupString()} ?: listOf())
            }
        })

        viewModel.isLoading.observe(this, Observer {
            if(it == true){
                startLoading()
            }else{
                stopLoading()
            }
        })

        course.onItemSelectedListener = this

        viewModel.getGroups()

        pick.setOnClickListener {
            viewModel.pickGroup(course.selectedItem.toString(), group.selectedItem.toString())
        }
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

    private fun startLoading(){
        constraintLayout.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading(){
        constraintLayout.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }
}
