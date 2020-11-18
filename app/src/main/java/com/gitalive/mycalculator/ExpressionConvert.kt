package com.gitalive.mycalculator

import java.util.*
import kotlin.collections.ArrayList

class ExpressionConvert {

    fun expressionConvert(expr: String): ArrayList<String> {
        var numbersList = ArrayList<String>()
        val operatorStack = Stack<String>()
        val stringBuilder = StringBuilder(32)

        for (char in expr.toCharArray()) {
            if ("0.123456789".contains(char)){
                stringBuilder.append(char)
            } else {
                numbersList.add(stringBuilder.toString())
                stringBuilder.clear()
                numbersList = Dispatcher().dispatcher(numbersList,operatorStack,char.toString())
            }
        }
        if (stringBuilder.isNotEmpty()){
            numbersList.add(stringBuilder.toString())
        }
        while (operatorStack.isNotEmpty()){
            numbersList.add(operatorStack.pop())
        }
        return numbersList
    }
}