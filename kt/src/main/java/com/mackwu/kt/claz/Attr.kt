package com.mackwu.kt.claz

import com.mackwu.kt.bean.Person


/**
 * ===================================================
 * Created by MackWu on 2019/10/18 18:44
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * 用关键字var声明可变属性，val声明只读属性
 */
class Attr {
    val person: Person = Person()  // 只读的，不能被赋值
    var person2: Person = Person()

    fun test() {
//        person = Person() // 编译器报错 Val cannot be reassigned
        person2 = Person() // 可变的，可以再次被赋值
    }
}

/**
 * 属性类型可以省略，编译器会自动推断
 */
class Attr2 {
    val person = Person() // 自动推断为Person类型

    val person2 // 自动推断为Person类型
        get() = Person()
}



/**
 * 属性一般需要在声明的时候初始化，否则编译器会报错。
 */
class Attr10 {
    // val person: Person // 编译器报错Property must be initialized or be abstract
}

/**
 * 如果不想在属性声明的时候初始化，可以延迟初始化属性
 * 可空类型迟初始化，将属性类型声明为可空类型，并初始化为null
 */
class Attr11 {
    var person2: Person? = null // 将属性类型声明为可空类型，并初始化为null

    fun test() {
        person2 = Person()
        person2?.name // 使用可空对象时要用安全调用操作符?.
    }
}

/**
 * 用关键字 lateinit 延迟初始化，要保证在使用对象时对象已经初始化，否则会抛出异常
 *
 * 如何判断 lateinit 的对象已经初始化？
 * 通过成员引用判断 lateinit 是否初始化，从Kotlin1.2开始支持
 */
class Attr12 {
    lateinit var person3: Person // 用关键字 lateinit 延迟初始化

    fun test() {
        person3.name // lateinit的对象还未初始化，抛出异常 kotlin.UninitializedPropertyAccessException: lateinit property person has not been initialized
        person3 = Person()
        if (::person3.isInitialized) { // 通过成员引用判断 lateinit 是否初始化，返回true表示已经初始化

        }
    }
}

/**
 * 用关键字 by lazy 也表示延迟初始化，不过仍然需要在声明的时候初始化，只是初始化的代码是在对象第一次被调用的时候才会执行，
 * 后续调用不会再执行，会返回记录的结果
 */
class Attr13 {
    val person4 by lazy { Person() } // Person()不会被立即执行

    fun test() {
        person4.name // 这时Person()被执行
    }
}



/**
 * getter和setter可选，声明一个属性默认提供getter和setter。
 * 访问一个属性实际是访问了属性的读访问器getter。
 * 设置一个属性实际是访问了属性的写访问器setter。
 */
class Attr20 {
    var person = Person() // getter和setter可选，声明一个属性默认提供getter和setter

    var name = "mack"
        get() = "I am getter"

    var name2 = "mack"
        set(value) {
            println("I am setter")
        }

    fun test() {
        println(name)  // 输出I am getter。访问一个属性实际是访问了属性的读访问器getter

        name2 = "xxx" // 输出I am setter。访问一个属性实际是访问了属性的读访问器getter

        println(name2) // 输出mack，name2的值没有改变，因为name2的写访问器中根本没有给属性赋值
    }
}

/**
 * 一个属性的默认getter和setter是什么样的呢？
 * 通过幕后字段给属性赋值。如果属性至少一个访问器使用默认实现，那么Kotlin会自动提供幕后字段，用field表示，相当于属性自身
 */
class Attr21(val type: Int) {

//    var name: String // 按照Java的写法，会陷入死循环
//        get() = this.name
//        set(value) {
//            this.name = value
//        }


    var name2: String = "" // 一个属性的默认getter和setter。等同于var name2: String = "
        get() = field
        set(value) {
            field = value
        }
}
