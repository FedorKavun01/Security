package Lab1

class IndexOfConsidenseAnalizer { // 3
    fun analyzeKey(string: String) {
        val indexOfConsidenseArray: Array<Int> = Array(string.length) { 0 }

        for (i in string.indices) {
            for (j in string.indices) {
                if (i != 0 && string[j] == string[(i + j) % string.length]) {
                    indexOfConsidenseArray[i]++
                }
            }
        }

        var min: Int = indexOfConsidenseArray[0]
        var max: Int = indexOfConsidenseArray[0]

//        for (index in indexOfConsidenseArray) {
//            min = Math.min(min, index)
//            max = Math.max(max, index)
//        }

        for (i in 0..8) {
            min = Math.min(min, indexOfConsidenseArray[i])
            max = Math.max(max, indexOfConsidenseArray[i])
        }

        var indexOfMax = indexOfConsidenseArray.indexOf(max)

        for (i in indexOfConsidenseArray.indices) {
//            println("$i - " + indexOfConsidenseArray[i] + " [ ${map(indexOfConsidenseArray[i], min, max, 1, 30)} ]")
        }
        println("$indexOfMax - " + indexOfConsidenseArray[indexOfMax] + " [ ${map(indexOfConsidenseArray[indexOfMax], min, max, 1, 30)} ]")
    }

    private fun map(x: Int, inMin: Int, inMax: Int, outMin: Int, outMax: Int): Int
    {
        return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
    }
}