import java.math.BigInteger
import java.util.*

class HexDecoder {
    fun decode(hex: String): String {
        var bytesList = ArrayList<Char>()

        for (i in hex.indices step 2) {
            val hexByte = hex.substring(i, i + 2)
            val integer = Integer.parseInt(hexByte, 16)
//            val bigInteger = BigInteger.valueOf(integer)
            bytesList.add(integer.toChar())
        }

        var bytesArray = bytesList.toCharArray()
        var string = String(bytesArray)
        return string
    }
}