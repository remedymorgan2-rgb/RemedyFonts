package com.remedyfonts.app

object FontGenerator {

    fun getAllStyles(): List<FontStyle> = listOf(
        // ===== FREE =====
        FontStyle("Bold Serif", false) { boldSerif(it) },
        FontStyle("Italic Serif", false) { italicSerif(it) },
        FontStyle("Bold Italic", false) { boldItalic(it) },
        FontStyle("Script", false) { script(it) },
        FontStyle("Fraktur", false) { fraktur(it) },
        FontStyle("Double Struck", false) { doubleStruck(it) },
        FontStyle("Sans Serif", false) { sansSerif(it) },
        FontStyle("Sans Bold", false) { sansBold(it) },
        FontStyle("Monospace", false) { monospace(it) },
        FontStyle("Upside Down", false) { upsideDown(it) },

        // ===== PREMIUM (locked, unlock with rewarded ad) =====
        FontStyle("Circled", true) { circled(it) },
        FontStyle("Squared", true) { squared(it) },
        FontStyle("Fullwidth", true) { fullwidth(it) },
        FontStyle("Strikethrough", true) { strikethrough(it) },
        FontStyle("Underline", true) { underline(it) },
        FontStyle("Double Underline", true) { doubleUnderline(it) },
        FontStyle("Zalgo Light", true) { zalgo(it, 1) },
        FontStyle("Zalgo Medium", true) { zalgo(it, 2) },
        FontStyle("Zalgo Heavy", true) { zalgo(it, 4) },
        FontStyle("Emojify", true) { emojify(it) }
    )

    // ===== TRANSFORM FUNCTIONS =====
    private fun boldSerif(input: String): String = input
        .replace("A","𝐀").replace("B","𝐁").replace("C","𝐂").replace("D","𝐃").replace("E","𝐄")
        .replace("F","𝐅").replace("G","𝐆").replace("H","𝐇").replace("I","𝐈").replace("J","𝐉")
        .replace("K","𝐊").replace("L","𝐋").replace("M","𝐌").replace("N","𝐍").replace("O","𝐎")
        .replace("P","𝐏").replace("Q","𝐐").replace("R","𝐑").replace("S","𝐒").replace("T","𝐓")
        .replace("U","𝐔").replace("V","𝐕").replace("W","𝐖").replace("X","𝐗").replace("Y","𝐘").replace("Z","𝐙")
        .replace("a","𝐚").replace("b","𝐛").replace("c","𝐜").replace("d","𝐝").replace("e","𝐞")
        .replace("f","𝐟").replace("g","𝐠").replace("h","𝐡").replace("i","𝐢").replace("j","𝐣")
        .replace("k","𝐤").replace("l","𝐥").replace("m","𝐦").replace("n","𝐧").replace("o","𝐨")
        .replace("p","𝐩").replace("q","𝐪").replace("r","𝐫").replace("s","𝐬").replace("t","𝐭")
        .replace("u","𝐮").replace("v","𝐯").replace("w","𝐰").replace("x","𝐱").replace("y","𝐲").replace("z","𝐳")

    private fun italicSerif(input: String): String = input
        .replace("A","𝐴").replace("B","𝐵").replace("C","𝐶").replace("D","𝐷").replace("E","𝐸")
        .replace("F","𝐹").replace("G","𝐺").replace("H","𝐻").replace("I","𝐼").replace("J","𝐽")
        .replace("K","𝐾").replace("L","𝐿").replace("M","𝑀").replace("N","𝑁").replace("O","𝑂")
        .replace("P","𝑃").replace("Q","𝑄").replace("R","𝑅").replace("S","𝑆").replace("T","𝑇")
        .replace("U","𝑈").replace("V","𝑉").replace("W","𝑊").replace("X","𝑋").replace("Y","𝑌").replace("Z","𝑍")
        .replace("a","𝑎").replace("b","𝑏").replace("c","𝑐").replace("d","𝑑").replace("e","𝑒")
        .replace("f","𝑓").replace("g","𝑔").replace("h","ℎ").replace("i","𝑖").replace("j","𝑗")
        .replace("k","𝑘").replace("l","𝑙").replace("m","𝑚").replace("n","𝑛").replace("o","𝑜")
        .replace("p","𝑝").replace("q","𝑞").replace("r","𝑟").replace("s","𝑠").replace("t","𝑡")
        .replace("u","𝑢").replace("v","𝑣").replace("w","𝑤").replace("x","𝑥").replace("y","𝑦").replace("z","𝑧")

