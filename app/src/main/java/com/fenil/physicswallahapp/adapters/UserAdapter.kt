package com.fenil.physicswallahapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fenil.physicswallahapp.api.models.entities.User
import com.fenil.physicswallahapp.databinding.ItemUserBinding
import java.lang.StringBuilder

class UserAdapter(val context: Context):RecyclerView.Adapter<UserAdapter.userViewHolder>() {
    private var binding: ItemUserBinding? = null
    private val differCallback: DiffUtil.ItemCallback<User> =
        object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id === newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.equals(newItem)
            }
        }

    var differ: AsyncListDiffer<User> =AsyncListDiffer(this@UserAdapter,differCallback)

    class userViewHolder(val binding: ItemUserBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        binding= ItemUserBinding.inflate(LayoutInflater.from(context),parent,false)

        return userViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        val  user: User = differ.currentList[position] as User
        with(binding!!){
            tvUserName.text=user.name
            val qualification=StringBuilder("");
            for (sub in user.subjects){
                if(qualification.toString() == ""){
                    qualification.append(sub)
                }else {
                    qualification.append(" · ")
                    qualification.append(sub)
                }
            }

            for (quali in user.qualification){
                if(qualification.toString() == ""){
                    qualification.append(quali)
                }else {
                    qualification.append(" · ")
                    qualification.append(quali)
                }
            }
            tvQualification.text= qualification.toString()
            Glide.with(context).load(user.profileImage).into(ivUserPicture)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}