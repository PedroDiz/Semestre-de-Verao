fun main(args: Array<String>) {
    val board = buildBoard()
    board.placeInitialPieces()
    board.startGame()
    while (true) {
        board.printBoard()
        if (board.isGame()) break
        val Pair = readInput()
        if(board.next(Pair) == "InvalidMove") {
           println("Please play another position")
        }
    }
    val winner = board.checkWinner()
    print("Winner is $winner")
}

    fun readInput() : Pair<Int,Int> {
        val CharArray = readln().trim().split(" ")
        return Pair(CharArray[0].toInt(), CharArray[1].toInt())
    }