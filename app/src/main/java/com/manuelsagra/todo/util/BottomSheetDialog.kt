package com.manuelsagra.todo.util

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.manuelsagra.todo.R

abstract class BottomSheetDialog : BottomSheetDialogFragment() {

    override fun getTheme(): Int = R.style.Dialog

}