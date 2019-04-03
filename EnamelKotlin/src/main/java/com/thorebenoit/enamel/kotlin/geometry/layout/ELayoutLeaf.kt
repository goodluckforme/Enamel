package com.thorebenoit.enamel.kotlin.geometry.layout

import com.thorebenoit.enamel.kotlin.core.color.randomColor
import com.thorebenoit.enamel.kotlin.geometry.alignement.ELayoutAxis
import com.thorebenoit.enamel.kotlin.geometry.figures.ERect
import com.thorebenoit.enamel.kotlin.geometry.figures.ERectType
import com.thorebenoit.enamel.kotlin.geometry.figures.ESizeType

class ELayoutLeaf(var color: Int = randomColor(), var child: ELayout? = null) : ELayoutAlongAxis {

    override val layoutAxis: ELayoutAxis get() = (child as? ELayoutAlongAxis)?.layoutAxis ?: ELayoutAxis.horizontal

    companion object {
        val unit = ELayoutLeaf(0)
    }

    private val _frame: ERect = ERect()
    val frame: ERectType = _frame


    override val childLayouts: List<ELayout> get() = child?.childLayouts ?: emptyList() // TODO Test if this fixes snugging issue

    override fun size(toFit: ESizeType): ESizeType = child?.size(toFit) ?: toFit


    override fun arrange(frame: ERectType) {
        _frame.set(frame)
        child?.arrange(frame)
    }

    override fun toString(): String {
        return "ELayoutLeaf(color=$color, child=$child, frame=$frame)"
    }

}