    private fun boldItalic(input: String): String = input
        .replace("A","𝑨").replace("B","𝑩").replace("C","𝑪").replace("D","𝑫").replace("E","𝑬")
        .replace("F","𝑭").replace("G","𝑮").replace("H","𝑯").replace("I","𝑰").replace("J","𝑱")
        .replace("K","𝑲").replace("L","𝑳").replace("M","𝑴").replace("N","𝑵").replace("O","𝑶")
        .replace("P","𝑷").replace("Q","𝑸").replace("R","𝑹").replace("S","𝑺").replace("T","𝑻")
        .replace("U","𝑼").replace("V","𝑽").replace("W","𝑾").replace("X","𝑿").replace("Y","𝒀").replace("Z","𝒁")
        .replace("a","𝒂").replace("b","𝒃").replace("c","𝒄").replace("d","𝒅").replace("e","𝒆")
        .replace("f","𝒇").replace("g","𝒈").replace("h","𝒉").replace("i","𝒊").replace("j","𝒋")
        .replace("k","𝒌").replace("l","𝒍").replace("m","𝒎").replace("n","𝒏").replace("o","𝒐")
        .replace("p","𝒑").replace("q","𝒒").replace("r","𝒓").replace("s","𝒔").replace("t","𝒕")
        .replace("u","𝒖").replace("v","𝒗").replace("w","𝒘").replace("x","𝒙").replace("y","𝒚").replace("z","𝒛")

    private fun script(input: String): String = input
        .replace("A","𝓐").replace("B","𝓑").replace("C","𝓒").replace("D","𝓓").replace("E","𝓔")
        .replace("F","𝓕").replace("G","𝓖").replace("H","𝓗").replace("I","𝓘").replace("J","𝓙")
        .replace("K","𝓚").replace("L","𝓛").replace("M","𝓜").replace("N","𝓝").replace("O","𝓞")
        .replace("P","𝓟").replace("Q","𝓠").replace("R","𝓡").replace("S","𝓢").replace("T","𝓣")
        .replace("U","𝓤").replace("V","𝓥").replace("W","𝓦").replace("X","𝓧").replace("Y","𝓨").replace("Z","𝓩")
        .replace("a","𝓪").replace("b","𝓫").replace("c","𝓬").replace("d","𝓭").replace("e","𝓮")
        .replace("f","𝓯").replace("g","𝓰").replace("h","𝓱").replace("i","𝓲").replace("j","𝓳")
        .replace("k","𝓴").replace("l","𝓵").replace("m","𝓶").replace("n","𝓷").replace("o","𝓸")
        .replace("p","𝓹").replace("q","𝓺").replace("r","𝓻").replace("s","𝓼").replace("t","𝓽")
        .replace("u","𝓾").replace("v","𝓿").replace("w","𝔀").replace("x","𝔁").replace("y","𝔂").replace("z","𝔃")

    private fun fraktur(input: String): String = input
        .replace("A","𝔄").replace("B","𝔅").replace("C","ℭ").replace("D","𝔇").replace("E","𝔈")
        .replace("F","𝔉").replace("G","𝔊").replace("H","ℌ").replace("I","ℑ").replace("J","𝔍")
        .replace("K","𝔎").replace("L","𝔏").replace("M","𝔐").replace("N","𝔑").replace("O","𝔒")
        .replace("P","𝔓").replace("Q","𝔔").replace("R","ℜ").replace("S","𝔖").replace("T","𝔗")
        .replace("U","𝔘").replace("V","𝔙").replace("W","𝔚").replace("X","𝔛").replace("Y","𝔜").replace("Z","ℨ")
        .replace("a","𝔞").replace("b","𝔟").replace("c","𝔠").replace("d","𝔡").replace("e","𝔢")
        .replace("f","𝔣").replace("g","𝔤").replace("h","𝔥").replace("i","𝔦").replace("j","𝔧")
        .replace("k","𝔨").replace("l","𝔩").replace("m","𝔪").replace("n","𝔫").replace("o","𝔬")
        .replace("p","𝔭").replace("q","𝔮").replace("r","𝔯").replace("s","𝔰").replace("t","𝔱")
        .replace("u","𝔲").replace("v","𝔳").replace("w","𝔴").replace("x","𝔵").replace("y","𝔶").replace("z","𝔷")

