package com.example.kotlinbasic_bai1.fragment.OneContainer_TwoFragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinbasic_bai1.R
import com.example.kotlinbasic_bai1.databinding.ActivityFragmentContainerViewBinding

class ContainerView1 : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentContainerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentContainerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add Fragment A to the FragmentContainerView
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, FragmentA())
            .commit()
    }
}
