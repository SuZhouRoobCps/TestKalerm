package com.example.learnkt.jetpack

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenResumed
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.launch

class MyFragment : Fragment(){


    init {
        lifecycleScope.launch {
            whenCreated {  }
            whenResumed {  }
            whenStarted {  }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {

        }
    }
}