package mpdev.aoc2022.day16

import mpdev.aoc2022.utils.Graph
import mpdev.aoc2022.utils.GraphNode

const val VALVE_STATE_OFF = 0
const val VALVE_STATE_ON = 1

class InputDay16(var valveMap: Map<String,Valve>, val connections: List<Pair<String,String>>) {

    // the graph node is defined by the ID of the valve and its State
    // in addition, a function is provided to calculate connected nodes dynamically
    val graph = Graph<Pair<String,Int>> { id -> getConnectedNodes(id) }

    var startId = Pair("AA", VALVE_STATE_OFF)

    init {
        valveMap.forEach { (k,_) ->
            graph.addNode(Pair(k, VALVE_STATE_OFF))
            graph.addNode(Pair(k, VALVE_STATE_ON))
        }
        valveMap = valveMap.toList().sortedBy { (_, v) -> v.id }.reversed().toMap()
    }

    fun getConnectedNodes(id: Pair<String,Int>): List<GraphNode<Pair<String, Int>>> {
        val list = mutableListOf<GraphNode<Pair<String, Int>>>()
        connections.filter { from -> from.first == id.first }.forEach { cnx ->
            if (id.second == VALVE_STATE_OFF && valveMap[id.first]!!.rate > 0)
                list.add(GraphNode(Pair(id.first, VALVE_STATE_ON)) { id -> getConnectedNodes(id) })
            list.add(GraphNode(Pair(cnx.second, valveMap[cnx.second]!!.state)) { id -> getConnectedNodes(id) })
            if (id.second == VALVE_STATE_ON) {
                valveMap[id.first]!!.state = VALVE_STATE_ON
                //println("get connected nodes: valve change to ON: ${id.first}")
            }
        }
        list.forEach { graph.updateCost(id, it.nodeId, 1) }
        //print ("*** calculated connections for $id: ")
        //list.forEach { print("${it.nodeId} ,")  }
        //println()
        return list
    }

    fun getCurrentRate() = valveMap.values.filter { it.state == VALVE_STATE_ON }.sumOf { it.rate }

    fun clearValvesState() {
        valveMap.forEach { (_,v) -> v.state = VALVE_STATE_OFF }
    }

    fun calculateFlowAndTime(path: List<Pair<String,Int>>): Pair<Int,Int> {
        var totalFlow = 0
        var time = 0
        for (i in 0 until path.lastIndex) {
            valveMap[path[i].first]!!.state = path[i].second
            totalFlow += getCurrentRate() * 1 // rate per minute
            ++time
        }
        return Pair(totalFlow,time)
    }


}

/**
 * a Valve will translate to two nodes in the Graph, (1) for state OFF and (2) for state ON
 * the connected nodes will change accordingly depending on the state of the valve
 */
class Valve(val id: String, val rate: Int) {
    var state: Int = VALVE_STATE_OFF
    override fun toString() = "valve id:$id, rate: $rate, state:${if (state==1) "ON" else "OFF"}"
}
