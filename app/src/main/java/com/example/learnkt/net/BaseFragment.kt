package com.example.learnkt.net

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NavUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {
    var loadingDialog: LoadingDialog? = null

    var mviewModel: VM? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mviewModel = getViewModel()
        loadingDialog = LoadingDialog(view.context)
        mviewModel?.errorLiveData?.observe(viewLifecycleOwner, Observer {
            mviewModel?.loading?.observe(viewLifecycleOwner, Observer { show ->
                context?.let {
                    if (show) {
                        loadingDialog?.showDialog(it, false)
                    } else {
                        loadingDialog?.dismissDialog()
                    }

                }

            })


        })
    }


    override fun onResume() {
        super.onResume()
        activity
    }

    abstract fun getViewModel(): VM

}