package Lab1

import java.math.BigInteger

class HexDecoder {
    fun decode(hex: String): String {
        var bytesList = ArrayList<Byte>()

        for (i in hex.indices step 2) {
            val hexByte = hex.substring(i, i + 2)
            val integer = Integer.parseInt(hexByte, 16)
            val bigInteger = BigInteger.valueOf(integer.toLong())
            bytesList.add(bigInteger.toByte())
        }

        return String(bytesList.toByteArray())
    }
}