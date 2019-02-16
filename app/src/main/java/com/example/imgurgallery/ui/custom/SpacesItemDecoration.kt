package com.example.imgurgallery.ui.custom

/*

Decorator which adds spacing around the tiles in a Grid layout RecyclerView. Apply to a RecyclerView with:
    SpacesItemDecoration decoration = new SpacesItemDecoration(16);
    mRecyclerView.addItemDecoration(decoration);

Feel free to add any value you wish for SpacesItemDecoration. That value determines the amount of spacing.
Source: http://blog.grafixartist.com/pinterest-masonry-layout-staggered-grid/
*/

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(private val mSpace: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = mSpace
        outRect.right = mSpace
        outRect.bottom = mSpace
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0)
            outRect.top = mSpace
    }
}