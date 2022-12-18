val gameField = mutableListOf<MutableList<Char>>()
const val X = 'X'
const val O = 'O'
const val DEFAULT = '-'
const val EMPTY = ' '
var step: Int = 0
var coordinateX: Int = 0
var coordinateY: Int = 0


fun main() {
    createFiled()
    play()
}

fun play() {
    while (true) {
        move()
        if (checkWin()) break
    }
}

fun move() {
    var canMove = false
    while (!canMove) {
        canMove = inputCoordinates()
    }
    markCell(coordinateX, coordinateY, stepNext())
    showField()
}

fun inputCoordinates(): Boolean {
    val var1 = readln()
    var isCorrect = true

    try {
        coordinateX = var1.substringBefore(" ").toInt()
        coordinateY = var1.substringAfter(" ").toInt()
    } catch (e: NumberFormatException) {
        println("You should enter numbers!")
        isCorrect = false
        return isCorrect
    }
    if ((coordinateX < 1 || coordinateX > 3) || (coordinateY < 1 || coordinateY > 3)) {
        println("Coordinates should be from 1 to 3!")
        isCorrect = false
        return isCorrect
    } else if (gameField[coordinateX - 1][coordinateY - 1] == X || gameField[coordinateX - 1][coordinateY - 1] == O) {
        println("This cell is occupied! Choose another one!")
        isCorrect = false
    }
    return isCorrect
}

fun markCell(x: Int, y: Int, value: Char) {
    gameField[x - 1][y - 1] = value
    step++
}

fun stepNext(): Char = if ((step % 2) == 0) X else O

fun checkWin(): Boolean {
    if ((checkHorizontal(X) == DEFAULT && checkVertical(X) == DEFAULT && checkDiagonal(X) == DEFAULT)
        && (checkHorizontal(O) == DEFAULT && checkVertical(O) == DEFAULT && checkDiagonal(O) == DEFAULT)
    ) {
        if (!isEmptyCells()) {
            println("Draw")
            return true
        }
    } else if ((checkHorizontal(X) == X || checkVertical(X) == X || checkDiagonal(X) == X)
        && !(checkHorizontal(O) == O || checkVertical(O) == O || checkDiagonal(O) == O)
    ) {
        println("X wins")
        return true
    } else if ((checkHorizontal(O) == O || checkVertical(O) == O || checkDiagonal(O) == O)
        && !(checkHorizontal(X) == X || checkVertical(X) == X || checkDiagonal(X) == X)
    ) {
        println("O wins")
        return true
    }
    return false
}

fun isEmptyCells(): Boolean {
    var result = false
    for (i in gameField) {
        for (j in i) {
            if (j == EMPTY) result = true
        }
    }
    return result
}

fun checkHorizontal(input: Char): Char {
    var result: Char = DEFAULT
    for (i in 0 until 3) {
        if (gameField[i][0] == input && gameField[i][1] == input && gameField[i][2] == input) {
            result = input
            break
        }
    }
    return result
}

fun checkVertical(input: Char): Char {
    var result: Char = DEFAULT
    for (i in 0 until 3) {
        if (gameField[0][i] == input && gameField[1][i] == input && gameField[2][i] == input) {
            result = input
            break
        }
    }
    return result
}

fun checkDiagonal(input: Char): Char {
    var result: Char = DEFAULT
    if (gameField[0][0] == input && gameField[1][1] == input && gameField[2][2] == input) {
        result = input
    }
    if (gameField[0][2] == input && gameField[1][1] == input && gameField[2][0] == input) {
        result = input
    }
    return result
}

fun showField() {
    println("---------")
    for (i in 0 until 3) {
        println("| ${gameField[i].joinToString(" ")} |")
    }
    println("---------")
}

fun createFiled() {
    for (i in 0 until 3) {
        val tempList = mutableListOf<Char>()
        for (j in 0 until 3) {
            tempList.add(' ')
        }
        gameField.add(tempList.toMutableList())
    }
    showField()
}
