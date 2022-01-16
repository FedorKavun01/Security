package Lab1

import java.util.*

class SubstitutionCipher {
    private val ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    fun decrypt(cipher: String, key: List<Char>): String {
        val input = cipher.uppercase(Locale.getDefault()).toCharArray()
        val output = CharArray(input.size)

        for (i in input.indices) {
            output[i] = key[input[i] - 'A']
        }

        return String(output)
    }

    fun encrypt(text: String, key: ArrayList<Char>): String {
        val input = text.uppercase(Locale.getDefault()).replace(" ", "").toCharArray()
        val output = CharArray(input.size)

        for (i in input.indices) {
            if (Character.isAlphabetic(input[i].toInt())) {
                output[i] = ALPHABET[key.indexOf(input[i])]
            }
        }

        return String(output)
    }
}