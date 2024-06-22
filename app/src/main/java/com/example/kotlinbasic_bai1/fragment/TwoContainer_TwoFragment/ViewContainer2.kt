package com.example.kotlinbasic_bai1.fragment.TwoContainer_TwoFragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinbasic_bai1.R
import com.example.kotlinbasic_bai1.databinding.ActivityFragmentContainerBinding

class ViewContainer2 : AppCompatActivity() {

    private lateinit var binding:ActivityFragmentContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView1, FragmentC())
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView2, FragmentD())
            .commit()
    }
}
