# Sudoku Validator and Solver

This Scala application provides functionality for validating and solving Sudoku puzzles. It includes two main features:
1. **Validation**: Check whether a given Sudoku grid is valid.
2. **Solving**: Solve a Sudoku puzzle using backtracking algorithm.

## Project Structure

- **Sudoku.scala**: Contains all the logic for Sudoku validation and solving.
   - `isRowValid(row: Array[Int]): Boolean` - Checks if a single row is valid.
   - `isColValid(grid: SudokuGrid, colIdx: Int): Boolean` - Checks if a single column is valid.
   - `isBlockValid(grid: SudokuGrid, startRow: Int, startCol: Int): Boolean` - Checks if a 3x3 block is valid.
   - `isSudokuValid(grid: SudokuGrid): Boolean` - Validates the entire Sudoku grid.
   - `solve(grid: SudokuGrid): SudokuGrid` - Solves the Sudoku puzzle and returns the solved grid.
   - `main(args: Array[String]): Unit` - Contains example Sudoku grids and runs validation and solving.

## How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/fantomiks/sudoku-validator-solver.git
   ```

2. **Navigate to the project directory:**
   ```bash
   cd sudoku-validator-solver
   ```

3. **Compile and run the application:**
   Ensure you have Scala and sbt installed. Then, use the following commands:
   ```bash
   sbt run
   ```

   This will compile the code and execute the `main` method, which will print the results of the Sudoku validation and solving.

## Example

The `main` method in `Sudoku.scala` includes examples of both valid and invalid Sudoku grids. It prints the results of validation and displays the solved Sudoku grid if the puzzle is solvable.
