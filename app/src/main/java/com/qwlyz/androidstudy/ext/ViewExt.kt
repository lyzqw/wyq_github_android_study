package com.qwlyz.androidstudy.ext

import android.graphics.Color
import com.qwlyz.androidstudy.AA
import java.util.*


fun getRandomColor (): Int {
    val rnd = Random()
    return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
}


fun AA.show () {
    println("ext.show")
}

fun AA.eat () {
    println("ext.eat")
}