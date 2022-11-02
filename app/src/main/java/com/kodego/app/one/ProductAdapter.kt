package com.kodego.app.one

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kodego.app.one.databinding.RowItemBinding

class ProductAdapter(val products:MutableList<Products>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var onItemClick : ((Products) -> Unit)? = null
    var onUpdateButtonClick : ((Products,Int) -> Unit)? = null
    var onDeleteButtonClick : ((Products,Int) -> Unit)? = null

    inner class ProductViewHolder(val binding:RowItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowItemBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
            var bitmap: Bitmap? = BitmapFactory.decodeByteArray(products[position].imageName,0,products[position].imageName.size)
            imgProduct.setImageBitmap(bitmap)
            tvItemName.text = products[position].itemName
            tvDescription.text = products[position].itemDescription
            tvQuantity.text = products[position].quantity.toString()
            imgBtnUpdate.setOnClickListener(){
                onUpdateButtonClick?.invoke(products[position],position)
            }
            imgBtnDelete.setOnClickListener(){
                onDeleteButtonClick?.invoke(products[position],position)
            }
        }
        holder.itemView.setOnClickListener(){
            onItemClick?.invoke(products[position])
        }
    }

    override fun getItemCount(): Int {
       return products.size
    }
}