package main.kotlin

interface Operation {
    fun evaluate(input: String): Boolean
    fun size(): Int
}

class NameOperation(val name: String) : Operation {
    var operation: Operation? = null

    constructor(name: String, opt: Operation) : this(name) {
        operation = opt
    }

    override fun evaluate(input: String): Boolean {
        if (operation == null) {
            return false
        }
        return operation!!.evaluate(input)
    }

    override fun size(): Int {
        if (operation == null) {
            return 0
        }
        return operation!!.size()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is NameOperation) {
            return false
        }

        return name == other.name
    }

//    override fun toString(): String {
//        return "NameOperation($name, $operation)"
//    }

    override fun toString(): String {
        return operation.toString()
    }
}

class EqualOperation(private val value: String) : Operation {
    override fun evaluate(input: String): Boolean {
        return input == value
    }

    override fun size(): Int {
        return 1
    }

//    override fun toString(): String {
//        return "EqualOperation($value)"
//    }

    override fun toString(): String {
        return value
    }

    override fun equals(other: Any?): Boolean {
        if (other !is EqualOperation) {
            return false
        }

        return value == other.value
    }
}

class AndOperation(vararg val operators: Operation) : Operation {
    override fun evaluate(input: String): Boolean {
        var startIndex = 0
        for (operator in this.operators) {
            val size = operator.size()
            if (!operator.evaluate(input.substring(startIndex, startIndex + size))) {
                return false
            }
            startIndex += size
        }

        return true
    }

    override fun size(): Int {
        return operators.sumOf { it.size() }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AndOperation) {
            return false
        }
        if (this.size() != other.size()) {
            return false
        }

        return operators.mapIndexed { i, opt -> other.operators[i] == opt }.firstOrNull { !it } == null
    }

//    override fun toString(): String {
//        val innerStr = operators.joinToString(", ") { it.toString() }
//        return "AndOperation($innerStr)"
//    }

    override fun toString(): String {
        return operators.joinToString("") { it.toString() }
    }
}

class OrOperation(vararg val operators: Operation) : Operation {
    override fun evaluate(input: String): Boolean {
        return operators.any { it.evaluate(input) }
    }

    override fun size(): Int {
        return operators.first().size()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is OrOperation) {
            return false
        }
        if (this.size() != other.size()) {
            return false
        }


        return operators.mapIndexed { i, opt -> other.operators[i] == opt }.firstOrNull { !it } == null
    }

//    override fun toString(): String {
//        val innerStr = operators.joinToString(", ") { it.toString() }
//        return "OrOperation($innerStr)"
//    }

    override fun toString(): String {
        return operators.joinToString(" | ") { it.toString() }
    }
}

fun buildOperations(lines: List<String>): Map<String, Operation> {
    val result = mutableMapOf<String, Operation>()

    lines
        .takeWhile(String::isNotEmpty)
        .map { parseOperationStr(it) }
        .forEach { opt ->
            result[opt.first] = opt.second
        }

    result.values.forEach { fillOperation(it, result) }

    return result
}

fun fillOperation(opt: Operation, optMap: Map<String, Operation>) {
    if (opt is OrOperation) {
        opt.operators.forEach { fillOperation(it, optMap) }
        return
    }
    if (opt is AndOperation) {
        opt.operators.forEach { fillOperation(it, optMap) }
        return
    }
    if (opt is NameOperation) {
        opt.operation = optMap[opt.name]
        return
    }
}