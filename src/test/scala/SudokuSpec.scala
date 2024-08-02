import Sudoku.{SudokuGrid, isBlockValid, isColValid, isRowValid, isSudokuValid}
import org.scalatest.funsuite.AnyFunSuite

class SudokuSpec extends AnyFunSuite {

  test("Sudoku is valid") {
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

    assert(isSudokuValid(validSudoku))
  }

  test("Sudoku is invalid") {
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

    assert(!isSudokuValid(invalidSudoku))
  }

  test("Sudoku row is valid") {
    val row = Array(4, 3, 5, 2, 6, 9, 7, 8, 1)
    assert(isRowValid(row))
  }

  test("Sudoku row is invalid") {
    val row = Array(4, 3, 5, 2, 6, 9, 7, 8, 4)
    assert(!isRowValid(row))
  }

  test("Sudoku column is valid") {
    val grid: SudokuGrid = Array(
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
    assert(isColValid(grid, 0))
  }

  test("Sudoku column is invalid") {
    val grid: SudokuGrid = Array(
      Array(4, 1, 5, 2, 6, 9, 7, 8, 1),
      Array(6, 8, 2, 5, 7, 1, 4, 9, 3),
      Array(1, 9, 7, 8, 3, 4, 5, 6, 2),
      Array(8, 2, 6, 1, 9, 5, 3, 4, 7),
      Array(3, 7, 4, 6, 8, 2, 9, 1, 5),
      Array(9, 5, 1, 7, 4, 3, 6, 2, 8),
      Array(5, 1, 9, 3, 2, 6, 8, 7, 4),
      Array(2, 4, 8, 9, 5, 7, 1, 3, 6),
      Array(7, 6, 3, 4, 1, 8, 2, 5, 9)
    )
    assert(!isColValid(grid, 1))
  }

  test("Sudoku block is valid") {
    val grid: SudokuGrid = Array(
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
    assert(isBlockValid(grid, 0, 0))
  }

  test("Sudoku block is invalid") {
    val grid: SudokuGrid = Array(
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
    assert(!isBlockValid(grid, 0, 1))
  }
}