package Lab1

import java.util.*

class Chromosome(var genes: ArrayList<Char>) : Comparable<Chromosome> {
    var fitness = 0.0

    override operator fun compareTo(other: Chromosome): Int {
        if (fitness > other.fitness) {
            return 1
        } else if (fitness < other.fitness) {
            return -1
        }
        return 0
    }

    override fun toString(): String {
        return String.format("$fitness -> $genes")
    }

    companion object {
        private const val ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

        @JvmOverloads
        fun create(empty: Boolean = false): Chromosome {
            val genes: ArrayList<Char> = ArrayList(ALPHABET.length)

            for (i in 0 until ALPHABET.length) {
                genes.add(if (empty) '_' else ALPHABET[i])
            }

            genes.shuffle()
            return Chromosome(genes)
        }
    }
}