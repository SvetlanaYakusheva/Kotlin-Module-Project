fun start() {

    val archiveList: MutableList<Archive> = mutableListOf()
    val menuTitle = "Главное меню: "
    val type = "Создать архив"

    val command2Action: MutableList<Pair<String, () -> Boolean>> = createCommand2Actions(archiveList)

    println(command2Action)

    processApp(menuTitle, type, archiveList, command2Action)

}

fun createCommand2Actions(archiveList: MutableList<Archive>): MutableList<Pair<String, () -> Boolean>> {
    val command2Action: MutableList<Pair<String, () -> Boolean>> = mutableListOf()

    command2Action.add(Pair("0") {
        val archiveName = getInputCheckNotEmpty("Введите название нового архива")

        val newArchive = Archive(archiveName)
        archiveList.add(newArchive)

        // обновляем список команд
        val lastActionIndex = command2Action.size - 1
        val exitCommand = command2Action[lastActionIndex]
        command2Action[lastActionIndex] =
            Pair(lastActionIndex.toString()) { newArchive.open(); false }
        command2Action.add(Pair((lastActionIndex + 1).toString(), exitCommand.second))

        false
    })

    for ((index, archive) in archiveList.withIndex()) {
        command2Action.add(Pair((index + 1).toString()) { archive.open(); false })
    }

    command2Action.add(Pair((archiveList.size + 1).toString()) { true })

    return command2Action
}




/*
private fun processCommand(command: Int, archiveList: MutableList<Archive>): Boolean {
    var needExit = false

    when (command) {
        0 -> {
            println("Введите название нового архива")
            archiveList.add(Archive(getInputCheckNotEmpty()))
            needExit = false
        }

        (archiveList.size + 1) -> needExit = true

        else -> {
            archiveList[command - 1].open()
            needExit = false
        }
    }
    return needExit
}
*/

/*fun <T> processCommand(command: Int, list: MutableList<T>): Unit {
    when (command) {
        0 -> list.add(createItem(t)
        in 1..(list.size) -> list[command - 1].open()
        else -> exitScreen()
    }
}

inline fun <reified T> createItem(list: MutableList<T>) {
    if (* is ) {
        println("Введите название нового архива")
        Archive(getInputCheckNotEmpty())
    }
    if (t is Note) {
        println("Введите название заметки")
        val inputName = getInputCheckNotEmpty()

        println("Введите текст заметки")
        val inputText = getInputCheckNotEmpty()

        Note(inputName, inputText)
    }
}


 */

