package vn.tiki.android.androidhometest

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.item_keyword.view.*
import java.util.*



/**
 * Created by HuongPN on 01/07/2019.
 */
class MainAdapter(var context: Context, var list: List<String>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Random background color
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

        val draw = GradientDrawable();
        draw.shape = GradientDrawable.RECTANGLE
        draw.cornerRadius = 15f
        draw.setColor(color)

        val keyword = handleKeyword(list[position])

        holder.tvKeyword.background = draw
        holder.tvKeyword.text = keyword
        holder.itemView.setOnClickListener {
            Toast.makeText(context, list[position], Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.item_keyword, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvKeyword = itemView.tvKeyword
    }

    fun handleKeyword(input: String?): String {
        var start = ""
        var end = ""
        var result = "";
        val string = input!!.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        for (i in string.indices) {
            println(i)
            if (string.size / 2 > i) {
                start = start + " " + string[i]
            } else {
                end = end + " " + string[i]
            }
        }

        if (string.size > 1){
            result = start+ "\n" + end;
        }else{
            result = input
        }

        return result
    }
}