package com.manuelsagra.todo.util.bottomsheet

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.manuelsagra.todo.R
import kotlinx.android.synthetic.main.bottom_sheet_menu.view.*

/**
 * Created by costular on 29/08/17.
 */
class BottomSheetMenu(private val context: Context,
                      private val items: List<BottomMenuItem>) {

    private val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context, R.style.Dialog)

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_menu, null)
        bottomSheetDialog.setContentView(view)

        with(view) {
            recyclerBottomSheet.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            recyclerBottomSheet.adapter = BottomSheetMenuAdapter(items) {
                bottomSheetDialog.dismiss()
            }
        }
    }

    fun show() {
        bottomSheetDialog.show()
    }
}