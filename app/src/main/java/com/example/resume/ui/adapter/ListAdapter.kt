package com.example.resume.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.resume.R
import com.example.resume.bean.Applicant

/**
 * @author: playboi_YzY
 * @date: 2023/6/3 10:27
 * @description:列表adapter
 * @version:
 */
class ListAdapter : BaseQuickAdapter<Applicant, BaseViewHolder>(R.layout.item_list),LoadMoreModule{
    override fun convert(holder: BaseViewHolder, item: Applicant) {
        holder.setText(R.id.text_name,item.name)
        holder.setText(R.id.text_age,item.age.toString())
        holder.setText(R.id.text_academic,item.college)
        holder.setText(R.id.text_qualifications,item.aq)
        holder.setText(R.id.text_experience,item.exp.toString())
    }

}