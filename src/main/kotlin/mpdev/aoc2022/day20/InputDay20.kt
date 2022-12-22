package mpdev.aoc2022.day20

class InputDay20(var encrList: MutableList<Pair<Int,Int>>) {

    companion object {
    }


    fun shiftOneItem(indx: Int) {
        val newPos = (encrList[indx].second + encrList[indx].first % encrList.size) % encrList.size
        for (i in encrList.indices)
            encrList[i] = when (encrList[i].second) {
                in 0 .. newPos -> Pair(encrList[i].first, encrList[i].second - 1)
                in newPos..newPos -> Pair(encrList[i].first,
                    (encrList[i].second + encrList[i].first % encrList.size) % encrList.size)
                else -> encrList[i]
            }
        encrList[indx] = Pair(encrList[indx].first, newPos)
    }

    fun printListSorted() {
        println(encrList.sortedBy { it.second })
    }
}
