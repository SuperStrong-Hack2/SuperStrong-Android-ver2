package com.superstrong.android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.superstrong.android.R

/**
 * A simple [Fragment] subclass.
 * Use the [Chpasswd3Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Chpasswd3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chpasswd3, container, false)
    }

}