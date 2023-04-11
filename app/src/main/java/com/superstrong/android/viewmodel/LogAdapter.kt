package com.superstrong.android.viewmodel


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.superstrong.android.R
import com.superstrong.android.data.History

class LogAdapter(val context: Context, val items: MutableList<History>):BaseAdapter() {
    override fun getView(position: Int, convertView:  View?, parent: ViewGroup?): View? {

        // converView가 null이 아닐 경우 View를 재활용
        // 이 부분이 없다면, View를 리스트의 갯수만큼 호출해야 함
        var convertView = convertView
        if (convertView == null) {
            // list_view_item 을 가져온다
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.item_log, parent, false)
        }
        val typeText = arrayOf("보내기,받기")
        val coinText = arrayOf("BTC,ETH,Doge")
        var type = convertView?.findViewById<TextView>(R.id.send_or_rec)
        type!!.text = typeText[items[position].type]
        var coin = convertView?.findViewById<TextView>(R.id.coin_name)
        coin!!.text = coinText[items[position].coin]
        var amount = convertView?.findViewById<TextView>(R.id.coin_quantity)
        amount!!.text = items[position].amount.toString()
        var date = convertView?.findViewById<TextView>(R.id.log_time)
        date!!.text = items[position].date
        var address = convertView?.findViewById<TextView>(R.id.log_address)
        address!!.text = items[position].address
        return convertView

    }
    override fun getCount(): Int {
        return items.size //returns total of items in the list
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}