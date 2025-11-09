package com.example.homework_19

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: DynamicFragmentAdapter
    private lateinit var btnAddFragment: Button
    private lateinit var btnRemoveFragment: Button

    private var fragmentCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setupViewPager()
        setupClickListeners()

    }

    private fun setupClickListeners() {
        btnAddFragment.setOnClickListener {
            addNewFragment()
        }

        btnRemoveFragment.setOnClickListener {
            removeCurrentFragment()
        }
    }

    private fun setupViewPager() {
        adapter = DynamicFragmentAdapter(this)
        viewPager.adapter = adapter
    }

    private fun initViews() {
        viewPager = findViewById(R.id.viewPager2)
        btnAddFragment = findViewById(R.id.btnAddFragment)
        btnRemoveFragment = findViewById(R.id.btnRemoveFragment)
    }


    private fun addNewFragment() {
        fragmentCounter++
        val fragment = MyFragment.newInstance("Fragment № $fragmentCounter")
        adapter.addFragment(fragment)

        // Переход к новому фрагменту
        viewPager.setCurrentItem(adapter.itemCount - 1, true)

        Snackbar.make(
            findViewById<View>(android.R.id.content),
            "Фрагмент добавлен",
            Snackbar.LENGTH_LONG
        ).setAction("ОТМЕНА") {
            removeCurrentFragment()
        }.show()
    }


    private fun removeCurrentFragment() {
        val currentItem = viewPager.currentItem
        if (adapter.itemCount > 0) {
            adapter.removeFragment(currentItem)
            fragmentCounter--
        }
    }
}