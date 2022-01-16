package Lab1

class FrequancyAnalizer {
    public val symbols = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")

    public fun analizeFrequancy(string: String) {
        for(symbol in symbols) {
            println("$symbol: ${string.count { value: Char -> value == symbol.single() }.toDouble() / string.length * 100}")
        }
    }

    fun getNormalTextPercent(str: String): Float {
        var n = 0f
        for (i in str.indices) {
            val ch: Char = str[i]
            if (ch in 'A'..'Z' || ch in 'a'..'z' || ch == '.' || ch == ',' || ch == ' ' || ch == '/' || ch == ':' || ch == '-') {
                n++
            }
        }
        return n / str.length * 100.0f
    }
}