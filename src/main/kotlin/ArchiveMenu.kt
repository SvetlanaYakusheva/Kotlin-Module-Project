class Archive(override val name: String): ItemHasName {

    var notes: MutableList<Note> = mutableListOf()
    val type: String = "Создать заметку"

    fun addNote() {
        val inputName = getInputCheckNotEmpty("Введите название заметки")

        val inputText = getInputCheckNotEmpty("Введите текст заметки")

        notes.add(Note(inputName, inputText))
    }

    override fun open() {
        val menuTitle = "Меню архива \"${name}\":"
        val subtitle = "Создать заметку"

        val command2Action : MutableList<Pair<String, () -> Boolean>> = mutableListOf()

        command2Action.add(Pair("0") { addNote(); false })
        for ((index, note) in notes.withIndex()) {
            command2Action.add(Pair((index + 1).toString(), { note.open(); false }))
        }
        command2Action.add(Pair((notes.size + 1).toString()) { true })

        processApp(menuTitle, subtitle, notes, command2Action)
    }

    /*
    private fun processApp(
        menuTitle: String,
        subtitle: String,
        command2Action: MutableList<Pair<String, () -> Boolean>>
    ) {
        var needExit = false
        while (!needExit) {

            showMenu(
                menuTitle,
                subtitle,
                notes
            )

            val inputCommand = getCommand(notes, type).toString()

            for ((command, action) in command2Action) {
                if (command == inputCommand) {
                    needExit = action()
                    break
                }
            }

        }
    }
     */
}