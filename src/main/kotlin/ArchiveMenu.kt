class Archive(override val name: String): MenuScreen() {

    private var notesList: MutableList<Note> = mutableListOf()
    override val menuTitle = "Меню архива \"${name}\":"

    override fun open() {
        val command2Action: MutableList<Pair<String, () -> Boolean>> = createCommand2Actions(notesList)

        processApp(menuTitle, notesList, command2Action)
    }

    private fun createCommand2Actions(notesList: MutableList<Note>): MutableList<Pair<String, () -> Boolean>> {
        val command2Action: MutableList<Pair<String, () -> Boolean>> = mutableListOf()

        command2Action.add(Pair("0") {
            val inputName = getInputCheckNotEmpty("Введите название заметки")
            val inputText = getInputCheckNotEmpty("Введите текст заметки")

            val newNote = Note(inputName, inputText)
            notesList.add(newNote)

            addNewCommand(command2Action, newNote)
            false
        })
        // добавляем пункты с 1 по последний - открытие элементов меню и выход
        addCommandsFromOneToLast(command2Action, notesList)

        return command2Action
    }
}