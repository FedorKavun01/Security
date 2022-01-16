import kotlin.experimental.xor

class Salsa20Decoder {
    fun decode(ciphers: ArrayList<CharArray>, key: String) {
        val frequancyAnalizer = FrequancyAnalizer()
        var bestTextPercent = 0f
        var bestText: List<String> = ArrayList(ciphers.size)

        for (first in ciphers.indices)
        {
            val currentText = ArrayList<String>(ciphers.size)

            for (second in ciphers.indices)
            {
                val minLength: Int = Math.min(ciphers[first].size, ciphers[second].size)
                val cipherArrayFirst = ciphers[first].copyOf(minLength)
                val cipherArraySecond = ciphers[second].copyOf(minLength)

                val xorArray = xor(cipherArrayFirst, cipherArraySecond);
                val keyArray = key.toCharArray()
                val resultArray = xor(xorArray, keyArray);
                val resultStr = String(resultArray)
                currentText.add(resultStr);
                println("{$first}:{$second} -> \t + $resultStr")
            }

            val strBuilder = StringBuilder();
            for (line in currentText)
            {
                val length = Math.min(key.length, line.length)
                strBuilder.append(line.substring(0, length));
            }

            var currentTextPercent = frequancyAnalizer.getNormalTextPercent(strBuilder.toString())
            if (bestTextPercent < currentTextPercent)
            {
                bestTextPercent = currentTextPercent;
                bestText = currentText;
            }
        }


        for (i in bestText.indices)
        {
            var length = Math.min(key.length, bestText[i].length)
            println(i)
            print(bestText[i].substring(0, length))
            print("=======================")
            println(bestText[i].substring(length))
            println("")
        }
    }

    fun xor(input: CharArray, key: CharArray): CharArray {
        val output = CharArray(input.size)
        for (i in output.indices) {
            output[i] = (input[i].toInt() xor key[i % key.size].toInt()).toChar()
        }
        return output
    }
}