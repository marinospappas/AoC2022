package mpdev.aoc2022.day20

class Day20(var dataList: MutableList<Item>) {

    private val DECR_KEY = 811_589_153

    fun getCoordinatesSum(): Long {
        val pos0 = dataList.indexOfFirst { it.value == 0L }
        return listOf(1000, 2000, 3000).sumOf { dataList[(pos0 + it) % dataList.size].value }
    }

    fun processDecrKey() {
        dataList.forEach { it.value = it.value * DECR_KEY }
    }

    fun shiftList() {
        dataList.indices.forEach { origPos ->
            val pos = dataList.indexOfFirst { it.originalPosition == origPos }
            val item = dataList.removeAt(pos)
            dataList.add((pos + item.value).mod(dataList.size), item)
        }
    }

    fun shiftedList() = mutableListOf<Long>().also { l -> dataList.forEach { l.add(it.value) } }
}

data class Item(var value: Long, var originalPosition: Int)
