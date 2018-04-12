package com.ekalips.base.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

abstract class BaseFragmentNavigator constructor(private val fragmentManager: FragmentManager) {

    abstract val containerId: Int

    fun replaceFragment(newFragment: Fragment, tag: String? = null, addToBackStack: Boolean = false) {
        val transition = fragmentManager.beginTransaction()
                .replace(containerId, newFragment, tag)
        if (addToBackStack) {
            transition.addToBackStack(tag)
        }
        transition.commit()
    }

    fun getFragment(tag: String): Fragment? = fragmentManager.findFragmentByTag(tag)

    fun clearCurrentFragment() {
        val currentFragment: Fragment? = fragmentManager.findFragmentById(containerId)
        if (currentFragment != null) {
            fragmentManager.beginTransaction()
                    .remove(currentFragment)
                    .commit()
        }
    }

}