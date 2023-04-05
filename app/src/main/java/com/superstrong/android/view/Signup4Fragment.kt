package com.superstrong.android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.superstrong.android.R
import com.superstrong.android.databinding.FragmentSignup4Binding

/**
 * A simple [Fragment] subclass.
 * Use the [Signup4Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Signup4Fragment : Fragment() {
    private var _binding: FragmentSignup4Binding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup4, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}