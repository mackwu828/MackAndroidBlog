
```
class Person2(
        val name: String,
        val age: Int
) {

    constructor(builder: Builder) : this(builder.name, builder.age)

    class Builder {
        var name: String = ""
        var age: Int = 0

        fun build(): Person2 {
            return Person2(this)
        }
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}

fun main() {
    val person = Person2.build {
        name = "aaa"
        age = 11
    }
}
```