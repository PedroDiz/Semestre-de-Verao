class Board {
    private val columns = 8
    private val rows = 8
    private val Board = Array(rows) {CharArray(columns)}
    private var diskCount = 4
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
        val list = mutableListOf<Pair<Int,Int>>()

        var row = row + rowShift
        var column = column + columnShift
        while (isInsideBoard(row, column) && Board[row][column].isLetter()) {
            if (player.findPlayerByChar(Board[row][column]) == player.other(player)) {
                list.add(Pair(row,column))
                row += rowShift
                column += columnShift
            }
            else {
                return list
            }
        }
        return emptyList()
    }

    private fun checkAllFlanks(row : Int, column : Int , player: Player) : List<Pair<Int,Int>> {
        val list = mutableListOf<Pair<Int,Int>>()
        for(rowShift in -1..1) {
            for(columnShift in -1..1) {
                if(rowShift == 0 && columnShift == 0) continue
                list.addAll(checkFlank(row,column,rowShift,columnShift,player))
            }
        }
        return list
    }

    private fun flipDisks(row : Int, column : Int, player: Player) : Unit {
        if(player == Player.WHITE) {
            Board[row][column] = 'W'
            whiteDisks++
            blackDisks--
        }
        else {
            Board[row][column] = 'B'
            blackDisks++
            whiteDisks--
        }
    }
    private fun placeOnEmptySquare(player : Player,row : Int, column : Int) {
        if(player == Player.WHITE) {
            Board[row][column] = 'W'
        }
        else {
            Board[row][column] = 'B'
        }
        if(player == Player.WHITE) whiteDisks++ else blackDisks++
    }
    private fun placePiece(player : Player,row : Int, column : Int) : String {
        if(!Board[row][column].isLetter()) {
            placeOnEmptySquare(player,row,column)
            val list = checkAllFlanks(row, column, player)
            list.forEach { flipDisks(it.first, it.second, player) }
        }
        else {
            return "InvalidMove";
        }
        currentPlayer = player.other(player)
        diskCount++
        return "ValidMove"
    }

    public fun startGame() : Unit { currentPlayer = Player.WHITE }

    public fun next(Position : Pair<Int,Int>) : String {
        return placePiece(currentPlayer, Position.first, Position.second)
    }
}

public fun buildBoard() : Board = Board()