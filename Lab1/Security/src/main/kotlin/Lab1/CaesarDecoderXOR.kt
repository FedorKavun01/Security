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
}