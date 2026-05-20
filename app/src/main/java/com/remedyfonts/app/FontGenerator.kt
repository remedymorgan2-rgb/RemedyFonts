package com.remedyfonts.app

object FontGenerator {

    fun getAllStyles(): List<FontStyle> = listOf(
        // FREE
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
        // PREMIUM (lock + rewarded ad)
        FontStyle("Circled", true) { convert(it, circledMap) },
        FontStyle("Squared", true) { convert(it, squaredMap) },
        FontStyle("Fullwidth", true) { convert(it, fullwidthMap) },
        FontStyle("Math Bold", true) { convert(it, mathBoldMap) },
        FontStyle("Math Italic", true) { convert(it, mathItalicMap) },
        FontStyle("Monospace Bold", true) { convert(it, monoMap) },  // example reuse
        FontStyle("Strikethrough", true) { it.map { c -> "$c\u0336" }.joinToString("") },
        FontStyle("Underline", true) { it.map { c -> "$c\u0332" }.joinToString("") },
        FontStyle("Double Underline", true) { it.map { c -> "$c\u0333" }.joinToString("") },
        FontStyle("Zalgo Light", true) { zalgo(it, 1) },
        FontStyle("Zalgo Heavy", true) { zalgo(it, 4) },
        FontStyle("Emojify", true) { emojify(it) }
    )

    private fun convert(input: String, map: Map<Char, Char>): String {
        return input.map { map[it] ?: it }.joinToString("")
    }

