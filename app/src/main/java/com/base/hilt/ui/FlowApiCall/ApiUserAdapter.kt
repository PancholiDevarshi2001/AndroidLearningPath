package com.base.hilt.ui.FlowApiCall

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.hilt.R
import com.base.hilt.bind.GenericRecyclerViewAdapter
import com.base.hilt.databinding.ItemLayoutBinding
import com.bumptech.glide.Glide


    class ApiUserAdapter(context: Context, private val  data : ArrayList<ApiUserModel>,
                         override val layoutResId: Int

    ) :
        GenericRecyclerViewAdapter<ApiUserModel, ItemLayoutBinding>(context, data) {


        override fun onBindData(
            model: ApiUserModel,
            position: Int,
            dataBinding: ItemLayoutBinding,
        ) {
            dataBinding.textViewUserName.text = model.name
            dataBinding.textViewUserEmail.text = model.email
            Glide.with(dataBinding.imageViewAvatar.context)
                .load(model.avatar)
                .into(dataBinding.imageViewAvatar)
        }

        override fun onItemClick(model: ApiUserModel, position: Int) {

        }
        fun addData(list: List<ApiUserModel>) {
            data.addAll(list)
        }
    }