    private fun doubleStruck(input: String): String = input
        .replace("A","𝔸").replace("B","𝔹").replace("C","ℂ").replace("D","𝔻").replace("E","𝔼")
        .replace("F","𝔽").replace("G","𝔾").replace("H","ℍ").replace("I","𝕀").replace("J","𝕁")
        .replace("K","𝕂").replace("L","𝕃").replace("M","𝕄").replace("N","ℕ").replace("O","𝕆")
        .replace("P","ℙ").replace("Q","ℚ").replace("R","ℝ").replace("S","𝕊").replace("T","𝕋")
        .replace("U","𝕌").replace("V","𝕍").replace("W","𝕎").replace("X","𝕏").replace("Y","𝕐").replace("Z","ℤ")
        .replace("a","𝕒").replace("b","𝕓").replace("c","𝕔").replace("d","𝕕").replace("e","𝕖")
        .replace("f","𝕗").replace("g","𝕘").replace("h","𝕙").replace("i","𝕚").replace("j","𝕛")
        .replace("k","𝕜").replace("l","𝕝").replace("m","𝕞").replace("n","𝕟").replace("o","𝕠")
        .replace("p","𝕡").replace("q","𝕢").replace("r","𝕣").replace("s","𝕤").replace("t","𝕥")
        .replace("u","𝕦").replace("v","𝕧").replace("w","𝕨").replace("x","𝕩").replace("y","𝕪").replace("z","𝕫")

    private fun sansSerif(input: String): String = input
        .replace("A","𝖠").replace("B","𝖡").replace("C","𝖢").replace("D","𝖣").replace("E","𝖤")
        .replace("F","𝖥").replace("G","𝖦").replace("H","𝖧").replace("I","𝖨").replace("J","𝖩")
        .replace("K","𝖪").replace("L","𝖫").replace("M","𝖬").replace("N","𝖭").replace("O","𝖮")
        .replace("P","𝖯").replace("Q","𝖰").replace("R","𝖱").replace("S","𝖲").replace("T","𝖳")
        .replace("U","𝖴").replace("V","𝖵").replace("W","𝖶").replace("X","𝖷").replace("Y","𝖸").replace("Z","𝖹")
        .replace("a","𝖺").replace("b","𝖻").replace("c","𝖼").replace("d","𝖽").replace("e","𝖾")
        .replace("f","𝖿").replace("g","𝗀").replace("h","𝗁").replace("i","𝗂").replace("j","𝗃")
        .replace("k","𝗄").replace("l","𝗅").replace("m","𝗆").replace("n","𝗇").replace("o","𝗈")
        .replace("p","𝗉").replace("q","𝗊").replace("r","𝗋").replace("s","𝗌").replace("t","𝗍")
        .replace("u","𝗎").replace("v","𝗏").replace("w","𝗐").replace("x","𝗑").replace("y","𝗒").replace("z","𝗓")

    private fun sansBold(input: String): String = input
        .replace("A","𝗔").replace("B","𝗕").replace("C","𝗖").replace("D","𝗗").replace("E","𝗘")
        .replace("F","𝗙").replace("G","𝗚").replace("H","𝗛").replace("I","𝗜").replace("J","𝗝")
        .replace("K","𝗞").replace("L","𝗟").replace("M","𝗠").replace("N","𝗡").replace("O","𝗢")
        .replace("P","𝗣").replace("Q","𝗤").replace("R","𝗥").replace("S","𝗦").replace("T","𝗧")
        .replace("U","𝗨").replace("V","𝗩").replace("W","𝗪").replace("X","𝗫").replace("Y","𝗬").replace("Z","𝗭")
        .replace("a","𝗮").replace("b","𝗯").replace("c","𝗰").replace("d","𝗱").replace("e","𝗲")
        .replace("f","𝗳").replace("g","𝗴").replace("h","𝗵").replace("i","𝗶").replace("j","𝗷")
        .replace("k","𝗸").replace("l","𝗹").replace("m","𝗺").replace("n","𝗻").replace("o","𝗼")
        .replace("p","𝗽").replace("q","𝗾").replace("r","𝗿").replace("s","𝘀").replace("t","𝘁")
        .replace("u","𝘂").replace("v","𝘃").replace("w","𝘄").replace("x","𝘅").replace("y","𝘆").replace("z","𝘇")

