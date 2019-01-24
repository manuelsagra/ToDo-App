package com.manuelsagra.todo.util

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration(@DimenRes val offsetId: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val offset = view.context.resources.getDimensionPixelSize(offsetId)
        val position = parent.getChildAdapterPosition(view)
        val topOffset = if (position == 0) offset else 0

        outRect.set(offset, topOffset, offset, offset)
    }
}