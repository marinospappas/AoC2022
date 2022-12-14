package mpdev.aoc2022.day13

import mpdev.aoc2022.common.RunTimeException
import mpdev.aoc2022.day02.PlayData
import java.util.Stack

const val LEFT_BRACKET = 0
const val RIGHT_BRACKET = 1
const val COMMA = 2
const val NUMBER = 3

class Token (var type: Int, var value: Int = -1) {

    override fun equals(other: Any?): Boolean {
        return other is Token && this.type == other.type && this.value == other.value
    }
    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + type
        hash = hash * 31 + value
        return hash
    }

    override fun toString(): String {
        return when(this.type) {
            LEFT_BRACKET -> "["
            RIGHT_BRACKET -> "]"
            COMMA -> ","
            NUMBER -> this.value.toString()
            else -> "UNRECOGNISED TOKEN"
        }
    }
}

class CompareTokens {

    companion object : Comparator<List<Token>> {

        override fun compare(a: List<Token>?, b: List<Token>?): Int {
            if (a == null && b == null) return 0
            if (a != null && b == null) return 1
            if (a == null && b != null) return -1
            return TokenListUtils.compare(a!!, b!!)
        }
    }
}

object TokenListUtils {

    private var inputString: String = ""
    private var indx: Int = 0
    private var nextChar: Char = ' '
    private val zero = 0
    private val endOfString = zero.toChar()

    fun tokenise(s: String): List<Token> {
        val tokenList = mutableListOf<Token>()
        if (s.isEmpty())
            return tokenList
        inputString = s
        indx = 0
        getNextChar()
        while (nextChar != endOfString) {
            when (nextChar) {
                '[' -> { tokenList.add(Token(LEFT_BRACKET)); getNextChar() }
                ']' -> { tokenList.add(Token(RIGHT_BRACKET)); getNextChar() }
                ',' ->  { tokenList.add(Token(COMMA)); getNextChar() }
                else -> tokenList.add(Token(NUMBER, getNumber()))
            }
        }
        return tokenList
    }

    fun getFirstObject(list: List<Token>): List<Token> {
        val stack = Stack<Token>()
        val result = mutableListOf<Token>()
        if (list.isEmpty()) return result
        if (list.size == 1) return list
        if (list.first().type != LEFT_BRACKET && list.last().type != RIGHT_BRACKET)
            throw RunTimeException("invalid token list size: ${list.size}, first: ${list.first()}, last: ${list.last()}")
        for (i in 1 until list.size-1) {
            result.add(list[i])
            when (list[i].type) {
                LEFT_BRACKET -> stack.push(list[i])
                RIGHT_BRACKET -> stack.pop()
            }
            if (stack.isEmpty())
                break
        }
        return result
    }

    fun getNextPart(list: List<Token>): List<Token> {
        val stack = Stack<Token>()
        if (list.isEmpty()) return listOf()
        if (list.first().type != LEFT_BRACKET && list.last().type != RIGHT_BRACKET)
            throw RunTimeException("invalid token list size: ${list.size}, first: ${list.first()}, last: ${list.last()}")
        for (i in 1 until list.size - 1) {
            when (list[i].type) {
                LEFT_BRACKET -> stack.push(list[i])
                RIGHT_BRACKET -> stack.pop()
            }
            if (stack.isEmpty() && list[i+1].type == COMMA)
                return listOf(Token(LEFT_BRACKET)) + list.subList(i+2, list.size)
        }
        return listOf()
    }

    fun compare(a: List<Token>, b: List<Token>): Int {
        // both integers - return the comparison of the two integers
        if (a.size == 1 && a.first().type == NUMBER
            && b.size == 1 && b.first().type == NUMBER)
            return a.first().value - b.first().value

        var objA = getFirstObject(a)
        var objB = getFirstObject(b)

        var result: Int

        if (objA.isEmpty() && objB.isNotEmpty())
            return -1
        if (objA.isNotEmpty() && objB.isEmpty())
            return 1
        if (objA.isEmpty() && objB.isEmpty())
            return 0
        else {
            // convert both objects to lists
            if (objA.size == 1 && objA.first().type == NUMBER
                && objB.first().type == LEFT_BRACKET)
                objA = tokenToList(objA.first()) as MutableList<Token>
            if (objB.size == 1 && objB.first().type == NUMBER
                && objA.first().type == LEFT_BRACKET)
                objB = tokenToList(objB.first()) as MutableList<Token>
            result = compare(objA, objB)
            if (result != 0)
                return result
            else {
                objA = getNextPart(a)
                objB = getNextPart(b)
                if (numberOfTokens(objA) == 0 && numberOfTokens(objB) > 0)
                    return -1
                if (numberOfTokens(objA) > 0 && numberOfTokens(objB) == 0)
                    return 1
                if (numberOfTokens(objA) == 0 && numberOfTokens(objB) == 0)
                    return 0
                return compare(objA, objB)
            }
        }
    }

    fun numberOfTokens(l: List<Token>): Int {
        var len = 0
        var depth = -1
        for (i in l.indices) {
            when (l[i].type) {
                LEFT_BRACKET -> ++depth
                RIGHT_BRACKET -> --depth
            }
            if (depth == 0 && l[i].type == COMMA
                || depth == -1 && l[i].type == RIGHT_BRACKET && l[i-1].type != LEFT_BRACKET)
                ++len
        }
        return len
    }

    fun tokenToList(t: Token) =
        listOf(Token(LEFT_BRACKET), t, Token(RIGHT_BRACKET))

    fun depth(l: List<Token>): Int {
        var thisDepth = -1
        var maxDepth = Integer.MIN_VALUE
        l.forEach { token ->
            if (token.type == LEFT_BRACKET)
                ++thisDepth
            if (token.type == RIGHT_BRACKET)
                --thisDepth
            if (thisDepth > maxDepth)
                maxDepth = thisDepth
        }
        return maxDepth
    }

    fun toString(tokenList: List<Token>): String {
        var s = ""
        tokenList.forEach { token -> s += token.toString() }
        return s
    }

    private fun getNextChar(): Char {
        if (indx >= inputString.length)
            nextChar = endOfString
        else
            nextChar =  inputString[indx++]
        return nextChar
    }

    private fun getNumber(): Int {
        var number = nextChar.digitToInt()
        var c = getNextChar()
        while (c in '0'..'9') {
            number = number * 10 + c.digitToInt()
            c = getNextChar()
        }
        return number
    }

}