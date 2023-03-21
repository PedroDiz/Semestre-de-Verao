class Board {
    private val columns = 8
    private val rows = 8
    private val Board = Array(rows) {CharArray(columns)}
    private var diskCount = 0
    private var whiteDisks = 2
    private var blackDisks = 2

    private var currentPlayer = Player.EMPTY
    public fun printBoard() : Unit {
        Board.forEach {
            it.forEach { print("|$it|") }
            println() }
        println()
    }

    public fun isGame() : Boolean = diskCount == 64

    public fun checkWinner() : String {
        return if(whiteDisks > blackDisks) "WHITE"
        else if(blackDisks > whiteDisks) "BLACK"
        else "DRAW"
    }

    public fun placeInitialPieces() {
        Board[3][3] = 'W'
        Board[4][3] = 'B'
        Board[4][4] = 'W'
        Board[3][4] = 'B'
    }

    private fun isInsideBoard(row : Int, column : Int) : Boolean {
        return row in 0..7 && column in 0..7
    }

    private fun checkFlank(row : Int, column : Int, rowShift : Int, columnShift : Int, player: Player) : List<Pair<Int,Int>> {
        return emptyList()
    }

    private fun checkAllFlanks(row : Int, column : Int , player: Player) : List<Pair<Int,Int>> {
        return emptyList()
    }

    private fun flipDisks(row : Int, column : Int, player: Player) : Unit {
        if(player == Player.WHITE) Board[row][column] = 'W'
        else Board[row][column] = 'B'
    }

    private fun placePiece(player : Player,row : Int, column : Int) : String {
        if(!Board[row][column].isLetter() && player == Player.WHITE) {
            Board[row][column] = 'W'
            whiteDisks++
            val list = checkAllFlanks(row,column,player)
            list.forEach { flipDisks(it.first, it.second, player) }
        }
        else if(!Board[row][column].isLetter() && player == Player.BLACK) {
            Board[row][column] = 'B'
            blackDisks++
            val list = checkAllFlanks(row, column, player)
            list.forEach { flipDisks(it.first, it.second, player) }
        }
        else {
            return "InvalidMove";
        }
        currentPlayer = player.other(player)
        return "ValidMove"
    }

    public fun startGame() : Unit { currentPlayer = Player.WHITE }

    public fun next(Position : Pair<Int,Int>) : String {
        return placePiece(currentPlayer, Position.first, Position.second)
    }
}

public fun buildBoard() : Board = Board()