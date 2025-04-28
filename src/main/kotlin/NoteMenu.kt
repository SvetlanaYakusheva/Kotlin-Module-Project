class Note(override val name: String, val text: String): ItemHasName {

    fun showText() {
        println("Содержание заметки \"$name\":")
        println(text)
    }

    override fun open() {
        val type : String = "Просмотр заметки"
        while (true) {
            showMenu(
                "Меню заметки \"${name}\":",
                type,
                mutableListOf()
            )
            val command = getCommand(listOf(),type)
            when (command) {
                "0" -> showText()
                "1" -> return
            }
        }
    }
}
