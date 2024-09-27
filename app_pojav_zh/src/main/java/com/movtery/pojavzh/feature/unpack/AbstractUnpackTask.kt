package com.movtery.pojavzh.feature.unpack

abstract class AbstractUnpackTask: Runnable {
    abstract fun isNeedUnpack(): Boolean
    protected var listener: OnTaskRunningListener? = null

    fun setTaskRunningListener(listener: OnTaskRunningListener) {
        this.listener = listener
    }
}