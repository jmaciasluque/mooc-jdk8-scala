import scala.collection.GenSeq


object Lesson3 {
  private val RUN_COUNT: Int = 5

  /**
   * Computes the Levenshtein distance between every pair of words in the
   * subset, and returns a matrix of distances. This actually computes twice as
   * much as it needs to, since for every word a, b it should be the case that
   * lev(a,b) == lev(b,a) i.e., Levenshtein distance is commutative.
   *
   * @param wordList The subset of words whose distances to compute
   * @param parallel Whether to run in parallel
   * @return Matrix of Levenshtein distances
   */
  def computeLevenshtein(wordList: List[String], parallel: Boolean): Array[Array[Int]] = {
    maybeParallel(parallel, wordList).map((a) =>
      maybeParallel(parallel, wordList).map((b) =>
        Levenshtein.lev(a, b)).toArray
    ).toArray
  }

  def maybeParallel(parallel: Boolean, wordList: List[String]): GenSeq[String] with Immutable = {
    if(parallel)
      wordList.par
    else
      wordList
  }

  /**
   * Process a list of random strings and return a modified list
   *
   * @param wordList The subset of words whose distances to compute
   * @param parallel Whether to run in parallel
   * @return The list processed in whatever way you want
   */
  def processWords(wordList: List[String], parallel: Boolean): List[String] = {

    maybeParallel(parallel, wordList.sorted)
      .map(_.toLowerCase)
      .map(_.toUpperCase)
      .filter(!_.startsWith("a"))
      .filter(!_.startsWith("A"))
      .filter(!_.startsWith("m"))
      .filter(!_.startsWith("M"))
      .filter(!_.startsWith("z"))
      .filter(!_.startsWith("Z"))
      .distinct
      .toList
  }

  def main(args: Array[String]) {
    val fullWordList: RandomWords = new RandomWords
    val wordList = fullWordList.createList(3000)
    println("\nScala Sequential Levenshtein\n")
    measure("Sequential", () => computeLevenshtein(wordList, false))
    println("\nScala Parallel Levenshtein\n")
    measure("Parallel", () => computeLevenshtein(wordList, true))
    val anotherWordList = fullWordList.createList(3000)
    println("\nScala Sequential process words\n")
    measure("Sequential", () => processWords(anotherWordList, false))
    println("\nScala Parallel process words\n")
    measure("Parallel", () => processWords(anotherWordList, true))
  }

  def measureOneRun(label: String, supplier: () => Any): Any = {
    val startTime: Long = System.nanoTime
    val result: Any = supplier()
    val endTime: Long = System.nanoTime
    printf("%s took %dms%n", label, (endTime - startTime + 500000L) / 1000000L)
    result
  }

  def measure(label: String, supplier: () => Any): Any = {
    var result: Any = supplier()
    var i: Int = 0
    while (i < RUN_COUNT) {
      result = measureOneRun(label, supplier)
      i = i + 1
    }
    result
  }
}
