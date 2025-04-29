class Note(override val name: String, val text: String): MenuScreen() {

    override val menuTitle = "Меню заметки \"${name}\":"

    private fun showText() {
        println("Содержание заметки \"$name\":")
        println(text)
        println() //делаем отступ в выводе на экране
    }

    override fun open() {
        val command2Action: MutableList<Pair<String, () -> Boolean>> = createCommand2Actions()

        processApp(menuTitle, mutableListOf(), command2Action)
    }

    private fun createCommand2Actions(): MutableList<Pair<String, () -> Boolean>> {
        val command2Action: MutableList<Pair<String, () -> Boolean>> = mutableListOf()
        command2Action.add(Pair((0).toString()) { showText(); false })
        command2Action.add(Pair((1).toString()) { true })

        return command2Action
    }
}
    /* override fun open() {

            while (true) {
                showMenu(
                    menuTitle,
                    mutableListOf()
                )
                val command = getCommand(menuTitle, listOf())
                when (command) {
                    "0" -> showText()
                    "1" -> return
                }
            }
        }
         */