package Lab1

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

class TextWorker {
    fun fromFile(path: String): Map<String, Double> {
        val nGramsMap: MutableMap<String, Double> = HashMap()

        try {
            BufferedReader(FileReader(path)).use { bufferedReader ->
                var line: String

                while (bufferedReader.ready()) {
                    line = bufferedReader.readLine()
                    val strs = line.split(" ").toTypedArray()
                    nGramsMap[strs[0]] = strs[1].toDouble()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return nGramsMap
    }

    fun fromText(n: Int, text: String): Map<String, Double> {
        val nGrams: HashMap<String, Double> = HashMap()
        var count = 0

        for (i in 0 until text.length - n + 1) {
            val ngram = text.substring(i, i + n)
            nGrams[ngram] = if (nGrams.containsKey(ngram)) nGrams[ngram]!! + 1.0 else 1.0
            count++
        }

        for (key in nGrams.keys) {
            nGrams[key] = nGrams[key]!! / count
        }

        return nGrams
    }
}