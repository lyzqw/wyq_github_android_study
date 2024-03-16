package com.qwlyz.androidstudy

interface IAA {
    fun eat()
}

class BB : IAA {
    override fun eat() {
        println("eat...")
    }
}

class AA(bb: BB) : IAA by bb {


    fun show() {
        println("show")
        eat()
    }

//    fun eat(){
//        println("eat")
//    }
}