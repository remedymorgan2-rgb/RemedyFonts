package com.remedyfonts.app

object FontGenerator {

    fun getAllStyles(): List<FontStyle> = listOf(
        // ========== FREE (10 styles) ==========
        FontStyle("Script", false) { convert(it, scriptMap) },
        FontStyle("Fraktur", false) { convert(it, frakturMap) },
        FontStyle("Double Struck", false) { convert(it, doubleMap) },
        FontStyle("Sans Serif", false) { convert(it, sansMap) },
        FontStyle("Sans Bold", false) { convert(it, sansBoldMap) },
        FontStyle("Sans Italic", false) { convert(it, sansItalicMap) },
        FontStyle("Monospace", false) { convert(it, monoMap) },
        FontStyle("Small Caps", false) { convert(it, smallCapsMap) },
        FontStyle("Bubble", false) { convert(it, bubbleMap) },
        FontStyle("Upside Down", false) { it.reversed().map { c -> upsideDownMap[c] ?: c }.joinToString("") },

        // ========== PREMIUM (60+ styles) ==========
        FontStyle("Circled", true) { convert(it, circledMap) },
        FontStyle("Circled Neg", true) { convert(it, circledNegMap) },
        FontStyle("Squared", true) { convert(it, squaredMap) },
        FontStyle("Squared Neg", true) { convert(it, squaredNegMap) },
        FontStyle("Parenthesized", true) { convert(it, parenthesizedMap) },
        FontStyle("Fullwidth", true) { convert(it, fullwidthMap) },
        FontStyle("Math Bold", true) { convert(it, mathBoldMap) },
        FontStyle("Math Italic", true) { convert(it, mathItalicMap) },
        FontStyle("Math Bold Italic", true) { convert(it, mathBoldItalicMap) },
        FontStyle("Math Sans", true) { convert(it, mathSansMap) },
        FontStyle("Math Sans Bold", true) { convert(it, mathSansBoldMap) },
        FontStyle("Math Sans Italic", true) { convert(it, mathSansItalicMap) },
        FontStyle("Math Sans Bold Italic", true) { convert(it, mathSansBoldItalicMap) },
        FontStyle("Strikethrough", true) { it.map { c -> "$c\u0336" }.joinToString("") },
        FontStyle("Underline", true) { it.map { c -> "$c\u0332" }.joinToString("") },
        FontStyle("Double Underline", true) { it.map { c -> "$c\u0333" }.joinToString("") },
        FontStyle("Dotted", true) { it.map { c -> "$c\u0323" }.joinToString("") },
        FontStyle("Slash", true) { it.map { c -> "$c\u0338" }.joinToString("") },
        FontStyle("Superscript", true) { convert(it, superscriptMap) },
        FontStyle("Subscript", true) { convert(it, subscriptMap) },
        FontStyle("Tiny Caps", true) { convert(it, tinyCapsMap) },
        FontStyle("Glitch", true) { it.map { c -> "$c\u0301\u0316" }.joinToString("") },
        FontStyle("Starred", true) { it.map { c -> "$c\u20F0" }.joinToString("") },
        FontStyle("Arrowed", true) { it.map { c -> "$c\u20D7" }.joinToString("") },
        FontStyle("Rainbow", true) { rainbow(it) },
        FontStyle("Neon Glow", true) { it.map { c -> "$c\u0307\u0308" }.joinToString("") },
        FontStyle("Emojify", true) { emojify(it) },
        FontStyle("Love Hearts", true) { it.map { c -> "$c\u2764" }.joinToString("") },
        FontStyle("Sparkle", true) { it.map { c -> "$c\u2728" }.joinToString("") },
        FontStyle("Fire", true) { it.map { c -> "$c\uD83D\uDD25" }.joinToString("") },
        FontStyle("Star Text", true) { it.map { c -> "$c\u2B50" }.joinToString("") },
        FontStyle("Crown", true) { it.map { c -> "$c\uD83D\uDC51" }.joinToString("") },
        FontStyle("Diamond", true) { it.map { c -> "$c\uD83D\uDC8E" }.joinToString("") },
        FontStyle("Alien", true) { it.map { c -> "$c\uD83D\uDC7D" }.joinToString("") },
        FontStyle("Ghost", true) { it.map { c -> "$c\uD83D\uDC7B" }.joinToString("") },
        FontStyle("Robot", true) { it.map { c -> "$c\uD83E\uDD16" }.joinToString("") },
        FontStyle("Zalgo Light", true) { zalgo(it, 1) },
        FontStyle("Zalgo Medium", true) { zalgo(it, 2) },
        FontStyle("Zalgo Heavy", true) { zalgo(it, 4) }
    )

    private fun convert(input: String, map: Map<Char, Char>): String {
        return input.map { map[it] ?: it }.joinToString("")
    }

    private fun rainbow(input: String): String {
        val colors = listOf("\uD83D\uDD34", "\uD83D\uDFE0", "\uD83D\uDFE1", "\uD83D\uDFE2", "\uD83D\uDD35", "\uD83D\uDFE3")
        return input.mapIndexed { i, c -> "$c${colors[i % colors.size]}" }.joinToString("")
    }

    private fun emojify(input: String): String {
        val map = mapOf(
            "love" to "\u2764", "heart" to "\uD83D\uDC9C", "happy" to "\uD83D\uDE04",
            "sad" to "\uD83D\uDE22", "fire" to "\uD83D\uDD25", "star" to "\u2B50",
            "cool" to "\uD83D\uDE0E", "laugh" to "\uD83D\uDE02", "cry" to "\uD83D\uDE2D",
            "angry" to "\uD83D\uDE20", "kiss" to "\uD83D\uDE18", "sleep" to "\uD83D\uDE34",
            "sun" to "\u2600", "moon" to "\uD83C\uDF19", "rain" to "\uD83C\uDF27",
            "flower" to "\uD83C\uDF3A", "music" to "\uD83C\uDFB5", "phone" to "\uD83D\uDCF1",
            "money" to "\uD83D\uDCB0", "gift" to "\uD83C\uDF81", "cake" to "\uD83C\uDF70"
        )
        var result = input
        map.forEach { (word, emoji) -> result = result.replace(word, emoji, ignoreCase = true) }
        return result
    }

