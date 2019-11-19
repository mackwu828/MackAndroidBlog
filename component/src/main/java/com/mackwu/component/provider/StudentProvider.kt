package com.mackwu.component.provider

import android.app.SearchManager.QUERY
import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.util.Log
import com.mackwu.component.db.StudentDao

/**
 * ===================================================
 * Created by MackWu on 2019/11/1 17:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 增删改查
 */
class StudentProvider : ContentProvider() {

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    /**
     * 在ContentProvider被创建时调用
     */
    override fun onCreate(): Boolean {
        Log.d(TAG, "onCreate...")
        uriMatcher.addURI(AUTHORITY, PATH, MATCH_CODE)
        return true
    }

    /**
     * 用于查询指定uri的数据返回一个Cursor
     */
    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        Log.d(TAG, "query...")
        val match = uriMatcher.match(uri)
        Log.d(TAG, "match: $match")
        return if (match == MATCH_CODE) StudentDao.query() else null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.d(TAG, "insert...")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        Log.d(TAG, "delete...")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        Log.d(TAG, "update...")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(uri: Uri): String? {
        Log.d(TAG, "getType...")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private val TAG = StudentProvider::class.java.simpleName
        private const val AUTHORITY = "com.mackwu.component.provider.StudentProvider" // 在AndroidManifest.xml中配置的authorities
        private const val PATH = "query"
        private const val MATCH_CODE = 0x01
        val STUDENT_URI = Uri.parse("content://$AUTHORITY/$PATH")

        val ERROR_URI = Uri.parse("content://$AUTHORITY/xxx")
    }
}