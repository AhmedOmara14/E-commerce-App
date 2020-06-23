package com.shopping.e_commerce.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.shopping.e_commerce.R

class MainActivity : AppCompatActivity() {
    lateinit var ch: ChipNavigationBar
    lateinit var frame:FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportFragmentManager().beginTransaction().replace(
            R.id.frame,
            homeFragment()
        ).commit()

        ch=findViewById(R.id.nav)
        frame=findViewById(R.id.frame)
         ch.setOnItemSelectedListener(object :ChipNavigationBar.OnItemSelectedListener{
             override fun onItemSelected(id: Int) {
                 var fragment: Fragment? = null
                 if (id == R.id.menu_home) {
                    fragment= homeFragment()
                 } else if (id == R.id.search) {
                     fragment= searchFragment()
                 }
                 else if (id == R.id.pro) {
                     fragment= favFragment()
                 }
                 else {
                     fragment= homeFragment()
                 }
                 getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit()

             }


         })
    }
}