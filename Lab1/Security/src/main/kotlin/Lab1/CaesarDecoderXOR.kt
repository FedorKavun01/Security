package Lab1

import kotlin.experimental.xor

class CaesarDecoderXOR {
    fun decode(cipher: String, step: Int): String {
        val bytes = cipher.toByteArray()
        val resultBytes = ArrayList<Byte>()
        for (byte in bytes) {
            resultBytes.add((byte.toInt().xor(step)).toByte())
        }
        val result = String(resultBytes.toByteArray())
        return result
    }

    fun decodeXorBruteforce(input: String): Result
    {
        val frequancyAnalizer = FrequancyAnalizer()
        val results: ArrayList<Result> = ArrayList()

        for (key in 0..Byte.MAX_VALUE)
        {
            results.add(Result((key.toChar()).toString(), decode(input, key)))
        }

        var index = 1
        var maxCount: Float = 0f

        for (i in results.indices)
        {
            val p = frequancyAnalizer.getNormalTextPercent(results[i].value);

//            println("[$p] =" + results[i])
            if (p > maxCount)
            {
                maxCount = p;
                index = i;
            }
        }

        return results[index];
    }
}