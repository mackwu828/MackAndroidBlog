package com.mackwu.view.leanback

import android.os.Bundle
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ItemBridgeAdapter
import androidx.leanback.widget.Presenter
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mackwu.view.R
import kotlinx.android.synthetic.main.activity_hor.*

/**
 * ================================================
 * Created by MackWu on 2019/9/6 11:51
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * HorizontalGridView
 * HorizontalGridView是RecyclerView的子类。默认实现记住焦点，获得焦点的item保持居中
 *
 */
class HorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hor)

        //
//        horizontal_grid_view.setNumRows(3)

        // 数据
        val list = listOf("Home", "Promote", "User")

        val arrayObjectAdapter = ArrayObjectAdapter(HorPresenter())
        val itemBridgeAdapter = ItemBridgeAdapter(arrayObjectAdapter)
        horizontal_grid_view.adapter = itemBridgeAdapter
        arrayObjectAdapter.addAll(0, list)
    }

    class HorPresenter: Presenter(){

        override fun onCreateViewHolder(viewGroup: ViewGroup): ViewHolder {
            val inflate = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_hor, viewGroup, false)
            return ViewHolder(inflate)
        }

        override fun onBindViewHolder(viewHolder:Presenter.ViewHolder, any: Any) {
            val vh= viewHolder as HorPresenter.ViewHolder
            vh.tvTest.text = any as String
        }

        override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
        }

        inner class ViewHolder( view: View): Presenter.ViewHolder(view){
            val tvTest = view.findViewById(R.id.tv_title) as TextView
        }
    }

}