package com.patrykkosieradzki.theanimalapp.utils

import android.graphics.Rect
import android.view.View
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.patrykkosieradzki.theanimalapp.utils.extensions.dpToPx

class PagerMarginItemDecoration(
    private val horizontalMargin: Int = 8.dpToPx,
    private val adjacentVisibleSize: Int = 16.dpToPx
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val childCount = state.itemCount

        val firstItem = position == 0
        val lastItem = position == childCount - 1

        val metrics = parent.context.resources.displayMetrics
        var itemWidth = metrics.widthPixels - 2 * adjacentVisibleSize - 4 * horizontalMargin

        if (firstItem || lastItem) {
            itemWidth = metrics.widthPixels - adjacentVisibleSize - 4 * horizontalMargin
        }

        if (firstItem && lastItem) {
            itemWidth = metrics.widthPixels - 4 * horizontalMargin
        }

        view.updateLayoutParams {
            width = itemWidth
            height = RecyclerView.LayoutParams.MATCH_PARENT
        }

        with(outRect) {
            left = if (firstItem) 2 * horizontalMargin else horizontalMargin
            right = if (lastItem) 2 * horizontalMargin else horizontalMargin
        }
    }
}
