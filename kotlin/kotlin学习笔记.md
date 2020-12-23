

## 带接收的lambda表达式

     //传递一个方法(join: StringBuffer.() -> Unit),只不过这个方法是扩展方法对象, 只能使用StringBuffer调用
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

      fun BornPerson.handleBornPerson(block: BornPerson.() -> Unit) {
            //block()//如果扩展函数本身是这个对象,那么可以不用传递参数
            //BornPerson().apply(block) 否则需要显示的使用对象调用
      }

      fun  String.handleBornPerson(block: BornPerson.() -> Unit) {
            //BornPerson().apply(block) 需要显示的使用对象调用
            //BornPerson().block()
            block(BornPerson())
      }

    //参数要一个函数对象
    fun handleMethod(block: () -> String) {
        println("执行方法前")
        val result = block()
        println(result)
        println("执行方法后")
    }
    //给一个函数对象
    handleMethod(::method)

    //给一个lambda对象, 只不过要返回值, 所以给了最后一行, 如果是有参数的, it就是这个参数
    handleMethod{
        println("我是lambda")
        "我是lambda的返回值"
    }

    //匿名对象方法
    handleMethod(fun(): String = "111")
    -----------------------------------------------------------------------------
    buildAction(::method)
    
    //想要传递带有接收参数的高阶函数对象, 第一个参数需要加一个接收者的对象
    private fun method(r: ReceiverBean) {

    }
    private fun buildAction(action: ReceiverBean.() -> Unit) {
        val receiverBean = ReceiverBean()
        receiverBean.action()
        println(receiverBean.toString())
    }
    ---------------------------------------------------------------------------------------------------
    
## for循环
    for (i in 0 until childCount)  // 0 ~ childCount-1

    for (i in 0..childCount)  // 0 ~ childCount


## 泛型
    PECS ? extend Fruit 生产者, 在kotlin中是out   
    PECS ? super Apple 消费者, 在kotlin中是in   
