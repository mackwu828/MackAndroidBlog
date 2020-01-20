package com.mackwu.http.okhttp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.http.R
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient

/**
 * ===================================================
 * Created by MackWu on 2019/10/9 10:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * ConnectionPool连接池
 * @see okhttp3.ConnectionPool
 *
 * 客户端与服务端进行通信：
 * 1.首先要建立连接，okhttp使用socket建立连接。Connection中持有Socket对象
 * @see okhttp3.Connection
 * @see okhttp3.internal.connection.RealConnection
 *
 * 2.然后进行数据通信，一对请求和响应分别对应着输入流和输出流，okhttp使用的流叫做HttpCodec，HttpCodec负责请求的写入与响应的读出
 * @see okhttp3.internal.http.HttpCodec
 *
 * 3.
 *
 * 建立连接是昂贵的？因此在请求任务完成后不会立即关闭连接，使得连接可以复用，okhttp使用ConnectionPool来完成连接的管理和复用
 *
 * <h2>连接池的使用</h2>
 * @see OkHttpClient.Builder =》默认创建了连接池connectionPool = new ConnectionPool();
 * ConnectionPool(int maxIdleConnections, long keepAliveDuration, TimeUnit timeUnit)
 *
 *
 */
class ConnectionPoolActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connectionPool = ConnectionPool()
        val okHttpClient = OkHttpClient.Builder()
                .connectionPool(connectionPool)
                .build()
    }
}