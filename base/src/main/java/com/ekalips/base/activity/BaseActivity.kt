package com.ekalips.base.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.ekalips.base.providers.ToastProvider
import com.ekalips.base.state.BaseViewState
import com.ekalips.base.vm.BaseViewModel
import com.ekalips.base.vm.BaseViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by Ekalips on 2/7/18.
 */

abstract class BaseActivity<VM : BaseViewModel<BaseViewState>, DataBinding : ViewDataBinding> : DaggerAppCompatActivity() {


    lateinit var viewModel: VM
    @Inject
    internal lateinit var vmFactory: BaseViewModelFactory

    @Inject
    lateinit var toastProvider: ToastProvider

    abstract val vmClass: Class<VM>
    abstract val layoutId: Int
    abstract val brRes: Int

    var binding: DataBinding? = null

    open val inflate = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, vmFactory).get(vmClass)
        if (inflate) {
            binding = DataBindingUtil.setContentView(this, layoutId)
        }
        binding?.setVariable(brRes, viewModel)
        binding?.setLifecycleOwner(this)
        viewModel.state.toast.observe(this, Observer { it?.let { toastProvider.showToast(it) } })
        viewModel.state.back.observe(this, Observer { onBackPressed() })
    }

    override fun onStart() {
        super.onStart()
        viewModel.onActive()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onInactive()
    }
}