package mpdev.aoc2022.day20

class Day20(var encrList: MutableList<Item>) {

    val DECR_KEY = 811_589_153

    fun getCoordinatesSum(): Long {
        val pos0 = encrList.indexOfFirst { it.value == 0L }
        return listOf(1000, 2000, 3000).sumOf { encrList[(pos0 + it) % encrList.size].value }
    }

    fun processDecrKey() {
        encrList.forEach { it.value = it.value * DECR_KEY }
    }

    fun shiftList_(noOfTimes: Int = 1) {
        for (i in 1..noOfTimes) { encrList.indices.forEach { shiftOneItem(it) } }
    }

    fun shiftList(noOfTimes: Int = 1) {
        for (i in 1..noOfTimes) {
            encrList.indices.forEach { origPos ->
                val pos = encrList.indexOfFirst { it.position == origPos }
                val item = encrList.removeAt(pos)
                encrList.add((pos+item.value).mod(encrList.size), item)
                //encrList.forEach { print("${it.value}, ") }; println()
            }
        }
    }

    fun shiftOneItem(indx: Int) {
        val curPos = encrList[indx].position
        var newPos = (curPos + encrList[indx].value) % encrList.size
        //println("moving ${encrList[indx].value} from pos $curPos to pos $newPos")
        if (newPos >= encrList.size-1) newPos = newPos - encrList.size + 1
        if (newPos <= 0) newPos = encrList.size + newPos - 1
        println("moving ${encrList[indx].value} from pos $curPos to pos $newPos")
        encrList.filter { it.position in curPos+1..newPos }.forEach { it.position = it.position - 1 }
        encrList.filter { it.position in newPos..curPos-1 }.forEach { it.position = it.position + 1 }
        encrList[indx].position = newPos.toInt()
    }

    fun shiftedList(): List<Long> {
        val res = mutableListOf<Long>()
        encrList.sortedBy { it.position }.forEach { res.add(it.value) }
        return res
    }
}

class Item(var value: Long, var position: Int) {
    override fun toString() = "val=$value, origpos=$position"
}
