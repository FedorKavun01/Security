package Lab1

import java.util.*

class GeneticAlgorithm(private val trigrams: Map<String, Double>, populationSize: Int, generationCount: Int) {
    private val population: Population
    private val generationCount: Int

    init {
        population = Population.create(populationSize)
        this.generationCount = generationCount
    }

    fun decrypt(cipher: String): Result {
        val substitutionCipher = SubstitutionCipher()
        evaluate(cipher)

        for (i in 0 until generationCount) {
            nextGeneration()
            evaluate(cipher)
//            println(java.lang.String.format("$i -> \t ${population.bestChromosome}"))
        }

        return Result(
            String(population.bestChromosome.genes.toCharArray()),
            substitutionCipher.decrypt(cipher, population.bestChromosome.genes)
        )
    }

    private fun evaluate(cipher: String) {
        for (i in 0 until population.size) {
            val chromosome: Chromosome = population.chromosomes.get(i)
            population.chromosomes.get(i).fitness = fitnessFunction(chromosome, cipher)
        }

        Collections.sort(population.chromosomes)
    }

    private fun fitnessFunction(chromosome: Chromosome, cipher: String): Double {
        val substitutionCipher = SubstitutionCipher()
        val textWorker = TextWorker()
        val decrypt: String = substitutionCipher.decrypt(cipher, chromosome.genes)
        val textTrigrams: Map<String, Double> = textWorker.fromText(3, decrypt)
        var fitness = 0.0

        for (key in textTrigrams.keys) {
            val freq = textTrigrams[key]!!
            val freqEng: Double = if (trigrams.containsKey(key)) trigrams[key]!! else 0.0
            fitness += freq - freqEng
        }

        return fitness
    }

    private fun nextGeneration() {
        val newChromosomes: ArrayList<Chromosome> = ArrayList(population.size)

        for (i in 0 until population.size / 5) {
            newChromosomes.add(population.chromosomes.get(i))
        }

        var i: Int = population.size / 5

        while (i < population.size) {
            val firstPatentChromosome: Chromosome = population.chromosomes[selectionMethod(population)]
            val secondPatentChromosome: Chromosome = population.chromosomes[selectionMethod(population)]
            crossover(firstPatentChromosome, secondPatentChromosome, newChromosomes)
            i += 2
        }

        population.chromosomes = newChromosomes
    }

    private fun selectionMethod(population: Population): Int {
        val totalFitness: Double = population.totalFitness
        val rnd = Random()
        val limit = rnd.nextDouble() * totalFitness
        var sum = 0.0

        for (i in 0 until population.size) {
            sum += population.chromosomes[i].fitness
            if (sum >= limit) {
                return i
            }
        }

        return 0
    }

    fun crossover(firstParentChromosome: Chromosome, secondParentChromosome: Chromosome, newChromosomes: ArrayList<Chromosome>) {
        val rn = Random()
        val end = rn.nextInt(firstParentChromosome.genes.size) + 1
        val start = rn.nextInt(end)
        val firstChildChromosome = Chromosome.create(true)
        val secondChildChromosome = Chromosome.create(true)

        for (i in start until end) {
            firstChildChromosome.genes[i] = secondParentChromosome.genes[i]
            secondChildChromosome.genes[i] = firstParentChromosome.genes[i]
        }

        var i = 0
        while (i < firstParentChromosome.genes.size) {
            if (i == start) {
                i += end - start
                if (i == firstParentChromosome.genes.size) {
                    i++
                    continue
                }
            }

            var index = i

            while (firstChildChromosome.genes.contains(firstParentChromosome.genes[index])) {
                index = firstChildChromosome.genes.indexOf(firstParentChromosome.genes[index])
            }

            firstChildChromosome.genes[i] = firstParentChromosome.genes[index]
            index = i

            while (secondChildChromosome.genes.contains(secondParentChromosome.genes[index])) {
                index = secondChildChromosome.genes.indexOf(secondParentChromosome.genes[index])
            }

            secondChildChromosome.genes[i] = secondParentChromosome.genes[index]
            i++
        }

        newChromosomes.add(firstChildChromosome)
        newChromosomes.add(secondChildChromosome)
    }
}