//package com.mackwu.http.okhttp
//
//
//import com.zeasn.accu.services.bean.BaseResponse
//import io.reactivex.Observer
//import io.reactivex.disposables.Disposable
//import retrofit2.HttpException
//import java.net.SocketTimeoutException
//import java.net.UnknownHostException
//import java.net.UnknownServiceException
//
///**
// * ===================================================
// * Created by MackWu on 2018/5/24 11:46
// * <a href="mailto:wumengjiao828@163.com">Contact me</a>
// * <a href="https://github.com/mackwu828">Follow me</a>
// * ===================================================
// */
//abstract class BaseObserver<T> : Observer<BaseResponse<T>> {
//
//    private var disposable: Disposable? = null
//
//    abstract fun onSuccess(t: T?, successMsg: String)
//
//    abstract fun onFail(errorMsg: String)
//
//    override fun onSubscribe(d: Disposable) {
//        disposable = d
//    }
//
//    override fun onNext(response: BaseResponse<T>) {
//        response.run {
//            if (error == 0) {
//                onSuccess(datas, "")
//                return
//            }
//            onFail("")
//        }
//    }
//
//    override fun onError(t: Throwable) {
//        t.printStackTrace()
//        val errorMsg = when (t) {
//            is HttpException -> "网络不给力，请检查网络：" + t.message
//            is SocketTimeoutException -> "服务器响应超时，请检查网络"
//            is RuntimeException -> "运行时错误"
//            is UnknownHostException -> "无法解析主机，请检查网络"
//            is UnknownServiceException -> "未知的服务器错误，请检查网络"
//            else -> "网络不给力，请检查网络"
//        }
//        onFail(errorMsg)
//        disposable?.dispose()
//    }
//
//    override fun onComplete() {
//        disposable?.dispose()
//    }
//
//}
