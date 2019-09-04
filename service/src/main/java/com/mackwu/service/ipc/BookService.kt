package com.mackwu.service.ipc

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.mackwu.service.BookManager

/**
 * ================================================
 * Created by MackWu on 2019/8/30 19:10
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * 1. 新建aidl文件，如Book.aidl
 *
 *
 */
class BookService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return BookStub()
    }

    class BookStub : BookManager.Stub() {

        private val books = arrayListOf(
                Book("Java"),
                Book("Android"),
                Book("Kotlin")
        )

        override fun getBooks(): MutableList<Book> {
            synchronized(this) {
                return books
            }
        }

        override fun addBook(book: Book) {
            synchronized(this) {
                books.add(book)
            }
        }
    }

}