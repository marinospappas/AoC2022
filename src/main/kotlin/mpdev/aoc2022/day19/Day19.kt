package mpdev.aoc2022.day19

import java.io.File
import java.io.PrintWriter
import java.lang.StringBuilder
import java.util.PriorityQueue
import kotlin.math.ceil
import kotlin.math.max

class Day19(var blueprintList: List<BluePrint>) {

    fun calculateGeodes(bluePrint: BluePrint, maxTime: Int): Int {
        var geodes = 0
        val start = State(1, 1, 0, 0, 0)
        start.ore = 1
        var count = 0
        val pQueue = PriorityQueue<State>().also { it.add(start) }
        while (pQueue.isNotEmpty()) {
            ++ count
            val state = pQueue.poll()
            if (state.canProduceMore(geodes, maxTime))
                pQueue.addAll(state.getNextStates(bluePrint, maxTime))
            geodes = max(geodes, state.geodes)
        }
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
        var newState: State?
        if (bluePrint.maxOreCost > oreRobots && ore > 0) {
            if (buildNewState(bluePrint, maxTime, incrOreRobots = 1).also { newState = it } != null)
                nextStates.add(newState!!)
        }
        if (bluePrint.maxClayCost > clayRobots && ore > 0) {
            if (buildNewState(bluePrint, maxTime, incrClayRobots = 1).also { newState = it } != null)
                nextStates.add(newState!!)
        }
        if (bluePrint.maxObsidianCost > obsidianRobots && ore > 0 && clay > 0) {
            if (buildNewState(bluePrint, maxTime, incrObsidianRobots = 1).also { newState = it } != null)
                nextStates.add(newState!!)
        }
        if (ore > 0 && obsidian > 0) {
            if (buildNewState(bluePrint, maxTime, incrGeodeRobots = 1).also { newState = it } != null)
                nextStates.add(newState!!)
        }
        return nextStates
    }

    private fun buildNewState(
        bluePrint: BluePrint, maxTime: Int, incrOreRobots: Int = 0, incrClayRobots: Int = 0,
        incrObsidianRobots: Int = 0, incrGeodeRobots: Int = 0
    ): State? {
        val requiredTime =
            when {
            incrOreRobots == 1 ->
                if (ore >= bluePrint.oreRobot.oreCost) 1
                else ceil((bluePrint.oreRobot.oreCost - ore) / oreRobots.toDouble()).toInt() + 1
            incrClayRobots == 1 ->
                if (ore >= bluePrint.clayRobot.oreCost) 1
                else ceil((bluePrint.clayRobot.oreCost - ore) / oreRobots.toDouble()).toInt() + 1
            incrObsidianRobots == 1 -> {
                // max of clay req. time and ore req.time
                maxOf(if (clay >= bluePrint.obsidianRobot.clayCost) 1
                    else ceil((bluePrint.obsidianRobot.clayCost - clay) / clayRobots.toDouble()).toInt() + 1,
                    if (ore >= bluePrint.obsidianRobot.oreCost) 1
                    else ceil((bluePrint.obsidianRobot.oreCost - ore) / oreRobots.toDouble()).toInt() + 1)
            }
            incrGeodeRobots == 1 -> {
                // max of obsidian req. time and ore req.time
                maxOf(if (obsidian >= bluePrint.geodeRobot.obsidianCost) 1
                    else ceil((bluePrint.geodeRobot.obsidianCost - obsidian) / obsidianRobots.toDouble()).toInt() + 1,
                    if (ore >= bluePrint.geodeRobot.oreCost) 1
                    else ceil((bluePrint.geodeRobot.oreCost - ore) / oreRobots.toDouble()).toInt() + 1)
            }
            else -> 1
        }

        if (time + requiredTime > maxTime)
            return null
        return State(
            time + requiredTime,
            oreRobots + incrOreRobots,
            clayRobots + incrClayRobots,
            obsidianRobots + incrObsidianRobots,
            geodeRobots + incrGeodeRobots,
            ore + requiredTime * oreRobots - incrOreRobots * bluePrint.oreRobot.oreCost - incrClayRobots * bluePrint.clayRobot.oreCost -
                    incrObsidianRobots * bluePrint.obsidianRobot.oreCost - incrGeodeRobots * bluePrint.geodeRobot.oreCost,
            clay + requiredTime * clayRobots - incrObsidianRobots * bluePrint.obsidianRobot.clayCost,
            obsidian + requiredTime * obsidianRobots - incrGeodeRobots * bluePrint.geodeRobot.obsidianCost,
            geodes + requiredTime * geodeRobots
        )
    }

    fun canProduceMore(bestSoFar: Int, maxTime: Int): Boolean {
        // if a state cannot produce more total geodes than the best so far geodes produced already
        // will not be considered as it cannot beat the previous state
        // maximum theoretical production is when we produce one geode robot per minute
        val totalGeodes = geodes + (0 until (maxTime-time)).sumOf { it + geodeRobots }
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
