package com.example.myapplication.mvp.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.myapplication.db.daos.FavouritesDao
import com.example.myapplication.db.database.AppDatabase
import com.example.myapplication.db.entities.FavouritesEntry
import com.example.myapplication.dialogs.ErrorDialog
import com.example.myapplication.dialogs.ProgressDialog
import kotlinx.android.synthetic.main.activity_artist.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


abstract class AppActivity<V : AppView, P : AppPresenter<V>> : AppCompatActivity(), AppView {

    var context: Context? = null

    var db: AppDatabase? = null
    var favouritesDao: FavouritesDao? = null

    private var p: P? = null

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        p = createPresenter()
        p?.attachView(this as V)

        GlobalScope.launch {
            db = AppDatabase.getAppDataBase(this@AppActivity)
            favouritesDao = db?.favouritesDao()
        }
    }

    override fun onProgressEnabled(enabled: Boolean) {
        if (enabled) {
            progressDialog = ProgressDialog.show(this)
        } else {
            progressDialog?.hide()
        }
    }

    override fun onBottomProgressEnabled(enabled: Boolean) {
        artistBottomPB.isVisible = enabled
    }

    override fun onDestroy() {
        p?.detachView()
        super.onDestroy()
    }

    override fun onError(message: String) {
        val dialogFragment = ErrorDialog(message)
        dialogFragment.isCancelable = false
        val ft = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        dialogFragment.show(ft, "dialog")
    }

    protected abstract fun createPresenter(): P

    protected fun getPresenter(): P {
        checkNotNull(p) { "Presenter is null" }
        return p!!
    }

}