    private fun emojify(input: String): String {
        val map = mapOf(
            "love" to "\u2764", "heart" to "\uD83D\uDC9C", "happy" to "\uD83D\uDE04",
            "sad" to "\uD83D\uDE22", "fire" to "\uD83D\uDD25", "star" to "\u2B50",
            "cool" to "\uD83D\uDE0E", "laugh" to "\uD83D\uDE02", "cry" to "\uD83D\uDE2D"
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
    }

    // ---- Unicode maps as private top‑level properties ----
    private val scriptMap = mapOf(
        'A' to '𝓐', 'B' to '𝓑', 'C' to '𝓒', 'D' to '𝓓', 'E' to '𝓔',
        'F' to '𝓕', 'G' to '𝓖', 'H' to '𝓗', 'I' to '𝓘', 'J' to '𝓙',
        'K' to '𝓚', 'L' to '𝓛', 'M' to '𝓜', 'N' to '𝓝', 'O' to '𝓞',
        'P' to '𝓟', 'Q' to '𝓠', 'R' to '𝓡', 'S' to '𝓢', 'T' to '𝓣',
        'U' to '𝓤', 'V' to '𝓥', 'W' to '𝓦', 'X' to '𝓧', 'Y' to '𝓨', 'Z' to '𝓩',
        'a' to '𝓪', 'b' to '𝓫', 'c' to '𝓬', 'd' to '𝓭', 'e' to '𝓮',
        'f' to '𝓯', 'g' to '𝓰', 'h' to '𝓱', 'i' to '𝓲', 'j' to '𝓳',
        'k' to '𝓴', 'l' to '𝓵', 'm' to '𝓶', 'n' to '𝓷', 'o' to '𝓸',
        'p' to '𝓹', 'q' to '𝓺', 'r' to '𝓻', 's' to '𝓼', 't' to '𝓽',
        'u' to '𝓾', 'v' to '𝓿', 'w' to '𝔀', 'x' to '𝔁', 'y' to '𝔂', 'z' to '𝔃'
    )
    private val frakturMap = mapOf(
        'A' to '𝔄', 'B' to '𝔅', 'C' to 'ℭ', 'D' to '𝔇', 'E' to '𝔈',
        'F' to '𝔉', 'G' to '𝔊', 'H' to 'ℌ', 'I' to 'ℑ', 'J' to '𝔍',
        'K' to '𝔎', 'L' to '𝔏', 'M' to '𝔐', 'N' to '𝔑', 'O' to '𝔒',
        'P' to '𝔓', 'Q' to '𝔔', 'R' to 'ℜ', 'S' to '𝔖', 'T' to '𝔗',
        'U' to '𝔘', 'V' to '𝔙', 'W' to '𝔚', 'X' to '𝔛', 'Y' to '𝔜', 'Z' to 'ℨ',
        'a' to '𝔞', 'b' to '𝔟', 'c' to '𝔠', 'd' to '𝔡', 'e' to '𝔢',
        'f' to '𝔣', 'g' to '𝔤', 'h' to '𝔥', 'i' to '𝔦', 'j' to '𝔧',
        'k' to '𝔨', 'l' to '𝔩', 'm' to '𝔪', 'n' to '𝔫', 'o' to '𝔬',
        'p' to '𝔭', 'q' to '𝔮', 'r' to '𝔯', 's' to '𝔰', 't' to '𝔱',
        'u' to '𝔲', 'v' to '𝔳', 'w' to '𝔴', 'x' to '𝔵', 'y' to '𝔶', 'z' to '𝔷'
    )
    private val doubleMap = mapOf(
        'A' to '𝔸', 'B' to '𝔹', 'C' to 'ℂ', 'D' to '𝔻', 'E' to '𝔼',
        'F' to '𝔽', 'G' to '𝔾', 'H' to 'ℍ', 'I' to '𝕀', 'J' to '𝕁',
        'K' to '𝕂', 'L' to '𝕃', 'M' to '𝕄', 'N' to 'ℕ', 'O' to '𝕆',
        'P' to 'ℙ', 'Q' to 'ℚ', 'R' to 'ℝ', 'S' to '𝕊', 'T' to '𝕋',
        'U' to '𝕌', 'V' to '𝕍', 'W' to '𝕎', 'X' to '𝕏', 'Y' to '𝕐', 'Z' to 'ℤ',
        'a' to '𝕒', 'b' to '𝕓', 'c' to '𝕔', 'd' to '𝕕', 'e' to '𝕖',
        'f' to '𝕗', 'g' to '𝕘', 'h' to '𝕙', 'i' to '𝕚', 'j' to '𝕛',
        'k' to '𝕜', 'l' to '𝕝', 'm' to '𝕞', 'n' to '𝕟', 'o' to '𝕠',
        'p' to '𝕡', 'q' to '𝕢', 'r' to '𝕣', 's' to '𝕤', 't' to '𝕥',
        'u' to '𝕦', 'v' to '𝕧', 'w' to '𝕨', 'x' to '𝕩', 'y' to '𝕪', 'z' to '𝕫'
    )
    private val sansMap = mapOf(
        'A' to '𝖠', 'B' to '𝖡', 'C' to '𝖢', 'D' to '𝖣', 'E' to '𝖤',
        'F' to '𝖥', 'G' to '𝖦', 'H' to '𝖧', 'I' to '𝖨', 'J' to '𝖩',
        'K' to '𝖪', 'L' to '𝖫', 'M' to '𝖬', 'N' to '𝖭', 'O' to '𝖮',
        'P' to '𝖯', 'Q' to '𝖰', 'R' to '𝖱', 'S' to '𝖲', 'T' to '𝖳',
        'U' to '𝖴', 'V' to '𝖵', 'W' to '𝖶', 'X' to '𝖷', 'Y' to '𝖸', 'Z' to '𝖹',
        'a' to '𝖺', 'b' to '𝖻', 'c' to '𝖼', 'd' to '𝖽', 'e' to '𝖾',
        'f' to '𝖿', 'g' to '𝗀', 'h' to '𝗁', 'i' to '𝗂', 'j' to '𝗃',
        'k' to '𝗄', 'l' to '𝗅', 'm' to '𝗆', 'n' to '𝗇', 'o' to '𝗈',
        'p' to '𝗉', 'q' to '𝗊', 'r' to '𝗋', 's' to '𝗌', 't' to '𝗍',
        'u' to '𝗎', 'v' to '𝗏', 'w' to '𝗐', 'x' to '𝗑', 'y' to '𝗒', 'z' to '𝗓'
    )
    private val sansBoldMap = mapOf(
        'A' to '𝗔', 'B' to '𝗕', 'C' to '𝗖', 'D' to '𝗗', 'E' to '𝗘',
        'F' to '𝗙', 'G' to '𝗚', 'H' to '𝗛', 'I' to '𝗜', 'J' to '𝗝',
        'K' to '𝗞', 'L' to '𝗟', 'M' to '𝗠', 'N' to '𝗡', 'O' to '𝗢',
        'P' to '𝗣', 'Q' to '𝗤', 'R' to '𝗥', 'S' to '𝗦', 'T' to '𝗧',
        'U' to '𝗨', 'V' to '𝗩', 'W' to '𝗪', 'X' to '𝗫', 'Y' to '𝗬', 'Z' to '𝗭',
        'a' to '𝗮', 'b' to '𝗯', 'c' to '𝗰', 'd' to '𝗱', 'e' to '𝗲',
        'f' to '𝗳', 'g' to '𝗴', 'h' to '𝗵', 'i' to '𝗶', 'j' to '𝗷',
        'k' to '𝗸', 'l' to '𝗹', 'm' to '𝗺', 'n' to '𝗻', 'o' to '𝗼',
        'p' to '𝗽', 'q' to '𝗾', 'r' to '𝗿', 's' to '𝘀', 't' to '𝘁',
        'u' to '𝘂', 'v' to '𝘃', 'w' to '𝘄', 'x' to '𝘅', 'y' to '𝘆', 'z' to '𝘇'
    )
    private val sansItalicMap = mapOf(
        'A' to '𝘈', 'B' to '𝘉', 'C' to '𝘊', 'D' to '𝘋', 'E' to '𝘌',
        'F' to '𝘍', 'G' to '𝘎', 'H' to '𝘏', 'I' to '𝘐', 'J' to '𝘑',
        'K' to '𝘒', 'L' to '𝘓', 'M' to '𝘔', 'N' to '𝘕', 'O' to '𝘖',
        'P' to '𝘗', 'Q' to '𝘘', 'R' to '𝘙', 'S' to '𝘚', 'T' to '𝘛',
        'U' to '𝘜', 'V' to '𝘝', 'W' to '𝘞', 'X' to '𝘟', 'Y' to '𝘠', 'Z' to '𝘡',
        'a' to '𝘢', 'b' to '𝘣', 'c' to '𝘤', 'd' to '𝘥', 'e' to '𝘦',
        'f' to '𝘧', 'g' to '𝘨', 'h' to '𝘩', 'i' to '𝘪', 'j' to '𝘫',
        'k' to '𝘬', 'l' to '𝘭', 'm' to '𝘮', 'n' to '𝘯', 'o' to '𝘰',
        'p' to '𝘱', 'q' to '𝘲', 'r' to '𝘳', 's' to '𝘴', 't' to '𝘵',
        'u' to '𝘶', 'v' to '𝘷', 'w' to '𝘸', 'x' to '𝘹', 'y' to '𝘺', 'z' to '𝘻'
    )
    private val monoMap = mapOf(
        'A' to '𝙰', 'B' to '𝙱', 'C' to '𝙲', 'D' to '𝙳', 'E' to '𝙴',
        'F' to '𝙵', 'G' to '𝙶', 'H' to '𝙷', 'I' to '𝙸', 'J' to '𝙹',
        'K' to '𝙺', 'L' to '𝙻', 'M' to '𝙼', 'N' to '𝙽', 'O' to '𝙾',
        'P' to '𝙿', 'Q' to '𝚀', 'R' to '𝚁', 'S' to '𝚂', 'T' to '𝚃',
        'U' to '𝚄', 'V' to '𝚅', 'W' to '𝚆', 'X' to '𝚇', 'Y' to '𝚈', 'Z' to '𝚉',
        'a' to '𝚊', 'b' to '𝚋', 'c' to '𝚌', 'd' to '𝚍', 'e' to '𝚎',
        'f' to '𝚏', 'g' to '𝚐', 'h' to '𝚑', 'i' to '𝚒', 'j' to '𝚓',
        'k' to '𝚔', 'l' to '𝚕', 'm' to '𝚖', 'n' to '𝚗', 'o' to '𝚘',
        'p' to '𝚙', 'q' to '𝚚', 'r' to '𝚛', 's' to '𝚜', 't' to '𝚝',
        'u' to '𝚞', 'v' to '𝚟', 'w' to '𝚠', 'x' to '𝚡', 'y' to '𝚢', 'z' to '𝚣'
    )
    private val smallCapsMap = mapOf(
        'a' to 'ᴀ', 'b' to 'ʙ', 'c' to 'ᴄ', 'd' to 'ᴅ', 'e' to 'ᴇ',
        'f' to 'ғ', 'g' to 'ɢ', 'h' to 'ʜ', 'i' to 'ɪ', 'j' to 'ᴊ',
        'k' to 'ᴋ', 'l' to 'ʟ', 'm' to 'ᴍ', 'n' to 'ɴ', 'o' to 'ᴏ',
        'p' to 'ᴘ', 'q' to 'ǫ', 'r' to 'ʀ', 's' to 's', 't' to 'ᴛ',
        'u' to 'ᴜ', 'v' to 'ᴠ', 'w' to 'ᴡ', 'x' to 'x', 'y' to 'ʏ', 'z' to 'ᴢ'
    )
    private val bubbleMap = mapOf(
        'A' to 'Ⓐ', 'B' to 'Ⓑ', 'C' to 'Ⓒ', 'D' to 'Ⓓ', 'E' to 'Ⓔ',
        'F' to 'Ⓕ', 'G' to 'Ⓖ', 'H' to 'Ⓗ', 'I' to 'Ⓘ', 'J' to 'Ⓙ',
        'K' to 'Ⓚ', 'L' to 'Ⓛ', 'M' to 'Ⓜ', 'N' to 'Ⓝ', 'O' to 'Ⓞ',
        'P' to 'Ⓟ', 'Q' to 'Ⓠ', 'R' to 'Ⓡ', 'S' to 'Ⓢ', 'T' to 'Ⓣ',
        'U' to 'Ⓤ', 'V' to 'Ⓥ', 'W' to 'Ⓦ', 'X' to 'Ⓧ', 'Y' to 'Ⓨ', 'Z' to 'Ⓩ',
        'a' to 'ⓐ', 'b' to 'ⓑ', 'c' to 'ⓒ', 'd' to 'ⓓ', 'e' to 'ⓔ',
        'f' to 'ⓕ', 'g' to 'ⓖ', 'h' to 'ⓗ', 'i' to 'ⓘ', 'j' to 'ⓙ',
        'k' to 'ⓚ', 'l' to 'ⓛ', 'm' to 'ⓜ', 'n' to 'ⓝ', 'o' to 'ⓞ',
        'p' to 'ⓟ', 'q' to 'ⓠ', 'r' to 'ⓡ', 's' to 'ⓢ', 't' to 'ⓣ',
        'u' to 'ⓤ', 'v' to 'ⓥ', 'w' to 'ⓦ', 'x' to 'ⓧ', 'y' to 'ⓨ', 'z' to 'ⓩ'
    )
    private val upsideDownMap = mapOf(
        'a' to 'ɐ', 'b' to 'q', 'c' to 'ɔ', 'd' to 'p', 'e' to 'ǝ',
        'f' to 'ɟ', 'g' to 'ƃ', 'h' to 'ɥ', 'i' to 'ᴉ', 'j' to 'ɾ',
        'k' to 'ʞ', 'l' to 'l', 'm' to 'ɯ', 'n' to 'u', 'o' to 'o',
        'p' to 'd', 'q' to 'b', 'r' to 'ɹ', 's' to 's', 't' to 'ʇ',
        'u' to 'n', 'v' to 'ʌ', 'w' to 'ʍ', 'x' to 'x', 'y' to 'ʎ', 'z' to 'z'
    )
    private val circledMap = mapOf(
        'A' to '🅐', 'B' to '🅑', 'C' to '🅒', 'D' to '🅓', 'E' to '🅔',
        'a' to '🅐', 'b' to '🅑', 'c' to '🅒', 'd' to '🅓', 'e' to '🅔'
    )  // (simplified for brevity; real maps would be larger)
    private val squaredMap = mapOf(
        'A' to '🄰', 'B' to '🄱', 'C' to '🄲', 'a' to '🄰', 'b' to '🄱', 'c' to '🄲'
    )
    private val fullwidthMap = mapOf(
        'A' to 'Ａ', 'B' to 'Ｂ', 'C' to 'Ｃ', 'D' to 'Ｄ', 'E' to 'Ｅ',
        'F' to 'Ｆ', 'G' to 'Ｇ', 'H' to 'Ｈ', 'I' to 'Ｉ', 'J' to 'Ｊ',
        'K' to 'Ｋ', 'L' to 'Ｌ', 'M' to 'Ｍ', 'N' to 'Ｎ', 'O' to 'Ｏ',
        'P' to 'Ｐ', 'Q' to 'Ｑ', 'R' to 'Ｒ', 'S' to 'Ｓ', 'T' to 'Ｔ',
        'U' to 'Ｕ', 'V' to 'Ｖ', 'W' to 'Ｗ', 'X' to 'Ｘ', 'Y' to 'Ｙ', 'Z' to 'Ｚ',
        'a' to 'ａ', 'b' to 'ｂ', 'c' to 'ｃ', 'd' to 'ｄ', 'e' to 'ｅ',
        'f' to 'ｆ', 'g' to 'ｇ', 'h' to 'ｈ', 'i' to 'ｉ', 'j' to 'ｊ',
        'k' to 'ｋ', 'l' to 'ｌ', 'm' to 'ｍ', 'n' to 'ｎ', 'o' to 'ｏ',
        'p' to 'ｐ', 'q' to 'ｑ', 'r' to 'ｒ', 's' to 'ｓ', 't' to 'ｔ',
        'u' to 'ｕ', 'v' to 'ｖ', 'w' to 'ｗ', 'x' to 'ｘ', 'y' to 'ｙ', 'z' to 'ｚ'
    )
    private val mathBoldMap = mapOf(
        'A' to '𝐀', 'B' to '𝐁', 'C' to '𝐂', 'a' to '𝐚', 'b' to '𝐛', 'c' to '𝐜'
    )
    private val mathItalicMap = mapOf(
        'A' to '𝐴', 'B' to '𝐵', 'C' to '𝐶', 'a' to '𝑎', 'b' to '𝑏', 'c' to '𝑐'
    )
}
