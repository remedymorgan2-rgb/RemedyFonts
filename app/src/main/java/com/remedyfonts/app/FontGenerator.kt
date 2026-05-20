package com.remedyfonts.app

object FontGenerator {

    fun getAllStyles(): List<FontStyle> = listOf(
        // FREE styles — simple string transformations
        FontStyle("Bold", false) { it.replace("a","𝐚").replace("b","𝐛").replace("c","𝐜")
            .replace("A","𝐀").replace("B","𝐁").replace("C","𝐂") },
        FontStyle("Italic", false) { it.replace("a","𝑎").replace("b","𝑏").replace("c","𝑐")
            .replace("A","𝐴").replace("B","𝐵").replace("C","𝐶") },
        FontStyle("Script", false) { it.replace("a","𝓪").replace("b","𝓫").replace("c","𝓬")
            .replace("A","𝓐").replace("B","𝓑").replace("C","𝓒") },
        FontStyle("Monospace", false) { it.replace("a","𝚊").replace("b","𝚋").replace("c","𝚌")
            .replace("A","𝙰").replace("B","𝙱").replace("C","𝙲") },
        FontStyle("Sans-Serif", false) { it.replace("a","𝖺").replace("b","𝖻").replace("c","𝖼")
            .replace("A","𝖠").replace("B","𝖡").replace("C","𝖢") },
        FontStyle("Sans-Bold", false) { it.replace("a","𝗮").replace("b","𝗯").replace("c","𝗰")
            .replace("A","𝗔").replace("B","𝗕").replace("C","𝗖") },
        FontStyle("Double", false) { it.replace("a","𝕒").replace("b","𝕓").replace("c","𝕔")
            .replace("A","𝔸").replace("B","𝔹").replace("C","ℂ") },
        FontStyle("Small Caps", false) { it.map { c -> smallCapsMap[c] ?: c }.joinToString("") },
        // PREMIUM — require rewarded ad
        FontStyle("Upside Down", true) { it.reversed().map { c -> upsideDownMap[c] ?: c }.joinToString("") },
        FontStyle("Zalgo Light", true) { zalgo(it, 1) },
        FontStyle("Zalgo Heavy", true) { zalgo(it, 4) }
    )

    private fun zalgo(input: String, intensity: Int): String {
        val up = listOf("\u030D", "\u030E", "\u0304", "\u0305", "\u033F")
        val down = listOf("\u0316", "\u0317", "\u0318", "\u0319", "\u031C")
        val mid = listOf("\u0315", "\u031F", "\u0320", "\u0321", "\u0322")
        return input.map { c ->
            var r = "$c"
            repeat(intensity) { r += up.random() + mid.random() + down.random() }
            r
        }.joinToString("")
    }

    private val smallCapsMap = mapOf(
        'a' to 'ᴀ', 'b' to 'ʙ', 'c' to 'ᴄ'
    )
    private val upsideDownMap = mapOf(
        'a' to 'ɐ', 'b' to 'q', 'c' to 'ɔ', 'd' to 'p', 'e' to 'ǝ',
        'f' to 'ɟ', 'g' to 'ƃ', 'h' to 'ɥ', 'i' to 'ᴉ', 'j' to 'ɾ',
        'k' to 'ʞ', 'l' to 'l', 'm' to 'ɯ', 'n' to 'u', 'o' to 'o',
        'p' to 'd', 'q' to 'b', 'r' to 'ɹ', 's' to 's', 't' to 'ʇ',
        'u' to 'n', 'v' to 'ʌ', 'w' to 'ʍ', 'x' to 'x', 'y' to 'ʎ', 'z' to 'z'
    )
}
