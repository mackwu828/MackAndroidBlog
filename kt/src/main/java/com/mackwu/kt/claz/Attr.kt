package com.mackwu.kt.claz

import android.os.Handler
import com.mackwu.kt.base.Null
import com.mackwu.kt.bean.Person

/**
 * ===================================================
 * Created by MackWu on 2019/10/18 18:44
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 属性
 * 用关键字`val`声明不可变属性，`var`声明可变属性
 * "
 * val 属性名称：属性类型 = 属性值
 *      getter
 *      setter
 * "
 * 如 val person: Person = Person()
 *
 * <h2>属性类型可以省略</h2>
 * 属性类型可以省略，编译器会自动推断。如 val person = Person()
 *
 * <h2>属性值必须要初始化</h2>
 * val person: Person // 编译器报错Property must be initialized or be abstract
 *
 * 如果不想在声明的时候初始化，怎么办？
 * 1. 声明为可空对象。如 var person: Person? = null [Null]
 * 2. 延迟初始化对象。如 lateinit var person: Person
 *
 * <h2>延迟初始化</h2>
 * 延迟初始化方式一：用关键字 lateinit 表示延迟初始化对象，要保证在使用对象时对象已经初始化，否则会抛出异常
 *
 * 如何判断 lateinit 的对象已经初始化？
 * 通过成员引用判断 lateinit 是否初始化，从Kotlin1.2开始支持。如::person.isInitialized // ::操作符获取成员引用
 *
 * 延迟初始化方式二：用关键字 by lazy 表示对象在第一次被调用的时候才会执行，后续调用只是返回记录的结果。如 val person by lazy { Person() }
 *
 *
 * <h2>getter和setter</h2>
 * getter和setter是可以省略的，如果没有自定义getter和setter，会自动生成。
 *     var person = Person()
 *          get() = field // Redundant getter
 *          set(value){ // Redundant setter
 *              field = value
 *          }
 * 如果自定义了getter，那么每次访问属性都会调用它。
 * 如果自定义了setter，那么每次给属性赋值都会调用它。set的默认参数是value
 *
 * <h2>幕后属性</h2>
 *
 * <h2>委托属性</h2>
 *
 */
class Attr {

//    val person: Person = Person()

    // 属性类型可以省略，编译器会自动推断
//    val person = Person()

    // 属性值必须要初始化
    // 1. 声明为可空对象
//    var person: Person? = null
    // 2. 延迟初始化对象
//    lateinit var person: Person
//    val person by lazy { Person() }

    // getter和setter
//    var person = Person()
//        get() = field
//        set(value){
//            field = value
//        }
//    var person
//        get() = Person()
//        set(value) {
//            xxx = value
//        }
//    var xxx: Person? = null


    fun test() {
        // 通过成员引用判断lateinit是否初始化
//        ::person.isInitialized
    }
}

fun main() {

}