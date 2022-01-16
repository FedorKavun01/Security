class FrequancyAnalizer {
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