package com.example.myapplication.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R

class ErrorDialog(private val message: String) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)

        builder.setTitle(R.string.something_went_wrong)
        builder.setMessage(message)
        builder.setPositiveButton(
            getString(R.string.DIALOG_POSITIVE)
        ) { _, _ -> if (activity!!.isTaskRoot) {
            dismiss()
        } else {
            activity!!.finish()
        }
        }
        builder.setIcon(R.drawable.ic_error_black)

        return builder.create()
    }

}