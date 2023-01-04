package mpdev.aoc2022.day25

class Day25(var inputList: List<String>) {

    companion object {
        val snafuList = mutableListOf<Snafu>()
        const val BASE = 5
        val sDigitValue = mapOf(
            '2' to 2,
            '1' to 1,
            '0' to 0,
            '-' to -1,
            '=' to -2
        )
    }

    init {
        snafuList.removeAll { true }
        inputList.forEach { snafuList.add(Snafu(it)) }
    }

    class Snafu(var value: String) {
        fun toLong(): Long {
            var longValue = 0L
            value.toList().forEach { longValue = longValue * BASE + sDigitValue[it]!! }
            return longValue
        }
        override fun toString() = value
    }
}

fun Long.toSnafu(): String {
    var toBase5r = this.toString(5).toList().reversed().toMutableList()
    for(i in toBase5r.indices)
        when (toBase5r[i]) {
            '3' -> {
                toBase5r[i] = '='
                carryOver1(toBase5r, i+1)
            }
            '4' -> {
                toBase5r[i] = '-'
                carryOver1(toBase5r, i+1)
            }
            else -> {}
        }
    return String(toBase5r.reversed().toCharArray())
}

fun carryOver1(l: MutableList<Char>, indx: Int) {
    if (indx >= l.size)
        l.add('1')
    else {
        l[indx] = l[indx] + 1
        if (l[indx] == '5') {
            l[indx] = '0'
            carryOver1(l, indx+1)
        }
    }
}