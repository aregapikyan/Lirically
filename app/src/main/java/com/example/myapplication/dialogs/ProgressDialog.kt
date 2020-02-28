package com.example.myapplication.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.R

class ProgressDialog : Fragment() {

    companion object{

        private const val PROGRESS_TAG = "PROGRESS_TAG"

        fun show(activity: FragmentActivity):ProgressDialog{
            val pd = ProgressDialog()
            activity.supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content,  pd, PROGRESS_TAG)
                .commit()

            return pd
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_progress, container, false)
    }

    fun hide(){
        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commitAllowingStateLoss()
    }
}