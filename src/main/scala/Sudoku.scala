object Sudoku {

  type SudokuGrid = Array[Array[Int]]

  def isRowValid(row: Array[Int]): Boolean = {
    val numbers = row.filter(_ > 0).toSet
    numbers.size == row.count(_ > 0) && numbers.size == 9
  }

  def isColValid(grid: SudokuGrid, colIdx: Int): Boolean = {
    val numbers = (0 until 9).map(rowIdx => grid(rowIdx)(colIdx)).filter(_ > 0).toSet
    numbers.size == (0 until 9).count(rowIdx => grid(rowIdx)(colIdx) > 0) && numbers.size == 9
  }

  def isBlockValid(grid: SudokuGrid, startRow: Int, startCol: Int): Boolean = {
    val numbers = for {
      row <- startRow until (startRow + 3)
      col <- startCol until (startCol + 3)
      num = grid(row)(col)
      if num > 0
    } yield num
    val uniqueNumbers = numbers.toSet
    uniqueNumbers.size == numbers.size
  }

  def isSudokuValid(grid: SudokuGrid): Boolean = {
    (0 until 9).forall(rowIdx => isRowValid(grid(rowIdx))) &&
      (0 until 9).forall(colIdx => isColValid(grid, colIdx)) &&
      (0 until 9 by 3).forall(rowIdx =>
        (0 until 9 by 3).forall(colIdx =>
          isBlockValid(grid, rowIdx, colIdx)
        )
      )
  }

  def solve(grid: SudokuGrid): SudokuGrid = {
    def solveSudoku(grid: SudokuGrid): Boolean = {
      def findEmptyLocation: Option[(Int, Int)] = {
        (for {
          rowIdx <- 0 until 9
          colIdx <- 0 until 9
          if grid(rowIdx)(colIdx) == 0
        } yield (rowIdx, colIdx)).headOption
      }

      def isValidMove(rowIdx: Int, colIdx: Int, num: Int): Boolean = {
        !grid(rowIdx).contains(num) &&
          !grid.map(_(colIdx)).contains(num) &&
          !grid.slice(rowIdx / 3 * 3, rowIdx / 3 * 3 + 3)
            .exists(_.slice(colIdx / 3 * 3, colIdx / 3 * 3 + 3).contains(num))
      }

      def solve: Boolean = {
        findEmptyLocation match {
          case None => true // Solved
          case Some((rowIdx, colIdx)) =>
            (1 to 9).exists { num =>
              if (isValidMove(rowIdx, colIdx, num)) {
                grid(rowIdx)(colIdx) = num
                if (solve) true
                else {
                  grid(rowIdx)(colIdx) = 0
                  false
                }
              } else false
            }
        }
      }

      solve
    }

    val newGrid = grid.map(_.clone)
    solveSudoku(newGrid)
    newGrid
  }

  def main(args: Array[String]): Unit = {
    val validSudoku: SudokuGrid = Array(
      Array(4, 3, 5, 2, 6, 9, 7, 8, 1),
      Array(6, 8, 2, 5, 7, 1, 4, 9, 3),
      Array(1, 9, 7, 8, 3, 4, 5, 6, 2),
      Array(8, 2, 6, 1, 9, 5, 3, 4, 7),
      Array(3, 7, 4, 6, 8, 2, 9, 1, 5),
      Array(9, 5, 1, 7, 4, 3, 6, 2, 8),
      Array(5, 1, 9, 3, 2, 6, 8, 7, 4),
      Array(2, 4, 8, 9, 5, 7, 1, 3, 6),
      Array(7, 6, 3, 4, 1, 8, 2, 5, 9)
    )

    val invalidSudoku: SudokuGrid = Array(
      Array(1, 3, 5, 0, 7, 0, 0, 0, 0),
      Array(6, 0, 0, 1, 9, 5, 0, 0, 0),
      Array(0, 9, 8, 0, 0, 0, 0, 6, 0),
      Array(8, 0, 0, 0, 6, 0, 0, 0, 3),
      Array(4, 0, 0, 8, 0, 3, 0, 0, 1),
      Array(7, 0, 0, 0, 2, 0, 0, 0, 6),
      Array(0, 6, 0, 0, 0, 0, 2, 8, 0),
      Array(0, 0, 0, 4, 1, 9, 0, 0, 5),
      Array(0, 0, 0, 0, 8, 0, 0, 7, 9)
    )

    println(isSudokuValid(validSudoku)) // Should print true
    println(isSudokuValid(invalidSudoku)) // Should print false

    val unsolvedSudoku: SudokuGrid = Array(
      Array(4, 3, 5, 2, 6, 9, 7, 8, 1),
      Array(6, 8, 2, 5, 7, 1, 4, 9, 3),
      Array(1, 9, 0, 8, 3, 4, 5, 6, 2),
      Array(8, 2, 6, 1, 9, 5, 3, 4, 7),
      Array(3, 7, 4, 6, 8, 2, 9, 1, 5),
      Array(9, 5, 1, 7, 4, 3, 6, 0, 8),
      Array(5, 1, 9, 3, 2, 6, 8, 7, 4),
      Array(2, 0, 8, 9, 5, 7, 1, 3, 0),
      Array(7, 6, 3, 4, 1, 8, 2, 5, 0)
    )

    val solvedSudoku = solve(unsolvedSudoku)

    if (!isSudokuValid(solvedSudoku)) {
      println("Invalid Sudoku")
      System.exit(1)
    }
    println("Solved Sudoku:")
    solvedSudoku.foreach(row => println(row.mkString(" ")))
  }
}
