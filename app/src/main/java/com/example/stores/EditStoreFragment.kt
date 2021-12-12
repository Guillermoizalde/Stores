package com.example.stores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stores.databinding.FragmentEditStoreBinding

class EditStoreFragment : Fragment() {

    private lateinit var mBiding: FragmentEditStoreBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        mBiding = FragmentEditStoreBinding.inflate(inflater, container, false)

        return mBiding.root
    }

}