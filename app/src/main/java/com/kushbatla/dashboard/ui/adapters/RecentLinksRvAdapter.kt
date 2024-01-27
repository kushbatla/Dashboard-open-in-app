package com.kushbatla.dashboard.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.kushbatla.dashboard.data.RecentLink
import com.kushbatla.dashboard.databinding.LinkItemBinding
import com.kushbatla.dashboard.loadImage

class RecentLinksRvAdapter : RecyclerView.Adapter<RecentLinksRvAdapter.RecentLinksRvAdapterViewHolder>() {


    inner class RecentLinksRvAdapterViewHolder (val binding: LinkItemBinding) : RecyclerView.ViewHolder(binding.root)

    var recentLinks : List<RecentLink> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentLinksRvAdapterViewHolder {
        return RecentLinksRvAdapterViewHolder(
            LinkItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return recentLinks.size
    }

    override fun onBindViewHolder(holder: RecentLinksRvAdapterViewHolder, position: Int) {
        holder.binding.apply {
            itemSampleLinkName.text = recentLinks[position].smart_link
            itemDate.text = recentLinks[position].created_at
            itemClickCount.text = recentLinks[position].total_clicks.toString()
            itemLink.text = recentLinks[position].web_link.toString()
            itemImg.loadImage(recentLinks[position].original_image)
            copyBtn.isVisible = false
        }
    }

}