package com.ekalips.base.rv

import android.support.v7.util.DiffUtil

class DiffCallback<out DataType>(private val oldData: List<DataType>,
                                 private val newData: List<DataType>,
                                 private val comparator: DiffComparator<DataType>?) : DiffUtil.Callback() {


    override fun getOldListSize(): Int = oldData.size
    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            comparator?.areItemsTheSame(oldData[oldItemPosition], newData[newItemPosition])
                    ?: false

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            comparator?.areContentsTheSame(oldData[oldItemPosition], newData[newItemPosition])
                    ?: false

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return comparator?.getChangePayload(oldData[oldItemPosition], newData[newItemPosition])
    }

    interface DiffComparator<in DataType> {
        fun areItemsTheSame(oldItem: DataType, newItem: DataType): Boolean
        fun areContentsTheSame(oldItem: DataType, newItem: DataType): Boolean
        fun getChangePayload(oldItem: DataType, newItem: DataType): Any? {
            return null
        }
    }
}