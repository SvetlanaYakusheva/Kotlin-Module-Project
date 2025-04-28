import java.util.Scanner

fun main(args: Array<String>) {
    println("Добро пожаловать в программу \"Заметки\"")
    start()
    println("Программа завершена. До свидания!")
}

interface ItemHasName {
    val name: String
    fun open()
}


fun <T> processApp(
    menuTitle: String,
    subtitle: String,
    elements: MutableList<T>,
    command2Action: MutableList<Pair<String, () -> Boolean>>
) where T: ItemHasName {

    var needExit = false
    while (!needExit) {
        showMenu(menuTitle, subtitle, elements)

        val inputCommand = getCommand(elements, subtitle)

        for ((command, action) in command2Action) {
            if (command == inputCommand) {
                needExit = action()
                break
            }
        }
    }
}

fun <T> showMenu(menuTitle: String, type: String, list: List<T>) where T: ItemHasName {
    println(menuTitle)

    println("0. $type")
    for (a in 1 .. list.size) {
        println("$a. ${list[a - 1].name}")
    }
    println("${list.size + 1}. Выход")
}

fun getInputCheckNotEmpty(type: String) :  String {
    println(type)

    var input = ""
    while (input.trim() == "") {
        input = Scanner(System.`in`).nextLine()
    }
    return input
}

fun <T> getCommand(list: List<T>, menuName: String) : String where T: ItemHasName {
    //проверка ввода команды
    while (true) {
        val command: Int? = Scanner(System.`in`).nextLine().toIntOrNull()
        when {
            (command == null) -> println("Cледует вводить цифру. Повторите ввод команды:")
            (command > list.size + 1) -> println("Такой цифры нет в меню. Повторите ввод команды:")
            else -> return command.toString()
        }

        showMenu("Выберите номер команды: ", menuName, list)
    }
}