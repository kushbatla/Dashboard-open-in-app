package com.kushbatla.dashboard.ui.adapters

import android.R.attr.text
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kushbatla.dashboard.data.TopLink
import com.kushbatla.dashboard.databinding.LinkItemBinding
import com.kushbatla.dashboard.loadImage


class TopLinksRvAdapter(var mContext: Context) : RecyclerView.Adapter<TopLinksRvAdapter.TopLinksRvViewHolder>(){
    inner class TopLinksRvViewHolder(val binding : LinkItemBinding) : RecyclerView.ViewHolder(binding.root)

    var topLinks: List<TopLink> = emptyList()

   /*var topLinks = listOf<String>("Naman", "Reena", "Aakash")*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopLinksRvViewHolder {
        return TopLinksRvViewHolder(LinkItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }


    override fun onBindViewHolder(holder: TopLinksRvViewHolder, position: Int) {
        holder.binding.apply {

            itemSampleLinkName.text = topLinks[position].smart_link
            itemDate.text = topLinks[position].created_at
            itemClickCount.text = topLinks[position].total_clicks.toString()
            itemLink.text = topLinks[position].web_link.toString()
            itemImg.loadImage(topLinks[position].original_image)

           /* itemSampleLinkName.text = topLinks[position]*/

        }

        holder.binding.copyBtn.setOnClickListener {
            val clipboard = ContextCompat.getSystemService(mContext,ClipboardManager::class.java)
            clipboard?.setPrimaryClip(ClipData.newPlainText("text",topLinks[position].web_link.toString() ))

            Toast.makeText(mContext, "Copied!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return topLinks.size
    }
}