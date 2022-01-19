package com.perficient.chartpoc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.perficient.chartpoc.R
import com.perficient.chartpoc.databinding.ChartTimeFrameSelectorBinding
import com.perficient.chartpoc.model.ChartTimeFrame

class AdapterChartTimeFrameSelector(val timeFrameListener: (ChartTimeFrame) -> Unit) :
    RecyclerView.Adapter<AdapterChartTimeFrameSelector.ViewHolder>() {

    private var data: MutableList<ChartTimeFrame> = mutableListOf()

    fun addData(items: List<ChartTimeFrame>) {
        data.addAll(items)
        notifyItemRangeChanged(0, data.size)
    }

    fun updateData(items: List<ChartTimeFrame>) {
        data.clear()
        data.addAll(items)
        notifyItemRangeChanged(0, data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChartTimeFrameSelectorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(val biding: ChartTimeFrameSelectorBinding) :
        RecyclerView.ViewHolder(biding.root) {

        fun bind(data: ChartTimeFrame) {
            biding.title.text = data.title
            biding.title.setBackgroundColor(
                itemView.resources.getColor(
                    if (data.isSelected)
                        R.color.gray_char else R.color.transparent
                )
            )
            biding.root.setOnClickListener {
                data.isSelected = true
                timeFrameListener.invoke(data)
            }
        }
    }
}

