package com.superstrong.android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.superstrong.android.databinding.FragmentCoinlistBinding
import com.superstrong.android.viewmodel.LogVModel

class CoinListFragment : Fragment() {
    private var _binding: FragmentCoinlistBinding? = null
    private val binding get() = _binding!!
    private val vmodel: LogVModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCoinlistBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.coinBalance1.text = "0"
        binding.coinBalance2.text = "0"
        binding.coinBalance3.text = "0"
        vmodel.balance.observe(viewLifecycleOwner, Observer {
            binding.coinBalance1.text = it.btc.toString()
            binding.coinBalance2.text = it.eth.toString()
            binding.coinBalance3.text = it.doge.toString()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}