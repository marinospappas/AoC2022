package mpdev.aoc2022.day20

class Day20(var dataList: MutableList<Item>) {

    val DECR_KEY = 811_589_153

    fun getCoordinatesSum(): Long {
        val pos0 = dataList.indexOfFirst { it.value == 0L }
        return listOf(1000, 2000, 3000).sumOf { dataList[(pos0 + it) % dataList.size].value }
    }

    fun processDecrKey() {
        dataList.forEach { it.value = it.value * DECR_KEY }
    }

    fun shiftList_(noOfTimes: Int = 1) {
        for (i in 1..noOfTimes) { dataList.indices.forEach { shiftOneItem(it) } }
    }

    fun shiftList() {
        dataList.indices.forEach { origPos ->
            val pos = dataList.indexOfFirst { it.position == origPos }
            val item = dataList.removeAt(pos)
            dataList.add((pos + item.value).mod(dataList.size), item)
            //encrList.forEach { print("${it.value}, ") }; println()
        }
    }

    //fun shiftList() {dataList.decrypt()}

    private fun MutableList<Item>.decrypt() {
        indices.forEach { originalIndex ->
            val index = indexOfFirst { it.position == originalIndex }
            val toBeMoved = removeAt(index)
            println("moving ${toBeMoved.value} from pos $originalIndex to pos ${(index + toBeMoved.value).mod(size)}")
            add((index + toBeMoved.value).mod(size), toBeMoved)
            println("new list -> $this")
        }
        indices.forEach { println("$it: ${this[it]}") }
    }

    fun shiftOneItem(indx: Int) {
        val curPos = dataList[indx].position
        var newPos = (curPos + dataList[indx].value).mod(dataList.size)
        //println("moving ${encrList[indx].value} from pos $curPos to pos $newPos")
        // (newPos >= dataList.size-1) newPos = newPos - dataList.size + 1
        //if (newPos <= 0) newPos = dataList.size + newPos - 1
        println("moving ${dataList[indx].value} from pos $curPos to pos $newPos")
        dataList.filter { it.position in curPos+1..newPos }.forEach { it.position = it.position - 1 }
        dataList.filter { it.position in newPos..curPos-1 }.forEach { it.position = it.position + 1 }
        dataList[indx].position = newPos
    }

    fun shiftedList(): List<Long> {
        val res = mutableListOf<Long>()
        dataList.sortedBy { it.position }.forEach { res.add(it.value) }
        return res
    }
}

data class Item(var value: Long, var position: Int) {
    override fun toString() = "$value"
}
