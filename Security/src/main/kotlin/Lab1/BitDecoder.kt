package Lab1

class BitDecoder {
    fun getBytesArray(bits: String): String {
        var bitsArray = ArrayList<String>()
        for (i in bits.indices step 8) {
            var byte = ""
            if (bits.length > i + 7) {
                byte = bits.substring(i, i + 8)
            } else {
                byte = bits.substring(i, bits.length - 1)
            }
            bitsArray.add(byte)
        }


        var bytesList = ArrayList<Byte>()

        for (b in bitsArray) {
            val byte = java.lang.Byte.parseByte(b, 2)
            bytesList.add(byte.toByte())
        }
        val result = String(bytesList.toByteArray())

        return result
    }
}