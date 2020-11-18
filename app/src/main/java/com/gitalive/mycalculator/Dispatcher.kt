package com.gitalive.mycalculator

import android.util.Log
import java.util.*

class Dispatcher {
    fun dispatcher(list: ArrayList<String>, stack: Stack<String>, opr: String): ArrayList<String> {
        if (judge(stack, opr)) {
            Log.d("Tag", "dispatcher: $opr added to stack")
            stack.push(opr)
        } else {
            Log.d("Tag", "dispatcher: $opr went to subDispatcher")
            subDispatcher(list, stack, opr)
        }
        return list
    }

    private fun judge(stack: Stack<String>, opr: String): Boolean {
        if (stack.isEmpty()) {
            return true
        } else {
            if (getPriority(stack.peek()) < getPriority(opr) || opr == "(" || stack.peek() == "(") {
                return true
            }
        }
        return false
    }

    private fun getPriority(opr: String): Int {
        return when (opr) {
            "+" -> 1
            "×" -> 2
            "-" -> 1
            "÷" -> 2
            else -> -1
        }
    }

    private fun subDispatcher(list: ArrayList<String>, stack: Stack<String>, opr: String) {
        if (opr == ")") {
            rightBrackets(list, stack, opr)
        } else {
            while (stack.isNotEmpty() && getPriority(stack.peek()) >= getPriority(opr)) {
                Log.d("Tag", "subDispatcher: now pop ${stack.peek()}")
                list.add(stack.pop())
            }
            stack.push(opr)
        }
    }

    private fun rightBrackets(list: ArrayList<String>, stack: Stack<String>, opr: String){
        var peek = ""
        while (peek != "(") {
            peek = stack.pop()
            Log.d("Tag", "damnRightBrackets: now pop $peek")
            if (peek == "(") {
                if (stack.isNotEmpty()) {
                    Log.d("Tag", "damnRightBrackets: now peek is ${stack.peek()}")
                    list.add(stack.pop())
                }
                break
            }
            list.add(peek)
        }
    }
}