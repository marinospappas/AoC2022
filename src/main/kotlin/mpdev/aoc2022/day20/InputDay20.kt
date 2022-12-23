package mpdev.aoc2022.day20

class InputDay20(var encrList: MutableList<Item>) {

    fun shiftList() {
        (0 until encrList.size).forEach { shiftOneItem(it) }
    }

    fun shiftOneItem(indx: Int) {
        val curPos = encrList[indx].position
        var shiftBy = encrList[indx].value % encrList.size
        if (shiftBy < 0)
            shiftBy += encrList.size - 1
        var newPos = curPos + shiftBy
        if (newPos >= encrList.size)
            newPos = newPos - encrList.size + 1
        encrList[indx].position = Int.MAX_VALUE  // give the current item temporarily a position far away
        if (newPos > curPos)
            encrList.filter { it.position in (curPos+1)..newPos }.forEach { it.position = it.position - 1 }
        else
            if (newPos < curPos)
                encrList.filter { it.position in newPos..(curPos-1) }.forEach { it.position = it.position + 1 }
        encrList[indx].position = newPos
    }

    fun shiftedList(): List<Int> {
        val res = mutableListOf<Int>()
        encrList.sortedBy { it.position }.forEach { res.add(it.value) }
        return res
    }

}

class Item(var value: Int, var position: Int) {
    override fun toString() = "$value, pos=$position"
}