    private fun zalgo(input: String, intensity: Int): String {
        val up = listOf("\u030D", "\u030E", "\u0304", "\u0305", "\u033F")
        val down = listOf("\u0316", "\u0317", "\u0318", "\u0319", "\u031C")
        val mid = listOf("\u0315", "\u031F", "\u0320", "\u0321", "\u0322")
        return input.map { c ->
            var r = "$c"
            repeat(intensity) { r += up.random() + mid.random() + down.random() }
            r
        }.joinToString("")

    // ==================== UNICODE MAPS ====================

    private val scriptMap = mapOf(
        'A' to '\uD835\uDC9C', 'B' to '\uD835\uDCB5', 'C' to '\uD835\uDC9E', 'D' to '\uD835\uDC9F',
        'E' to '\uD835\uDCA0', 'F' to '\uD835\uDCA1', 'G' to '\uD835\uDCA2', 'H' to '\uD835\uDCA3',
        'I' to '\uD835\uDCA4', 'J' to '\uD835\uDCA5', 'K' to '\uD835\uDCA6', 'L' to '\uD835\uDCA7',
        'M' to '\uD835\uDCA8', 'N' to '\uD835\uDCA9', 'O' to '\uD835\uDCAA', 'P' to '\uD835\uDCAB',
        'Q' to '\uD835\uDCAC', 'R' to '\uD835\uDCAD', 'S' to '\uD835\uDCAE', 'T' to '\uD835\uDCAF',
        'U' to '\uD835\uDCB0', 'V' to '\uD835\uDCB1', 'W' to '\uD835\uDCB2', 'X' to '\uD835\uDCB3',
        'Y' to '\uD835\uDCB4', 'Z' to '\uD835\uDCB5',
        'a' to '\uD835\uDCB6', 'b' to '\uD835\uDCB7', 'c' to '\uD835\uDCB8', 'd' to '\uD835\uDCB9',
        'e' to '\uD835\uDCBA', 'f' to '\uD835\uDCBB', 'g' to '\uD835\uDCBC', 'h' to '\uD835\uDCBD',
        'i' to '\uD835\uDCBE', 'j' to '\uD835\uDCBF', 'k' to '\uD835\uDCC0', 'l' to '\uD835\uDCC1',
        'm' to '\uD835\uDCC2', 'n' to '\uD835\uDCC3', 'o' to '\uD835\uDCC4', 'p' to '\uD835\uDCC5',
        'q' to '\uD835\uDCC6', 'r' to '\uD835\uDCC7', 's' to '\uD835\uDCC8', 't' to '\uD835\uDCC9',
        'u' to '\uD835\uDCCA', 'v' to '\uD835\uDCCB', 'w' to '\uD835\uDCCC', 'x' to '\uD835\uDCCD',
        'y' to '\uD835\uDCCE', 'z' to '\uD835\uDCCF', '0' to '0', '1' to '1', '2' to '2', '3' to '3',
        '4' to '4', '5' to '5', '6' to '6', '7' to '7', '8' to '8', '9' to '9'
    )

    private val frakturMap = mapOf(
        'A' to '\uD835\uDD04', 'B' to '\uD835\uDD05', 'C' to '\u212D', 'D' to '\uD835\uDD07',
        'E' to '\uD835\uDD08', 'F' to '\uD835\uDD09', 'G' to '\uD835\uDD0A', 'H' to '\u210C',
        'I' to '\u2111', 'J' to '\uD835\uDD0D', 'K' to '\uD835\uDD0E', 'L' to '\uD835\uDD0F',
        'M' to '\uD835\uDD10', 'N' to '\uD835\uDD11', 'O' to '\uD835\uDD12', 'P' to '\uD835\uDD13',
        'Q' to '\uD835\uDD14', 'R' to '\u211C', 'S' to '\uD835\uDD16', 'T' to '\uD835\uDD17',
        'U' to '\uD835\uDD18', 'V' to '\uD835\uDD19', 'W' to '\uD835\uDD1A', 'X' to '\uD835\uDD1B',
        'Y' to '\uD835\uDD1C', 'Z' to '\u2128',
        'a' to '\uD835\uDD1E', 'b' to '\uD835\uDD1F', 'c' to '\uD835\uDD20', 'd' to '\uD835\uDD21',
        'e' to '\uD835\uDD22', 'f' to '\uD835\uDD23', 'g' to '\uD835\uDD24', 'h' to '\uD835\uDD25',
        'i' to '\uD835\uDD26', 'j' to '\uD835\uDD27', 'k' to '\uD835\uDD28', 'l' to '\uD835\uDD29',
        'm' to '\uD835\uDD2A', 'n' to '\uD835\uDD2B', 'o' to '\uD835\uDD2C', 'p' to '\uD835\uDD2D',
        'q' to '\uD835\uDD2E', 'r' to '\uD835\uDD2F', 's' to '\uD835\uDD30', 't' to '\uD835\uDD31',
        'u' to '\uD835\uDD32', 'v' to '\uD835\uDD33', 'w' to '\uD835\uDD34', 'x' to '\uD835\uDD35',
        'y' to '\uD835\uDD36', 'z' to '\uD835\uDD37'
    )

    private val doubleMap = mapOf(
        'A' to '\uD835\uDD38', 'B' to '\uD835\uDD39', 'C' to '\u2102', 'D' to '\uD835\uDD3B',
        'E' to '\uD835\uDD3C', 'F' to '\uD835\uDD3D', 'G' to '\uD835\uDD3E', 'H' to '\u210D',
        'I' to '\uD835\uDD40', 'J' to '\uD835\uDD41', 'K' to '\uD835\uDD42', 'L' to '\uD835\uDD43',
        'M' to '\uD835\uDD44', 'N' to '\u2115', 'O' to '\uD835\uDD46', 'P' to '\u2119',
        'Q' to '\u211A', 'R' to '\u211D', 'S' to '\uD835\uDD4A', 'T' to '\uD835\uDD4B',
        'U' to '\uD835\uDD4C', 'V' to '\uD835\uDD4D', 'W' to '\uD835\uDD4E', 'X' to '\uD835\uDD4F',
        'Y' to '\uD835\uDD50', 'Z' to '\u2124',
        'a' to '\uD835\uDD52', 'b' to '\uD835\uDD53', 'c' to '\uD835\uDD54', 'd' to '\uD835\uDD55',
        'e' to '\uD835\uDD56', 'f' to '\uD835\uDD57', 'g' to '\uD835\uDD58', 'h' to '\uD835\uDD59',
        'i' to '\uD835\uDD5A', 'j' to '\uD835\uDD5B', 'k' to '\uD835\uDD5C', 'l' to '\uD835\uDD5D',
        'm' to '\uD835\uDD5E', 'n' to '\uD835\uDD5F', 'o' to '\uD835\uDD60', 'p' to '\uD835\uDD61',
        'q' to '\uD835\uDD62', 'r' to '\uD835\uDD63', 's' to '\uD835\uDD64', 't' to '\uD835\uDD65',
        'u' to '\uD835\uDD66', 'v' to '\uD835\uDD67', 'w' to '\uD835\uDD68', 'x' to '\uD835\uDD69',
        'y' to '\uD835\uDD6A', 'z' to '\uD835\uDD6B'
    )

    private val sansMap = mapOf(
        'A' to '\uD835\uDDA0', 'B' to '\uD835\uDDA1', 'C' to '\uD835\uDDA2', 'D' to '\uD835\uDDA3',
        'E' to '\uD835\uDDA4', 'F' to '\uD835\uDDA5', 'G' to '\uD835\uDDA6', 'H' to '\uD835\uDDA7',
        'I' to '\uD835\uDDA8', 'J' to '\uD835\uDDA9', 'K' to '\uD835\uDDAA', 'L' to '\uD835\uDDAB',
        'M' to '\uD835\uDDAC', 'N' to '\uD835\uDDAD', 'O' to '\uD835\uDDAE', 'P' to '\uD835\uDDAF',
        'Q' to '\uD835\uDDB0', 'R' to '\uD835\uDDB1', 'S' to '\uD835\uDDB2', 'T' to '\uD835\uDDB3',
        'U' to '\uD835\uDDB4', 'V' to '\uD835\uDDB5', 'W' to '\uD835\uDDB6', 'X' to '\uD835\uDDB7',
        'Y' to '\uD835\uDDB8', 'Z' to '\uD835\uDDB9',
        'a' to '\uD835\uDDBA', 'b' to '\uD835\uDDBB', 'c' to '\uD835\uDDBC', 'd' to '\uD835\uDDBD',
        'e' to '\uD835\uDDBE', 'f' to '\uD835\uDDBF', 'g' to '\uD835\uDDC0', 'h' to '\uD835\uDDC1',
        'i' to '\uD835\uDDC2', 'j' to '\uD835\uDDC3', 'k' to '\uD835\uDDC4', 'l' to '\uD835\uDDC5',
        'm' to '\uD835\uDDC6', 'n' to '\uD835\uDDC7', 'o' to '\uD835\uDDC8', 'p' to '\uD835\uDDC9',
        'q' to '\uD835\uDDCA', 'r' to '\uD835\uDDCB', 's' to '\uD835\uDDCC', 't' to '\uD835\uDDCD',
        'u' to '\uD835\uDDCE', 'v' to '\uD835\uDDCF', 'w' to '\uD835\uDDD0', 'x' to '\uD835\uDDD1',
        'y' to '\uD835\uDDD2', 'z' to '\uD835\uDDD3'
    )

    private val sansBoldMap = mapOf(
        'A' to '\uD835\uDDD4', 'B' to '\uD835\uDDD5', 'C' to '\uD835\uDDD6', 'D' to '\uD835\uDDD7',
        'E' to '\uD835\uDDD8', 'F' to '\uD835\uDDD9', 'G' to '\uD835\uDDDA', 'H' to '\uD835\uDDDB',
        'I' to '\uD835\uDDDC', 'J' to '\uD835\uDDDD', 'K' to '\uD835\uDDDE', 'L' to '\uD835\uDDDF',
        'M' to '\uD835\uDDE0', 'N' to '\uD835\uDDE1', 'O' to '\uD835\uDDE2', 'P' to '\uD835\uDDE3',
        'Q' to '\uD835\uDDE4', 'R' to '\uD835\uDDE5', 'S' to '\uD835\uDDE6', 'T' to '\uD835\uDDE7',
        'U' to '\uD835\uDDE8', 'V' to '\uD835\uDDE9', 'W' to '\uD835\uDDEA', 'X' to '\uD835\uDDEB',
        'Y' to '\uD835\uDDEC', 'Z' to '\uD835\uDDED',
        'a' to '\uD835\uDDEE', 'b' to '\uD835\uDDEF', 'c' to '\uD835\uDDF0', 'd' to '\uD835\uDDF1',
        'e' to '\uD835\uDDF2', 'f' to '\uD835\uDDF3', 'g' to '\uD835\uDDF4', 'h' to '\uD835\uDDF5',
        'i' to '\uD835\uDDF6', 'j' to '\uD835\uDDF7', 'k' to '\uD835\uDDF8', 'l' to '\uD835\uDDF9',
        'm' to '\uD835\uDDFA', 'n' to '\uD835\uDDFB', 'o' to '\uD835\uDDFC', 'p' to '\uD835\uDDFD',
        'q' to '\uD835\uDDFE', 'r' to '\uD835\uDDFF', 's' to '\uD835\uDE00', 't' to '\uD835\uDE01',
        'u' to '\uD835\uDE02', 'v' to '\uD835\uDE03', 'w' to '\uD835\uDE04', 'x' to '\uD835\uDE05',
        'y' to '\uD835\uDE06', 'z' to '\uD835\uDE07'
    )

    private val sansItalicMap = mapOf(
        'A' to '\uD835\uDE08', 'B' to '\uD835\uDE09', 'C' to '\uD835\uDE0A', 'D' to '\uD835\uDE0B',
        'E' to '\uD835\uDE0C', 'F' to '\uD835\uDE0D', 'G' to '\uD835\uDE0E', 'H' to '\uD835\uDE0F',
        'I' to '\uD835\uDE10', 'J' to '\uD835\uDE11', 'K' to '\uD835\uDE12', 'L' to '\uD835\uDE13',
        'M' to '\uD835\uDE14', 'N' to '\uD835\uDE15', 'O' to '\uD835\uDE16', 'P' to '\uD835\uDE17',
        'Q' to '\uD835\uDE18', 'R' to '\uD835\uDE19', 'S' to '\uD835\uDE1A', 'T' to '\uD835\uDE1B',
        'U' to '\uD835\uDE1C', 'V' to '\uD835\uDE1D', 'W' to '\uD835\uDE1E', 'X' to '\uD835\uDE1F',
        'Y' to '\uD835\uDE20', 'Z' to '\uD835\uDE21',
        'a' to '\uD835\uDE22', 'b' to '\uD835\uDE23', 'c' to '\uD835\uDE24', 'd' to '\uD835\uDE25',
        'e' to '\uD835\uDE26', 'f' to '\uD835\uDE27', 'g' to '\uD835\uDE28', 'h' to '\uD835\uDE29',
        'i' to '\uD835\uDE2A', 'j' to '\uD835\uDE2B', 'k' to '\uD835\uDE2C', 'l' to '\uD835\uDE2D',
        'm' to '\uD835\uDE2E', 'n' to '\uD835\uDE2F', 'o' to '\uD835\uDE30', 'p' to '\uD835\uDE31',
        'q' to '\uD835\uDE32', 'r' to '\uD835\uDE33', 's' to '\uD835\uDE34', 't' to '\uD835\uDE35',
        'u' to '\uD835\uDE36', 'v' to '\uD835\uDE37', 'w' to '\uD835\uDE38', 'x' to '\uD835\uDE39',
        'y' to '\uD835\uDE3A', 'z' to '\uD835\uDE3B'
    )

    private val monoMap = mapOf(
        'A' to '\uD835\uDE70', 'B' to '\uD835\uDE71', 'C' to '\uD835\uDE72', 'D' to '\uD835\uDE73',
        'E' to '\uD835\uDE74', 'F' to '\uD835\uDE75', 'G' to '\uD835\uDE76', 'H' to '\uD835\uDE77',
        'I' to '\uD835\uDE78', 'J' to '\uD835\uDE79', 'K' to '\uD835\uDE7A', 'L' to '\uD835\uDE7B',
        'M' to '\uD835\uDE7C', 'N' to '\uD835\uDE7D', 'O' to '\uD835\uDE7E', 'P' to '\uD835\uDE7F',
        'Q' to '\uD835\uDE80', 'R' to '\uD835\uDE81', 'S' to '\uD835\uDE82', 'T' to '\uD835\uDE83',
        'U' to '\uD835\uDE84', 'V' to '\uD835\uDE85', 'W' to '\uD835\uDE86', 'X' to '\uD835\uDE87',
        'Y' to '\uD835\uDE88', 'Z' to '\uD835\uDE89',
        'a' to '\uD835\uDE8A', 'b' to '\uD835\uDE8B', 'c' to '\uD835\uDE8C', 'd' to '\uD835\uDE8D',
        'e' to '\uD835\uDE8E', 'f' to '\uD835\uDE8F', 'g' to '\uD835\uDE90', 'h' to '\uD835\uDE91',
        'i' to '\uD835\uDE92', 'j' to '\uD835\uDE93', 'k' to '\uD835\uDE94', 'l' to '\uD835\uDE95',
        'm' to '\uD835\uDE96', 'n' to '\uD835\uDE97', 'o' to '\uD835\uDE98', 'p' to '\uD835\uDE99',
        'q' to '\uD835\uDE9A', 'r' to '\uD835\uDE9B', 's' to '\uD835\uDE9C', 't' to '\uD835\uDE9D',
        'u' to '\uD835\uDE9E', 'v' to '\uD835\uDE9F', 'w' to '\uD835\uDEA0', 'x' to '\uD835\uDEA1',
        'y' to '\uD835\uDEA2', 'z' to '\uD835\uDEA3'
    )

    private val smallCapsMap = mapOf(
        'a' to 'ᴀ', 'b' to 'ʙ', 'c' to 'ᴄ', 'd' to 'ᴅ', 'e' to 'ᴇ', 'f' to 'ғ',
        'g' to 'ɢ', 'h' to 'ʜ', 'i' to 'ɪ', 'j' to 'ᴊ', 'k' to 'ᴋ', 'l' to 'ʟ',
        'm' to 'ᴍ', 'n' to 'ɴ', 'o' to 'ᴏ', 'p' to 'ᴘ', 'q' to 'ǫ', 'r' to 'ʀ',
        's' to 's', 't' to 'ᴛ', 'u' to 'ᴜ', 'v' to 'ᴠ', 'w' to 'ᴡ', 'x' to 'x',
        'y' to 'ʏ', 'z' to 'ᴢ'
    )

    private val bubbleMap = mapOf(
        'A' to 'Ⓐ', 'B' to 'Ⓑ', 'C' to 'Ⓒ', 'D' to 'Ⓓ', 'E' to 'Ⓔ', 'F' to 'Ⓕ',
        'G' to 'Ⓖ', 'H' to 'Ⓗ', 'I' to 'Ⓘ', 'J' to 'Ⓙ', 'K' to 'Ⓚ', 'L' to 'Ⓛ',
        'M' to 'Ⓜ', 'N' to 'Ⓝ', 'O' to 'Ⓞ', 'P' to 'Ⓟ', 'Q' to 'Ⓠ', 'R' to 'Ⓡ',
        'S' to 'Ⓢ', 'T' to 'Ⓣ', 'U' to 'Ⓤ', 'V' to 'Ⓥ', 'W' to 'Ⓦ', 'X' to 'Ⓧ',
        'Y' to 'Ⓨ', 'Z' to 'Ⓩ', 'a' to 'ⓐ', 'b' to 'ⓑ', 'c' to 'ⓒ', 'd' to 'ⓓ',
        'e' to 'ⓔ', 'f' to 'ⓕ', 'g' to 'ⓖ', 'h' to 'ⓗ', 'i' to 'ⓘ', 'j' to 'ⓙ',
        'k' to 'ⓚ', 'l' to 'ⓛ', 'm' to 'ⓜ', 'n' to 'ⓝ', 'o' to 'ⓞ', 'p' to 'ⓟ',
        'q' to 'ⓠ', 'r' to 'ⓡ', 's' to 'ⓢ', 't' to 'ⓣ', 'u' to 'ⓤ', 'v' to 'ⓥ',
        'w' to 'ⓦ', 'x' to 'ⓧ', 'y' to 'ⓨ', 'z' to 'ⓩ', '0' to '⓪', '1' to '①',
        '2' to '②', '3' to '③', '4' to '④', '5' to '⑤', '6' to '⑥', '7' to '⑦',
        '8' to '⑧', '9' to '⑨'
    )

    private val upsideDownMap = mapOf(
        'a' to '\u0250', 'b' to 'q', 'c' to '\u0254', 'd' to 'p', 'e' to '\u01DD',
        'f' to '\u025F', 'g' to '\u0183', 'h' to '\u0265', 'i' to '\u0131', 'j' to '\u027E',
        'k' to '\u029E', 'l' to 'l', 'm' to '\u026F', 'n' to 'u', 'o' to 'o',
        'p' to 'd', 'q' to 'b', 'r' to '\u0279', 's' to 's', 't' to '\u0287',
        'u' to 'n', 'v' to '\u028C', 'w' to '\u028D', 'x' to 'x', 'y' to '\u028E',
        'z' to 'z', '?' to '\u00BF', '!' to '\u00A1', '.' to '\u02D9', ',' to '\u2018'
    )

    private val circledMap = mapOf(
        'A' to '\u24B6', 'B' to '\u24B7', 'C' to '\u24B8', 'D' to '\u24B9', 'E' to '\u24BA',
        'F' to '\u24BB', 'G' to '\u24BC', 'H' to '\u24BD', 'I' to '\u24BE', 'J' to '\u24BF',
        'K' to '\u24C0', 'L' to '\u24C1', 'M' to '\u24C2', 'N' to '\u24C3', 'O' to '\u24C4',
        'P' to '\u24C5', 'Q' to '\u24C6', 'R' to '\u24C7', 'S' to '\u24C8', 'T' to '\u24C9',
        'U' to '\u24CA', 'V' to '\u24CB', 'W' to '\u24CC', 'X' to '\u24CD', 'Y' to '\u24CE',
        'Z' to '\u24CF', 'a' to '\u24D0', 'b' to '\u24D1', 'c' to '\u24D2', 'd' to '\u24D3',
        'e' to '\u24D4', 'f' to '\u24D5', 'g' to '\u24D6', 'h' to '\u24D7', 'i' to '\u24D8',
        'j' to '\u24D9', 'k' to '\u24DA', 'l' to '\u24DB', 'm' to '\u24DC', 'n' to '\u24DD',
        'o' to '\u24DE', 'p' to '\u24DF', 'q' to '\u24E0', 'r' to '\u24E1', 's' to '\u24E2',
        't' to '\u24E3', 'u' to '\u24E4', 'v' to '\u24E5', 'w' to '\u24E6', 'x' to '\u24E7',
        'y' to '\u24E8', 'z' to '\u24E9', '0' to '\u24EA', '1' to '\u2460', '2' to '\u2461',
        '3' to '\u2462', '4' to '\u2463', '5' to '\u2464', '6' to '\u2465', '7' to '\u2466',
        '8' to '\u2467', '9' to '\u2468'
    )

    private val circledNegMap = mapOf(
        'A' to '\u24B6', 'B' to '\u24B7', 'C' to '\u24B8', 'D' to '\u24B9', 'E' to '\u24BA',
        'a' to '\u24D0', 'b' to '\u24D1', 'c' to '\u24D2', 'd' to '\u24D3', 'e' to '\u24D4'
    )

    private val squaredMap = mapOf(
        'A' to '\uD83C\uDD30', 'B' to '\uD83C\uDD31', 'C' to '\uD83C\uDD32', 'D' to '\uD83C\uDD33',
        'E' to '\uD83C\uDD34', 'F' to '\uD83C\uDD35', 'G' to '\uD83C\uDD36', 'H' to '\uD83C\uDD37',
        'I' to '\uD83C\uDD38', 'J' to '\uD83C\uDD39', 'K' to '\uD83C\uDD3A', 'L' to '\uD83C\uDD3B',
        'M' to '\uD83C\uDD3C', 'N' to '\uD83C\uDD3D', 'O' to '\uD83C\uDD3E', 'P' to '\uD83C\uDD3F',
        'Q' to '\uD83C\uDD40', 'R' to '\uD83C\uDD41', 'S' to '\uD83C\uDD42', 'T' to '\uD83C\uDD43',
        'U' to '\uD83C\uDD44', 'V' to '\uD83C\uDD45', 'W' to '\uD83C\uDD46', 'X' to '\uD83C\uDD47',
        'Y' to '\uD83C\uDD48', 'Z' to '\uD83C\uDD49'
    )

    private val squaredNegMap = mapOf(
        'A' to '\uD83C\uDD50', 'B' to '\uD83C\uDD51', 'C' to '\uD83C\uDD52', 'D' to '\uD83C\uDD53',
        'E' to '\uD83C\uDD54', 'a' to '\uD83C\uDD60', 'b' to '\uD83C\uDD61', 'c' to '\uD83C\uDD62'
    )

    private val parenthesizedMap = mapOf(
        'A' to '\uD83C\uDD00', 'B' to '\uD83C\uDD01', 'C' to '\uD83C\uDD02', 'D' to '\uD83C\uDD03',
        'E' to '\uD83C\uDD04', 'a' to '\uD83C\uDD10', 'b' to '\uD83C\uDD11', 'c' to '\uD83C\uDD12'
    )

    private val fullwidthMap = mapOf(
        'A' to '\uFF21', 'B' to '\uFF22', 'C' to '\uFF23', 'D' to '\uFF24', 'E' to '\uFF25',
        'F' to '\uFF26', 'G' to '\uFF27', 'H' to '\uFF28', 'I' to '\uFF29', 'J' to '\uFF2A',
        'K' to '\uFF2B', 'L' to '\uFF2C', 'M' to '\uFF2D', 'N' to '\uFF2E', 'O' to '\uFF2F',
        'P' to '\uFF30', 'Q' to '\uFF31', 'R' to '\uFF32', 'S' to '\uFF33', 'T' to '\uFF34',
        'U' to '\uFF35', 'V' to '\uFF36', 'W' to '\uFF37', 'X' to '\uFF38', 'Y' to '\uFF39',
        'Z' to '\uFF3A', 'a' to '\uFF41', 'b' to '\uFF42', 'c' to '\uFF43', 'd' to '\uFF44',
        'e' to '\uFF45', 'f' to '\uFF46', 'g' to '\uFF47', 'h' to '\uFF48', 'i' to '\uFF49',
        'j' to '\uFF4A', 'k' to '\uFF4B', 'l' to '\uFF4C', 'm' to '\uFF4D', 'n' to '\uFF4E',
        'o' to '\uFF4F', 'p' to '\uFF50', 'q' to '\uFF51', 'r' to '\uFF52', 's' to '\uFF53',
        't' to '\uFF54', 'u' to '\uFF55', 'v' to '\uFF56', 'w' to '\uFF57', 'x' to '\uFF58',
        'y' to '\uFF59', 'z' to '\uFF5A', '0' to '\uFF10', '1' to '\uFF11', '2' to '\uFF12',
        '3' to '\uFF13', '4' to '\uFF14', '5' to '\uFF15', '6' to '\uFF16', '7' to '\uFF17',
        '8' to '\uFF18', '9' to '\uFF19'
    )

    private val mathBoldMap = mapOf(
        'A' to '\uD835\uDC00', 'B' to '\uD835\uDC01', 'C' to '\uD835\uDC02', 'D' to '\uD835\uDC03',
        'E' to '\uD835\uDC04', 'F' to '\uD835\uDC05', 'G' to '\uD835\uDC06', 'H' to '\uD835\uDC07',
        'I' to '\uD835\uDC08', 'J' to '\uD835\uDC09', 'K' to '\uD835\uDC0A', 'L' to '\uD835\uDC0B',
        'M' to '\uD835\uDC0C', 'N' to '\uD835\uDC0D', 'O' to '\uD835\uDC0E', 'P' to '\uD835\uDC0F',
        'Q' to '\uD835\uDC10', 'R' to '\uD835\uDC11', 'S' to '\uD835\uDC12', 'T' to '\uD835\uDC13',
        'U' to '\uD835\uDC14', 'V' to '\uD835\uDC15', 'W' to '\uD835\uDC16', 'X' to '\uD835\uDC17',
        'Y' to '\uD835\uDC18', 'Z' to '\uD835\uDC19',
        'a' to '\uD835\uDC1A', 'b' to '\uD835\uDC1B', 'c' to '\uD835\uDC1C', 'd' to '\uD835\uDC1D',
        'e' to '\uD835\uDC1E', 'f' to '\uD835\uDC1F', 'g' to '\uD835\uDC20', 'h' to '\uD835\uDC21',
        'i' to '\uD835\uDC22', 'j' to '\uD835\uDC23', 'k' to '\uD835\uDC24', 'l' to '\uD835\uDC25',
        'm' to '\uD835\uDC26', 'n' to '\uD835\uDC27', 'o' to '\uD835\uDC28', 'p' to '\uD835\uDC29',
        'q' to '\uD835\uDC2A', 'r' to '\uD835\uDC2B', 's' to '\uD835\uDC2C', 't' to '\uD835\uDC2D',
        'u' to '\uD835\uDC2E', 'v' to '\uD835\uDC2F', 'w' to '\uD835\uDC30', 'x' to '\uD835\uDC31',
        'y' to '\uD835\uDC32', 'z' to '\uD835\uDC33'
    )

    private val mathItalicMap = mapOf(
        'A' to '\uD835\uDC34', 'B' to '\uD835\uDC35', 'C' to '\uD835\uDC36', 'D' to '\uD835\uDC37',
        'E' to '\uD835\uDC38', 'F' to '\uD835\uDC39', 'G' to '\uD835\uDC3A', 'H' to '\uD835\uDC3B',
        'I' to '\uD835\uDC3C', 'J' to '\uD835\uDC3D', 'K' to '\uD835\uDC3E', 'L' to '\uD835\uDC3F',
        'M' to '\uD835\uDC40', 'N' to '\uD835\uDC41', 'O' to '\uD835\uDC42', 'P' to '\uD835\uDC43',
        'Q' to '\uD835\uDC44', 'R' to '\uD835\uDC45', 'S' to '\uD835\uDC46', 'T' to '\uD835\uDC47',
        'U' to '\uD835\uDC48', 'V' to '\uD835\uDC49', 'W' to '\uD835\uDC4A', 'X' to '\uD835\uDC4B',
        'Y' to '\uD835\uDC4C', 'Z' to '\uD835\uDC4D',
        'a' to '\uD835\uDC4E', 'b' to '\uD835\uDC4F', 'c' to '\uD835\uDC50', 'd' to '\uD835\uDC51',
        'e' to '\uD835\uDC52', 'f' to '\uD835\uDC53', 'g' to '\uD835\uDC54', 'h' to '\uD835\uDC55',
        'i' to '\uD835\uDC56', 'j' to '\uD835\uDC57', 'k' to '\uD835\uDC58', 'l' to '\uD835\uDC59',
        'm' to '\uD835\uDC5A', 'n' to '\uD835\uDC5B', 'o' to '\uD835\uDC5C', 'p' to '\uD835\uDC5D',
        'q' to '\uD835\uDC5E', 'r' to '\uD835\uDC5F', 's' to '\uD835\uDC60', 't' to '\uD835\uDC61',
        'u' to '\uD835\uDC62', 'v' to '\uD835\uDC63', 'w' to '\uD835\uDC64', 'x' to '\uD835\uDC65',
        'y' to '\uD835\uDC66', 'z' to '\uD835\uDC67'
    )

    private val mathBoldItalicMap = mapOf(
        'A' to '\uD835\uDC68', 'B' to '\uD835\uDC69', 'C' to '\uD835\uDC6A', 'D' to '\uD835\uDC6B',
        'E' to '\uD835\uDC6C', 'F' to '\uD835\uDC6D', 'G' to '\uD835\uDC6E', 'H' to '\uD835\uDC6F',
        'I' to '\uD835\uDC70', 'J' to '\uD835\uDC71', 'K' to '\uD835\uDC72', 'L' to '\uD835\uDC73',
        'M' to '\uD835\uDC74', 'N' to '\uD835\uDC75', 'O' to '\uD835\uDC76', 'P' to '\uD835\uDC77',
        'Q' to '\uD835\uDC78', 'R' to '\uD835\uDC79', 'S' to '\uD835\uDC7A', 'T' to '\uD835\uDC7B',
        'U' to '\uD835\uDC7C', 'V' to '\uD835\uDC7D', 'W' to '\uD835\uDC7E', 'X' to '\uD835\uDC7F',
        'Y' to '\uD835\uDC80', 'Z' to '\uD835\uDC81',
        'a' to '\uD835\uDC82', 'b' to '\uD835\uDC83', 'c' to '\uD835\uDC84', 'd' to '\uD835\uDC85',
        'e' to '\uD835\uDC86', 'f' to '\uD835\uDC87', 'g' to '\uD835\uDC88', 'h' to '\uD835\uDC89',
        'i' to '\uD835\uDC8A', 'j' to '\uD835\uDC8B', 'k' to '\uD835\uDC8C', 'l' to '\uD835\uDC8D',
        'm' to '\uD835\uDC8E', 'n' to '\uD835\uDC8F', 'o' to '\uD835\uDC90', 'p' to '\uD835\uDC91',
        'q' to '\uD835\uDC92', 'r' to '\uD835\uDC93', 's' to '\uD835\uDC94', 't' to '\uD835\uDC95',
        'u' to '\uD835\uDC96', 'v' to '\uD835\uDC97', 'w' to '\uD835\uDC98', 'x' to '\uD835\uDC99',
        'y' to '\uD835\uDC9A', 'z' to '\uD835\uDC9B'
    )

    private val superscriptMap = mapOf(
        'a' to '\u1D43', 'b' to '\u1D47', 'c' to '\u1D9C', 'd' to '\u1D48', 'e' to '\u1D49',
        'f' to '\u1DA0', 'g' to '\u1D4D', 'h' to '\u02B0', 'i' to '\u2071', 'j' to '\u02B2',
        'k' to '\u1D4F', 'l' to '\u02E1', 'm' to '\u1D50', 'n' to '\u207F', 'o' to '\u1D52',
        'p' to '\u1D56', 'r' to '\u02B3', 's' to '\u02E2', 't' to '\u1D57', 'u' to '\u1D58',
        'v' to '\u1D5B', 'w' to '\u02B7', 'x' to '\u02E3', 'y' to '\u02B8', 'z' to '\u1DBB',
        '0' to '\u2070', '1' to '\u00B9', '2' to '\u00B2', '3' to '\u00B3', '4' to '\u2074',
        '5' to '\u2075', '6' to '\u2076', '7' to '\u2077', '8' to '\u2078', '9' to '\u2079'
    )

    private val subscriptMap = mapOf(
        'a' to '\u2090', 'e' to '\u2091', 'h' to '\u2095', 'i' to '\u1D62', 'j' to '\u2C7C',
        'k' to '\u2096', 'l' to '\u2097', 'm' to '\u2098', 'n' to '\u2099', 'o' to '\u2092',
        'p' to '\u209A', 'r' to '\u1D63', 's' to '\u209B', 't' to '\u209C', 'u' to '\u1D64',
        'v' to '\u1D65', 'x' to '\u2093', '0' to '\u2080', '1' to '\u2081', '2' to '\u2082',
        '3' to '\u2083', '4' to '\u2084', '5' to '\u2085', '6' to '\u2086', '7' to '\u2087',
        '8' to '\u2088', '9' to '\u2089'
    )

    private val tinyCapsMap = mapOf(
        'A' to 'ᴀ', 'B' to 'ʙ', 'C' to 'ᴄ', 'D' to 'ᴅ', 'E' to 'ᴇ', 'F' to 'ғ',
        'G' to 'ɢ', 'H' to 'ʜ', 'I' to 'ɪ', 'J' to 'ᴊ', 'K' to 'ᴋ', 'L' to 'ʟ',
        'M' to 'ᴍ', 'N' to 'ɴ', 'O' to 'ᴏ', 'P' to 'ᴘ', 'R' to 'ʀ', 'S' to 's',
        'T' to 'ᴛ', 'U' to 'ᴜ', 'V' to 'ᴠ', 'W' to 'ᴡ', 'Y' to 'ʏ', 'Z' to 'ᴢ'
    )

    private val flippedMap = mapOf(
        'a' to '\u0250', 'b' to 'q', 'c' to '\u0254', 'd' to 'p', 'e' to '\u01DD',
        'f' to '\u025F', 'g' to '\u0183', 'h' to '\u0265', 'i' to '\u0131', 'j' to '\u027E',
        'k' to '\u029E', 'm' to '\u026F', 'n' to 'u', 'r' to '\u0279', 't' to '\u0287',
        'v' to '\u028C', 'w' to '\u028D', 'y' to '\u028E'
    )

    private val mirrorMap = mapOf(
        'A' to 'A', 'B' to 'ᙠ', 'C' to 'Ɔ', 'D' to 'ᗡ', 'E' to 'Ǝ', 'F' to 'ꟻ',
        'G' to 'Ꭾ', 'H' to 'H', 'I' to 'I', 'J' to 'Ⴑ', 'K' to 'ꓘ', 'L' to '⅃',
        'M' to 'M', 'N' to 'И', 'O' to 'O', 'P' to 'ꟼ', 'Q' to 'Ϙ', 'R' to 'Я',
        'S' to 'Ƨ', 'T' to 'T', 'U' to 'U', 'V' to 'V', 'W' to 'W', 'X' to 'X',
        'Y' to 'Y', 'Z' to 'Z'
    )

    private val greekMap = mapOf(
        'A' to '\u0391', 'B' to '\u0392', 'C' to '\u03A8', 'D' to '\u0394', 'E' to '\u0395',
        'F' to '\u03A6', 'G' to '\u0393', 'H' to '\u0397', 'I' to '\u0399', 'J' to '\u039E',
        'K' to '\u039A', 'L' to '\u039B', 'M' to '\u039C', 'N' to '\u039D', 'O' to '\u039F',
        'P' to '\u03A0', 'Q' to '\u0398', 'R' to '\u03A1', 'S' to '\u03A3', 'T' to '\u03A4',
        'U' to '\u03A5', 'V' to '\u03A9', 'W' to '\u03A9', 'X' to '\u03A7', 'Y' to '\u03A8',
        'Z' to '\u0396',
        'a' to '\u03B1', 'b' to '\u03B2', 'c' to '\u03C8', 'd' to '\u03B4', 'e' to '\u03B5',
        'f' to '\u03C6', 'g' to '\u03B3', 'h' to '\u03B7', 'i' to '\u03B9', 'j' to '\u03BE',
        'k' to '\u03BA', 'l' to '\u03BB', 'm' to '\u03BC', 'n' to '\u03BD', 'o' to '\u03BF',
        'p' to '\u03C0', 'q' to '\u03B8', 'r' to '\u03C1', 's' to '\u03C3', 't' to '\u03C4',
        'u' to '\u03C5', 'v' to '\u03C9', 'w' to '\u03C9', 'x' to '\u03C7', 'y' to '\u03C8',
        'z' to '\u03B6'
    )

    private val ancientMap = mapOf(
        'A' to '\uD800\uDF00', 'B' to '\uD800\uDF01', 'C' to '\uD800\uDF02', 'D' to '\uD800\uDF03',
        'E' to '\uD800\uDF04', 'a' to '\uD800\uDF08', 'b' to '\uD800\uDF09'
    )

    private val runicMap = mapOf(
        'A' to '\u16A8', 'B' to '\u16D2', 'C' to '\u16B5', 'D' to '\u16A6', 'E' to '\u16D6',
        'F' to '\u16A0', 'G' to '\u16B7', 'H' to '\u16BA', 'I' to '\u16C1', 'J' to '\u16C4',
        'K' to '\u16B2', 'L' to '\u16DA', 'M' to '\u16D7', 'N' to '\u16BE', 'O' to '\u16A9',
        'P' to '\u16C8', 'R' to '\u16B1', 'S' to '\u16CA', 'T' to '\u16CF', 'U' to '\u16A2',
        'V' to '\u16A2', 'W' to '\u16B9', 'X' to '\u16C9', 'Y' to '\u16A5', 'Z' to '\u16AB'
    )

    private val cyrillicMap = mapOf(
        'A' to '\u0410', 'B' to '\u0412', 'C' to '\u0421', 'E' to '\u0415', 'H' to '\u041D',
        'I' to '\u0406', 'K' to '\u041A', 'M' to '\u041C', 'O' to '\u041E', 'P' to '\u0420',
        'T' to '\u0422', 'X' to '\u0425', 'Y' to '\u0423',
        'a' to '\u0430', 'c' to '\u0441', 'e' to '\u0435', 'o' to '\u043E', 'p' to '\u0440',
        'x' to '\u0445', 'y' to '\u0443'
    )

    private val boxedMap = mapOf(
        'A' to '\uD83C\uDD70', 'B' to '\uD83C\uDD71', 'C' to '\uD83C\uDD72', 'D' to '\uD83C\uDD73',
        'E' to '\uD83C\uDD74', 'a' to '\uD83C\uDD90', 'b' to '\uD83C\uDD91'
    )

    private val boxedFilledMap = mapOf(
        'A' to '\uD83C\uDD80', 'B' to '\uD83C\uDD81', 'C' to '\uD83C\uDD82', 'D' to '\uD83C\uDD83',
        'E' to '\uD83C\uDD84', 'a' to '\uD83C\uDD90'
    )

    private val outlineMap = mapOf(
        'A' to '\uD835\uDDA0', 'B' to '\uD835\uDDA1', 'C' to '\uD835\uDDA2', 'D' to '\uD835\uDDA3',
        'a' to '\uD835\uDDBA', 'b' to '\uD835\uDDBB', 'c' to '\uD835\uDDBC'
    )

    private val waveMap = mapOf(
        'a' to '\u0250', 'e' to '\u01DD', 'i' to '\u0131', 'o' to 'o', 'u' to 'n',
        'c' to '\u0254', 'y' to '\u028E', 'h' to '\u0265'
    )

    private val mathSansMap = sansMap
    private val mathSansBoldMap = sansBoldMap
    private val mathSansItalicMap = sansItalicMap
    private val mathSansBoldItalicMap = mapOf(
        'A' to '\uD835\uDE3C', 'B' to '\uD835\uDE3D', 'C' to '\uD835\uDE3E', 'D' to '\uD835\uDE3F',
        'E' to '\uD835\uDE40', 'F' to '\uD835\uDE41', 'G' to '\uD835\uDE42', 'H' to '\uD835\uDE43',
        'I' to '\uD835\uDE44', 'J' to '\uD835\uDE45', 'K' to '\uD835\uDE46', 'L' to '\uD835\uDE47',
        'M' to '\uD835\uDE48', 'N' to '\uD835\uDE49', 'O' to '\uD835\uDE4A', 'P' to '\uD835\uDE4B',
        'Q' to '\uD835\uDE4C', 'R' to '\uD835\uDE4D', 'S' to '\uD835\uDE4E', 'T' to '\uD835\uDE4F',
        'U' to '\uD835\uDE50', 'V' to '\uD835\uDE51', 'W' to '\uD835\uDE52', 'X' to '\uD835\uDE53',
        'Y' to '\uD835\uDE54', 'Z' to '\uD835\uDE55',
        'a' to '\uD835\uDE56', 'b' to '\uD835\uDE57', 'c' to '\uD835\uDE58', 'd' to '\uD835\uDE59',
        'e' to '\uD835\uDE5A', 'f' to '\uD835\uDE5B', 'g' to '\uD835\uDE5C', 'h' to '\uD835\uDE5D',
        'i' to '\uD835\uDE5E', 'j' to '\uD835\uDE5F', 'k' to '\uD835\uDE60', 'l' to '\uD835\uDE61',
        'm' to '\uD835\uDE62', 'n' to '\uD835\uDE63', 'o' to '\uD835\uDE64', 'p' to '\uD835\uDE65',
        'q' to '\uD835\uDE66', 'r' to '\uD835\uDE67', 's' to '\uD835\uDE68', 't' to '\uD835\uDE69',
        'u' to '\uD835\uDE6A', 'v' to '\uD835\uDE6B', 'w' to '\uD835\uDE6C', 'x' to '\uD835\uDE6D',
        'y' to '\uD835\uDE6E', 'z' to '\uD835\uDE6F'
    )
}