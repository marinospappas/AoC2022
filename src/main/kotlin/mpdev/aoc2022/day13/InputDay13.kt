package mpdev.aoc2022.day13

import mpdev.aoc2022.common.InputDataException


class InputDay13(var inputList: List<Pair<String,String>>) {

    var packetList: MutableList<Pair<Packet,Packet>> = mutableListOf()

    init {
        inputList.forEach { packetList.add(Pair( Packet(TokenListUtils.tokenise(it.first)), Packet(TokenListUtils.tokenise(it.second)) )) }
    }
}

class Packet() {
    constructor(listofTs: List<Token>) : this() {
        data = listofTs
    }
    lateinit var data: List<Token>
    override fun toString(): String {
        return StringBuilder().also { s-> data.forEach { s.append(it) } }.toString()
    }
}

/** string class extensions */
fun String.toPacket(): List<Any> {
    val packet = mutableListOf<Any>()
    if (this.isEmpty())
        return packet
    else {
        val list = this.splitToPacketStrings()
        list.forEach {
            if (it.isEmpty())
                packet.add(it)
            else
            if (it.first() in '0'..'9')
                packet.add(it)
            else
            if (it.first() == '[')
                packet.add(it.toPacket())
            else
                throw InputDataException("bad packet string: $this")
        }
    }

    /*
    if (this.first() in '0'..'9') {
        this.splitToPacketStrings().forEach {
            if (it.first() in '0'..'9')
                packet.add(it)
            else
                packet.add(it.toPacket())
        }
    }
     */

    return packet
}

fun String.splitToPacketStrings(): List<String> {
    val res = mutableListOf<String>()
    var to = 0
    var reduce = true
    var level = 0
    var len = this.length
    for (i in 0 until this.length) {
        if (this[i] == '[') ++level
        else
        if (this[i] == ']') --level
        else
        if (this[i] == ',' && level == 0)
            reduce = false
    }
    if (reduce)
        return listOf(this.substring(1,this.length-1))

    var from = to
    while (to < len) {
        if (this[from] in '0'..'9') {
            to = this.findNextComma(from)
            res.add(this.substring(from,to++))
            from = to
        }
        else
        if (this[from] == '[') {
            to = this.findNextCommaAfterBracket(from)
            res.add(this.substring(from,to++))
            from = to
        }
        else
            throw InputDataException("unexpected packet string $this (from pos $from to pos $to)")
    }
    return res
}

fun String.findNextComma(pos: Int): Int {
    var position = pos
    while (position < this.length && this[position] != ',')
        ++position
    return position
}

fun String.findNextCommaAfterBracket(pos: Int): Int {
    var position = pos
    var level = 0
    while (position < this.length) {
        if (this[position] == '[')
            ++level
        if (this[position] == ']')
            --level
        if (this[position] == ',' && level == 0)
            break
        ++position
    }
    if (level > 0)
        throw InputDataException("list does not have closing ']' $this")
    return position
}