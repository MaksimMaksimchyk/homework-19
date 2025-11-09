package com.example.homework_19

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DynamicFragmentAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = mutableListOf<Fragment>()


    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        notifyItemInserted(fragments.size - 1)
    }

    fun removeFragment(position: Int) {
        if (position in 0 until fragments.size) {
            fragments.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getFragmentAt(position: Int): Fragment? {
        return if (position in 0 until fragments.size) fragments[position] else null
    }

    fun getFragments(): List<Fragment> {
        return fragments.toList()
    }


    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }
}