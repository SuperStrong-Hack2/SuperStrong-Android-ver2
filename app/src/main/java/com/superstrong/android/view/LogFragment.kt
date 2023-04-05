package com.superstrong.android.view;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.superstrong.android.R

public class LogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_log, container, false)
    }

    public fun newInstant() : LogFragment
    {
        val args = Bundle()
        val frag = LogFragment()
        frag.arguments = args
        return frag
    }

}
