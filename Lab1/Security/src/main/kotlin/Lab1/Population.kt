package Lab1

class Population(var chromosomes: ArrayList<Chromosome>) {
    val size: Int
        get() = chromosomes.size

    val bestChromosome: Chromosome
        get() = chromosomes[0]

    val totalFitness: Double
        get() {
            var totalFitness = 0.0
            for (chromosome in chromosomes) {
                totalFitness += chromosome.fitness
            }
            return totalFitness
        }

    companion object {
        fun create(size: Int): Population {
            val chromosomes: ArrayList<Chromosome> = ArrayList(size)
            for (i in 0 until size) {
                chromosomes.add(Chromosome.create())
            }
            return Population(chromosomes)
        }
    }
}