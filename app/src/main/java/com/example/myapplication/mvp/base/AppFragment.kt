package com.example.myapplication.mvp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.myapplication.db.entities.FavouritesEntry
import com.example.myapplication.dialogs.ErrorDialog
import com.example.myapplication.dialogs.ProgressDialog
import kotlinx.android.synthetic.main.activity_artist.*

abstract class AppFragment<V : AppView, P : AppPresenter<V>> : Fragment(), AppView {

    fun getFragmentTag(): String = this::class.java.name


    private var p: P? = null

    private var progressDialog: ProgressDialog? = null

    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        p = createPresenter()
        p?.attachView(this as V)

        return layoutInflater.inflate(
            layoutId,
            container,
            false
        )
    }

    override fun onDestroy() {
        p?.detachView()
        super.onDestroy()
    }

    protected abstract fun createPresenter(): P

    protected fun getPresenter(): P {
        checkNotNull(p) { "Presenter is null" }
        return p!!
    }

    override fun onProgressEnabled(enabled: Boolean) {
        if (enabled) {
            progressDialog = ProgressDialog.show(activity!!)
        } else {
            progressDialog?.hide()
        }
    }

    override fun onBottomProgressEnabled(enabled: Boolean) {
        artistBottomPB.isVisible = enabled
    }

    override fun onError(message: String) {
        val dialogFragment = ErrorDialog(message)
        dialogFragment.isCancelable = false
        val ft = activity!!.supportFragmentManager.beginTransaction()
        val prev = activity!!.supportFragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        dialogFragment.show(ft, "dialog")
    }

}