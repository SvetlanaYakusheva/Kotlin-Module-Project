import java.util.Scanner

fun main(args: Array<String>) {
    println("Добро пожаловать в программу \"Заметки\"!")

    val screen = MainMenu("Главный экран")
    screen.open()

    println("Программа завершена. До свидания!")
}


//interface ItemHasName {
//    val name: String
//}

abstract class MenuScreen() {
    abstract val name: String
    abstract val menuTitle: String
    abstract fun open()

}

//функция для обработки команды
fun <T> processApp(
    menuTitle: String,
    elements: MutableList<T>,
    command2Action: MutableList<Pair<String, () -> Boolean>>
) where T: MenuScreen {

    var needExit = false
    while (!needExit) {
        showMenu(menuTitle, elements)

        val inputCommand = getCommand(menuTitle, elements)

        for ((command, action) in command2Action) {
            if (command == inputCommand) {
                needExit = action()
                break
            }
        }
    }
}

//функция для вывода на экран элементов текущего меню
fun <T> showMenu(menuTitle: String, list: List<T>) where T: MenuScreen {
    println() //делаем отступ в выводе на экране
    println(menuTitle)

    val firstItemInMenu = when (menuTitle.substring(0, 7)) {
        "Главное" -> "Создать архив"
        "Меню ар" -> "Создать заметку"
        else -> "Просмотр заметки"
    }
    println("0. $firstItemInMenu")
    for (a in 1 .. list.size) {
        println("$a. ${list[a - 1].name}")
    }
    println("${list.size + 1}. Выход")

}

//функция для проверки непустого ввода в консоли
fun getInputCheckNotEmpty(message: String) :  String {
    println(message)

    var input = ""
    while (input.trim() == "") {
        input = Scanner(System.`in`).nextLine()
    }
    return input
}

//функция для проверки ввода команд - должна быть цифра в диапазоне элементов, указанных на экране
fun <T> getCommand(menuTitle: String, list: List<T>) : String where T: MenuScreen {

    while (true) {
        val command: Int? = Scanner(System.`in`).nextLine().toIntOrNull()
        when {
            (command == null) -> println("Cледует вводить цифру. Повторите ввод команды:")
            (command > list.size + 1) -> println("Такой цифры нет в меню. Повторите ввод команды:")
            else -> return command.toString()
        }

        showMenu(menuTitle, list)
    }
}
// добавляем в список команд новый пункт на последнее место,
// а предыдущий последний пункт списка команд (Выход) добавляем в конец списка
fun <T> addNewCommand(
    command2Action: MutableList<Pair<String, () -> Boolean>>,
    t: T
) where T: MenuScreen {

    val lastActionIndex = command2Action.size - 1
    val exitCommand = command2Action[lastActionIndex]

    command2Action[lastActionIndex] =
            Pair(lastActionIndex.toString()) { t.open(); false }
    command2Action.add(Pair((lastActionIndex + 1).toString(), exitCommand.second))
}
//функция для заполнения списка команд с лямбдами с 1 по последний номер,
// кроме пункта 0 - добавление нового выполняется отдельно
fun <T> addCommandsFromOneToLast(
    command2Action: MutableList<Pair<String, () -> Boolean>>,
    list: List<T>
) where T: MenuScreen {
    // добавляем пункты с 1 по предпоследний - открытие элементов меню
    for ((index, item) in list.withIndex()) {
        command2Action.add(Pair((index + 1).toString()) { item.open(); false })
    }
    //добавляем последний пункт - команда Выход
    command2Action.add(Pair((list.size + 1).toString()) { true })
}