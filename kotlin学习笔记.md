

## 带接收的lambda表达式

    /**
     * 传递一个方法(join: StringBuffer.() -> Unit),只不过这个方法是扩展方法, 只能使用StringBuffer调用
     */
    fun buildString(join: StringBuffer.() -> Unit): String {
        //StringBuilder().join()报错

        //StringBuffer().join()可以
        return StringBuffer().apply {
        //apply方法里this就是调用者自己(StringBuffer对象)
        join() //调用join方法
        //apply方法返回值就是调用者自己(StringBuffer对象)
        }
        .toString()//StringBuffer对象调用toString
    }


