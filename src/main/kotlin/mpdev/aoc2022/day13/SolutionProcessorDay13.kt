package mpdev.aoc2022.day13

import mpdev.aoc2022.common.*

class SolutionProcessorDay13: SolutionProcessor<InputDay13> {

    /** part 1 calculation */
    override fun part1(input: InputDay13): String {
        var res = 0
        val compMap = mutableListOf<Int>().also { c -> input.packetList
            .forEach { c.add(TokenListUtils.compare(it.first.data, it.second.data)) } }
            .mapIndexed { index: Int, value: Int -> index + 1 to value }.toMap()
        compMap.forEach { (k, v) -> if (v < 0) res += k }
        return res.toString()
    }

    /** part 2 calculation */
    override fun part2(input: InputDay13): String {
        val packets = mutableListOf<Packet>()
        val packet2 = Packet("[[2]]")
        val packet6 = Packet("[[6]]")
        input.inputList.forEach { packets.add(Packet(it.first)); packets.add(Packet(it.second)) }
        packets.addAll(listOf(packet2, packet6))
        packets.sortWith(PacketComparator::compare)
        return ((packets.indexOf(packet2)+1) * (packets.indexOf(packet6)+1)).toString()
    }

}