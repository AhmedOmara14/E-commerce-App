package com.shopping.e_commerce.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.shopping.e_commerce.R

class homeFragment : Fragment() {
    lateinit var home: Button
    lateinit var packpack: Button
    lateinit var shoe: Button
    lateinit var search:TextView
    companion object {
        fun newInstance() = homeFragment()
    }


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.getSupportFragmentManager()?.beginTransaction()?.replace(
            R.id.frame2,
            home_itemFragment()
        )?.commit()

        return  inflater.inflate(R.layout.home_fragment, container, false)

    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search=view.findViewById(R.id.search_items)
        home=view.findViewById(R.id.home_btn);
        packpack=view.findViewById(R.id.packpack);
        shoe=view.findViewById(R.id.shoe);

        search.setOnClickListener {
            var fragment: Fragment? = null
           fragment=searchFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()?.replace(R.id.frame, fragment)?.commit()

        }
        home.setOnClickListener {
            activity?.getSupportFragmentManager()?.beginTransaction()?.replace(
                R.id.frame2,
                home_itemFragment()
            )?.commit()
        }
        packpack.setOnClickListener {
            activity?.getSupportFragmentManager()?.beginTransaction()?.replace(
                R.id.frame2,
                backpackFragment()
            )?.commit()
        }
        shoe.setOnClickListener {
            activity?.getSupportFragmentManager()?.beginTransaction()?.replace(
                R.id.frame2,
                shoeFragment()
            )?.commit()
        }
    }


}