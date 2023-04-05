package com.superstrong.android.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.superstrong.android.view.CoinListFragment
import com.superstrong.android.view.LogFragment

class TabFragmentAdapter (fm : FragmentManager): FragmentPagerAdapter(fm)  {
    //position 에 따라 원하는 Fragment로 이동시키기
    override fun getItem(position: Int): Fragment {
        val fragment =  when(position)
        {
            0-> CoinListFragment().newInstant()
            1-> LogFragment().newInstant()
            else -> CoinListFragment().newInstant()
        }
        return fragment
    }

    //tab의 개수만큼 return
    override fun getCount(): Int = 2

    //tab의 이름 fragment마다 바꾸게 하기
    override fun getPageTitle(position: Int): CharSequence? {
        val title = when(position)
        {
            0->"잔금"
            1->"거래내역"
            else -> "main"
        }
        return title
    }
}