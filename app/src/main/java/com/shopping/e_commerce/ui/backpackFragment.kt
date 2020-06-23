package com.shopping.e_commerce.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shopping.e_commerce.R
import com.shopping.e_commerce.data.Repositry
import com.shopping.e_commerce.pojo.items
import java.lang.Exception


class backpackFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var repositry: Repositry
    lateinit var adapter: itemAdapter

    companion object {
        fun newInstance() = homeFragment()
        private const val TAG = "backpackFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_backpack, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            recyclerView=view.findViewById(R.id.recycler_Backpack)
            recyclerView.layoutManager=LinearLayoutManager(context)
            adapter= context?.let { itemAdapter(it) }!!
            repositry=ViewModelProviders.of(this).get(Repositry::class.java)
            repositry.getinfo_packpack().observe(viewLifecycleOwner, Observer<ArrayList<items>> {item->
                adapter.setlist(item)
                adapter.notifyDataSetChanged()
                recyclerView.adapter=adapter
            })
        }
        catch (e :Exception){
            Log.d(Companion.TAG, "onViewCreated: "+e.message)
        }

    }


}