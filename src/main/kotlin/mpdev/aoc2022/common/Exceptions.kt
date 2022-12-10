package mpdev.aoc2022.common

/** Custom Exceptions */
class ProgramArgException(override var message: String): Exception(message)

class InputDataException(override var message: String): Exception(message)

class RunTimeException(override var message: String): Exception(message)
