package com.example.learnkt.net

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class ProjectFragment : Fragment() {

    lateinit var viewModel: ProjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)

        return super.onCreateView(inflater, container, savedInstanceState)
    }


    fun getData() {
        viewModel.loadProjectTree()
    }

    fun updateData() {
        viewModel.mProjectLiveData.observe(this, Observer {
            println(it.javaClass.simpleName)
            val isOk = it is List<ProjectTree>
        })
    }


    val viewModel2: ProjectViewModel by lazy {
        ViewModelProvider(this).get(ProjectViewModel::class.java)

    }

    init {
        lifecycleScope.launch {
            whenStarted {

            }

            whenCreated {

            }


        }
    }

}


