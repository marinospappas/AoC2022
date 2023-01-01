package mpdev.aoc2022.day19

import java.io.File
import java.io.PrintWriter
import java.lang.StringBuilder
import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.roundToInt

class Day19(var blueprintList: List<BluePrint>) {

    var maxTime = 24
    val debug: PrintWriter

    init {
        debug = File("day19debug.txt").printWriter()
    }

    fun printQueue(queue: PriorityQueue<State>, geodes: Int, count: Int) {
        debug.println("Count = $count  Geodes = $geodes")
        if(queue.isNotEmpty())
            debug.println(queue.peek())
    }

    fun calculateGeodes(bluePrint: BluePrint, maxTime: Int): Int {
        var geodes = 0
        val start = State(1, 1, 0, 0, 0)
        start.ore = 1
        var count = 0
        val pQueue = PriorityQueue<State>().also { it.add(start) }
        while (pQueue.isNotEmpty()) {
            ++ count
            val state = pQueue.poll()
            if (state.canProduceMore(geodes, maxTime)) {
                val newStates = state.getNextStates(bluePrint, maxTime)
                if (count < 20000) {
                    debug.println("\ncount $count")
                    debug.println("current state $state")
                    newStates.forEach { debug.println(it) }
                }
                pQueue.addAll(newStates)
            }
            geodes = max(geodes, state.geodes)
        }
        debug.close()
        return geodes
    }
}

data class State(var time: Int, var oreRobots: Int, var clayRobots: Int, var obsidianRobots: Int, var geodeRobots: Int,
                 var ore: Int = 0, var clay: Int = 0, var obsidian: Int = 0, var geodes: Int = 0): Comparable<State> {

    override fun compareTo(other: State) = other.geodes.compareTo(this.geodes) // reverse comparison
    // state with largest geodes number will be top of the queue

    fun getNextStates(bluePrint: BluePrint, maxTime: Int): List<State> {
        if (time >= maxTime)
            return mutableListOf()
        val nextStates = mutableListOf<State>()
        if (oreRobots in 1 until bluePrint.maxOreCost)
            nextStates.add(buildNewState(bluePrint, maxTime, incrOreRobots = 1))
        if (clayRobots < bluePrint.maxClayCost && oreRobots > 0)
            nextStates.add(buildNewState(bluePrint, maxTime, incrClayRobots = 1))
        if (obsidianRobots < bluePrint.maxObsidianCost && oreRobots > 0 && clayRobots > 0)
            nextStates.add(buildNewState(bluePrint, maxTime, incrObsidianRobots = 1))
        if (oreRobots > 0 && obsidianRobots > 0)
            nextStates.add(buildNewState(bluePrint, maxTime, incrGeodeRobots = 1))
        return nextStates
    }

    private fun buildNewState(bluePrint: BluePrint, maxTime: Int, incrOreRobots: Int = 0, incrClayRobots: Int = 0,
                              incrObsidianRobots: Int = 0, incrGeodeRobots: Int = 0): State {
        val requiredTime = when {
            incrOreRobots == 1 -> if (ore >= bluePrint.oreRobot.oreCost) 1
                else ((bluePrint.oreRobot.oreCost - ore) / oreRobots.toDouble()).roundToInt()
            incrClayRobots == 1 -> if (ore >= bluePrint.clayRobot.oreCost) 1
                else ((bluePrint.clayRobot.oreCost - ore) / oreRobots.toDouble()).roundToInt()
            incrObsidianRobots == 1 -> if (clay >= bluePrint.obsidianRobot.clayCost) 1
                else ((bluePrint.obsidianRobot.clayCost - clay) / clayRobots.toDouble()).roundToInt()
            incrGeodeRobots == 1 -> if (obsidian >= bluePrint.geodeRobot.obsidianCost) 1
                else ((bluePrint.geodeRobot.obsidianCost - obsidian) / obsidianRobots.toDouble()).roundToInt()
            else -> 1
        }
        if (time+requiredTime > maxTime) {
            val newState = State(maxTime, oreRobots, clayRobots, obsidianRobots, geodeRobots)
            newState.ore = ore + (maxTime-time) * oreRobots - incrOreRobots * bluePrint.oreRobot.oreCost
            newState.clay = clay + (maxTime-time) * clayRobots
            newState.obsidian = obsidian + (maxTime-time) * obsidianRobots
            newState.geodes = geodes + (maxTime-time) * geodeRobots
            return newState
        }
        val newState = State(time+requiredTime, oreRobots+incrOreRobots, clayRobots+incrClayRobots,
            obsidianRobots+incrObsidianRobots, geodeRobots+incrGeodeRobots)
        newState.ore = ore + requiredTime * oreRobots - incrOreRobots * bluePrint.oreRobot.oreCost - incrClayRobots * bluePrint.clayRobot.oreCost -
                incrObsidianRobots * bluePrint.obsidianRobot.oreCost - incrGeodeRobots * bluePrint.geodeRobot.oreCost
        newState.clay = clay + requiredTime * clayRobots - incrObsidianRobots * bluePrint.obsidianRobot.clayCost
        newState.obsidian = obsidian + requiredTime * obsidianRobots - incrGeodeRobots * bluePrint.geodeRobot.obsidianCost
        newState.geodes = geodes + requiredTime * geodeRobots
        return newState
    }

    fun canProduceMore(bestSoFar: Int, maxTime: Int): Boolean {
        // if a state cannot produce more total geodes than the best so far geodes produced already
        // will not be considered as it cannot beat the previous state
        // maximum theoretical production is when we produce one geode robot per minute
        val totalGeodes = geodes + (maxTime-time)*geodes + (maxTime-time)*(maxTime-time-1)
        return totalGeodes > bestSoFar
    }
}

/*
Blueprint 1:
    Each ore robot costs 4 ore.
    Each clay robot costs 2 ore.
    Each obsidian robot costs 3 ore and 14 clay.
    Each geode robot costs 2 ore and 7 obsidian.
 */
data class BluePrint(val id: Int,
                val oreRobot: OreRobot,
                val clayRobot: ClayRobot,
                val obsidianRobot: ObsidianRobot,
                val geodeRobot: GeodeRobot) {
    val maxOreCost: Int = maxOf(oreRobot.oreCost, clayRobot.oreCost, obsidianRobot.oreCost, geodeRobot.oreCost)
    val maxClayCost: Int = obsidianRobot.clayCost
    val maxObsidianCost: Int = geodeRobot.obsidianCost

    override fun toString() = StringBuilder().also { s ->
        s.append("Blueprint: $id\n").append("\t$oreRobot\n").append("\t$clayRobot\n").append("\t$obsidianRobot\n")
            .append("\t$geodeRobot\n")
    }.toString()
}

data class OreRobot(val oreCost: Int)
data class ClayRobot(val oreCost: Int)
data class ObsidianRobot(val oreCost: Int, val clayCost:Int)
data class GeodeRobot(val oreCost: Int, val obsidianCost: Int)