    private fun monospace(input: String): String = input
        .replace("A","𝙰").replace("B","𝙱").replace("C","𝙲").replace("D","𝙳").replace("E","𝙴")
        .replace("F","𝙵").replace("G","𝙶").replace("H","𝙷").replace("I","𝙸").replace("J","𝙹")
        .replace("K","𝙺").replace("L","𝙻").replace("M","𝙼").replace("N","𝙽").replace("O","𝙾")
        .replace("P","𝙿").replace("Q","𝚀").replace("R","𝚁").replace("S","𝚂").replace("T","𝚃")
        .replace("U","𝚄").replace("V","𝚅").replace("W","𝚆").replace("X","𝚇").replace("Y","𝚈").replace("Z","𝚉")
        .replace("a","𝚊").replace("b","𝚋").replace("c","𝚌").replace("d","𝚍").replace("e","𝚎")
        .replace("f","𝚏").replace("g","𝚐").replace("h","𝚑").replace("i","𝚒").replace("j","𝚓")
        .replace("k","𝚔").replace("l","𝚕").replace("m","𝚖").replace("n","𝚗").replace("o","𝚘")
        .replace("p","𝚙").replace("q","𝚚").replace("r","𝚛").replace("s","𝚜").replace("t","𝚝")
        .replace("u","𝚞").replace("v","𝚟").replace("w","𝚠").replace("x","𝚡").replace("y","𝚢").replace("z","𝚣")

    // ===== PREMIUM HELPERS =====
    private fun upsideDown(input: String): String = input.reversed().map { c ->
        mapOf(
            'a' to 'ɐ', 'b' to 'q', 'c' to 'ɔ', 'd' to 'p', 'e' to 'ǝ',
            'f' to 'ɟ', 'g' to 'ƃ', 'h' to 'ɥ', 'i' to 'ᴉ', 'j' to 'ɾ',
            'k' to 'ʞ', 'l' to 'l', 'm' to 'ɯ', 'n' to 'u', 'o' to 'o',
            'p' to 'd', 'q' to 'b', 'r' to 'ɹ', 's' to 's', 't' to 'ʇ',
            'u' to 'n', 'v' to 'ʌ', 'w' to 'ʍ', 'x' to 'x', 'y' to 'ʎ', 'z' to 'z',
            'A' to '∀', 'B' to 'ᗺ', 'C' to 'Ɔ', 'D' to 'ᗡ', 'E' to 'Ǝ',
            'F' to 'Ⅎ', 'G' to '⅁', 'H' to 'H', 'I' to 'I', 'J' to 'ſ',
            'K' to '⋊', 'L' to '⅂', 'M' to 'W', 'N' to 'N', 'O' to 'O',
            'P' to 'Ԁ', 'Q' to 'Ό', 'R' to 'ᴚ', 'S' to 'S', 'T' to '⊥',
            'U' to '∩', 'V' to 'Λ', 'W' to 'M', 'X' to 'X', 'Y' to '⅄', 'Z' to 'Z'
        )[c] ?: c
    }.joinToString("")

    private fun circled(input: String): String = input.map { "$it⃝" }.joinToString("")

    private fun squared(input: String): String = input.map { "$it⬜" }.joinToString("")

    private fun fullwidth(input: String): String = input.map { c ->
        when {
            c in 'A'..'Z' -> (c.code + 0xFEE0).toChar()
            c in 'a'..'z' -> (c.code + 0xFEE0).toChar()
            c in '0'..'9' -> (c.code + 0xFEE0).toChar()
            else -> c
        }
    }.joinToString("")

    private fun strikethrough(input: String): String = input.map { "$it\u0336" }.joinToString("")

    private fun underline(input: String): String = input.map { "$it\u0332" }.joinToString("")

    private fun doubleUnderline(input: String): String = input.map { "$it\u0333" }.joinToString("")

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

    private fun emojify(input: String): String {
        return input
            .replace("love", "❤️")
            .replace("heart", "💜")
            .replace("happy", "😄")
            .replace("sad", "😢")
            .replace("fire", "🔥")
            .replace("star", "⭐")
            .replace("cool", "😎")
            .replace("laugh", "😂")
            .replace("cry", "😭")
            .replace("angry", "😡")
            .replace("kiss", "😘")
            .replace("sleep", "😴")
    }
}