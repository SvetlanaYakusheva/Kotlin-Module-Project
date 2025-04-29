class MainMenu(override val name: String): MenuScreen() {

    private val archiveList: MutableList<Archive> = mutableListOf()
    override val menuTitle = "Главное меню: "

    override fun open() {
        val command2Action: MutableList<Pair<String, () -> Boolean>> = createCommand2Actions(archiveList)

        processApp(menuTitle, archiveList, command2Action)
    }

    fun createCommand2Actions(archiveList: MutableList<Archive>): MutableList<Pair<String, () -> Boolean>> {
        val command2Action: MutableList<Pair<String, () -> Boolean>> = mutableListOf()

        //добавляем пункт 0 - создание нового элемента меню,
        //при выборе этого пункта - добавляем в список команд новый пункт на последнее место,
        //а предыдущий последний пункт списка команд (Выход) добавляем в конец списка
        command2Action.add(Pair("0") {
            val archiveName = getInputCheckNotEmpty("Введите название нового архива")
            val newArchive = Archive(archiveName)
            archiveList.add(newArchive)

            addNewCommand(command2Action, newArchive)
            false
        })
        // добавляем пункты с 1 по последний - открытие элементов меню и выход
        addCommandsFromOneToLast(command2Action, archiveList)

        return command2Action
    }
}