package mpdev.aoc2022.day13

class InputDay13(var inputList: List<Pair<String,String>>) {
    var packetList: MutableList<Pair<Packet,Packet>> = mutableListOf()
    init {
        inputList.forEach { packetList.add(Pair( Packet(TokenListUtils.tokenise(it.first)), Packet(TokenListUtils.tokenise(it.second)) )) }
    }
}

class Packet() {
    companion object : Comparator<Packet> {
        override fun compare(a: Packet?, b: Packet?): Int {
            if (a == null && b == null) return 0
            if (a != null && b == null) return 1
            if (a == null && b != null) return -1
            return TokenListUtils.compare(a!!.data, b!!.data)
        }
    }
    constructor(listofTs: List<Token>) : this() {
        data = listofTs
    }
    constructor(packetStr: String) : this() {
        data = TokenListUtils.tokenise(packetStr)
    }
    lateinit var data: List<Token>
    override fun toString(): String {
        return StringBuilder().also { s-> data.forEach { s.append(it) } }.toString()
    }
}
