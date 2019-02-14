package com.thorebenoit.enamel.kotlin.genetics

import com.thorebenoit.enamel.kotlin.core.math.f
import com.thorebenoit.enamel.kotlin.core.math.random
import com.thorebenoit.enamel.kotlin.core.print
import kotlin.math.roundToInt

fun randomFloatArray(n: Int) = (0 until n).map { random() }.toFloatArray()


// randomWithWeight
private fun main() {
    val map = mapOf(
        "50" to .50f,
        "25" to .25f,
        "15" to .15f,
        "10" to .10f
    )


    val testList = (0 until 1_000_000).map { map.randomWithWeight() }
    map.keys.forEach { k ->
        val e = testList.filter { it.key == k }.size
        val ratio = (
                100 *
                        e / testList.size.f
                ).roundToInt()
        println("$k=$ratio%")
    }
}

//fun <T : Any, N : Number> Map<T, N>.randomWithWeight(): Map.Entry<T, N> {
//
//    val pool = mutableListOf<Map.Entry<T, N>>()
//    forEach { entry ->
//        val n = (entry.value.toFloat() * 100f).roundToInt()
//        for (i in 0 until n) {
//            pool.add(entry)
//        }
//    }
//    return pool.random()
//
//    TODO()
////    return toList().random()
//}

fun <T : Any, N : Number> Map<T, N>.randomWithWeight(): Map.Entry<T, N> {
    val totalWeight = values.asSequence().map { it.f }.sum()
    var value = random(0, totalWeight)
    forEach {
        value -= it.value.f
        if (value < 0) {
            return it
        }
    }

    TODO()
//    return toList().random()
}
