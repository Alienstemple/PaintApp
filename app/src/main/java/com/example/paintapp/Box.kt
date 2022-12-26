package com.example.paintapp

import android.graphics.PointF

class Box(var mOrigin: PointF, var mCurrent: PointF = PointF(0f, 0f)) {

//    constructor(mOrigin: PointF)
//    constructor(mOrigin: PointF, mCurrent: PointF)

    // ex1 - can't get private value
//    private val mOrigin: PointF = PointF(0f, 0f)
//        get() = field    // redundant getter

    // ex2 - no need to initialize
//    private val mCurrent: PointF
//        get() = mCurrent

    // ex3 - redundant getter
//    val mOrigin: PointF = PointF(0f, 0f)
//        get() = field   // redundant getter
//    val mCurrent: PointF
//        get() = mCurrent  // no redundant getter

    // ex4 - public fields, can't initialize
//    val mOrigin: PointF = PointF(1f, 1f)
//        get() = field
//    val mCurrent: PointF = PointF(3f, 3f)
//        get() = field
}