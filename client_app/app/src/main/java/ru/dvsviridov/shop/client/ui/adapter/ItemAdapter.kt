package ru.dvsviridov.shop.client.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.dvsviridov.shop.client.R
import ru.dvsviridov.shop.client.databinding.ShopItemLayoutBinding
import ru.dvsviridov.shop.client.model.dto.Item

class ItemAdapter
    (val recyclerEventListener: RecyclerItemEventListener) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(),
    RecyclerItemEventListener {

    inner class ItemViewHolder(val binding: ShopItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Item>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ShopItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        with(holder) {
            with(differ.currentList[position]) {
                binding.itemTitleText.text = this.title
                binding.itemDescriptionText.text = this.description
                binding.itemPriceText.text = this.price.toString()

                Glide.with(binding.root.context)
                    .load(this.imageUrl)
                    .placeholder(R.drawable.ic_item_card_placeholder)
                    .error(R.drawable.ic_cardview_item_error_placeholder)
                    .into(binding.itemImageContainer)

                binding.itemPriceText.setOnClickListener {
                    recyclerEventListener.onPriceClick(this)
                }

                if (this.isOnSale == true) {
                    binding.itemPriceText.setTextColor(
                        ContextCompat.getColor(
                            binding.itemPriceText.context,
                            R.color.sailed
                        )
                    )
                }
            }
        }
    }

    override fun onPriceClick(item: Item) {
        Log.d(TAG, "onPriceClick: ${item.price}")
    }

    companion object {
        private val TAG = ItemAdapter::class.java.simpleName
    }
}

interface RecyclerItemEventListener {
    fun onPriceClick(item: Item)
}