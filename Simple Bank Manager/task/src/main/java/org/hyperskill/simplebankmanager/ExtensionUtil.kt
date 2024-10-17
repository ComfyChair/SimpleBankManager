package org.hyperskill.simplebankmanager

object ExtensionUtil {
    fun Double.format(digits: Int) = "%.${digits}f".format(this)
}