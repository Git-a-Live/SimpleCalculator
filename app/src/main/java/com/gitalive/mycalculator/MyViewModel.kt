package com.gitalive.mycalculator

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.util.*

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val app = application
    private val result = MutableLiveData<String>()
    private val expression = MutableLiveData<String>()
    private val expressionConvert = ExpressionConvert()
    private val stringBuilder = StringBuilder(128)

    init {
        result.value = ""
        expression.value = ""
    }

    fun getExpr(): MutableLiveData<String> {
        return expression
    }

    fun getResult(): MutableLiveData<String> {
        return result
    }

    fun input(ip: Any) {
        stringBuilder.append(ip)
        result.value = stringBuilder.toString()
    }

    fun clear() {
        stringBuilder.clear()
        result.value = stringBuilder.toString()
        expression.value = stringBuilder.toString()
    }

    fun operate() {
        if (stringBuilder.isEmpty() || result.value == null) {
            notice(2)
        } else {
            val postFix = expressionConvert.expressionConvert(result.value!!)
            expression.value = calculate(postFix)
        }
    }

    fun notice(case: Int) {
        when (case) {
            0 -> {
                Toast.makeText(app.applicationContext, "页面正在施工", Toast.LENGTH_SHORT).show()
            }
            1 -> {
                Toast.makeText(app.applicationContext, "请检查计算式", Toast.LENGTH_SHORT).show()
            }
            2 -> {
                Toast.makeText(app.applicationContext, "请输入计算式", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculate(postFix: ArrayList<String>): String {
        val tempStack = Stack<Any>()
        for (s in postFix) {
            if ("+-×÷".contains(s)) {
                val num1 = tempStack.pop().toString()
                val num2 = tempStack.pop().toString()
                val result = compute(num1, num2, s)
                tempStack.push(result.toString())
            } else {
                tempStack.push(s)
            }
        }
        return tempStack.pop().toString()
    }

    private fun compute(num1: String, num2: String, opr: String): Double {
        return when (opr) {
            "+" -> num1.toDouble() + num2.toDouble()
            "×" -> num1.toDouble() * num2.toDouble()
            "-" -> num2.toDouble() - num1.toDouble()
            "÷" -> num2.toDouble() / num1.toDouble()
            else -> Double.NaN
        }
    }
}