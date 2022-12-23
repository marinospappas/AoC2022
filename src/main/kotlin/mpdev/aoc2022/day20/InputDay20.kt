package mpdev.aoc2022.day20

class InputDay20(var encrList: MutableList<Item>) {

    fun getCoordinatesSum(): Int {
        var posX = getPosForValue(0) + 1000 % encrList.size
        if (posX >= encrList.size)
           posX = posX - encrList.size
        var posY = getPosForValue(0) + 2000 % encrList.size
        if (posY >= encrList.size)
            posY = posY - encrList.size
        var posZ = getPosForValue(0) + 3000 % encrList.size
        if (posZ >= encrList.size)
            posZ = posZ - encrList.size
        return getValInPosition(posX) + getValInPosition(posY) + getValInPosition(posZ)
    }

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

    fun getValInPosition(p: Int) =
        encrList.filter { it.position == p }.first().value

    fun getPosForValue(v: Int) =
        encrList.filter { it.value == v }.first().position
}

class Item(var value: Int, var position: Int) {
    override fun toString() = "$value, pos=$position"
}
