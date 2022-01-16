package Lab1

class VigenerDecoder {

    fun decodeXorBruteforce(input: String, keyLength: Int): Result {
        var caesarDecoderXOR = CaesarDecoderXOR()
        var key: String = ""
        val blocksIn: Array<String> = divideToBlocks(input, keyLength)
        val blockOut: Array<String> = Array(blocksIn.size) { "" }

        for (i in blocksIn.indices) {
            val result: Result = caesarDecoderXOR.decodeXorBruteforce(blocksIn[i])
            blockOut[i] = result.value
            key += result.key
//            print(result)
        }

        return Result(key, getTogetherBlocks(blockOut))
    }


    fun divideToBlocks(input: String, keyLength: Int): Array<String> {
        val blocks: Array<String> = Array(keyLength) { "" }
        for (i in input.indices step keyLength) {
            for (j in 0 until keyLength) {
                blocks[j] += input.get((i + j) % input.length).toString()
            }
        }
        return blocks
    }

    fun getTogetherBlocks(blocks: Array<String>): String {
        val str = StringBuilder()
        for (i in blocks[0].indices) {
            for (j in blocks.indices) {
                str.append(blocks[j].get(i % blocks[j].length))
            }
        }
        return str.toString()
    }
}

class Result(val key: String, val value: String)
{
    override fun toString(): String {
        return "$key: $value";
    }
}