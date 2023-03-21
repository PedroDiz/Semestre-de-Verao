enum class Player {
    EMPTY, WHITE, BLACK;
    fun other(player : Player) : Player = if(player == WHITE) BLACK else WHITE
}