import scala.io.Source


object Lesson2 {
  val WORD_REGEXP: String = "[- .:,]+"

  /**
   * Run the exercises to ensure we got the right answers
   */
  def runExercises() {
    println("JDK 8 Lambdas and Streams MOOC Lesson 2 - A Scala way")
    println("Running exercise 1 solution...")
    exercise1()
    println("Running exercise 2 solution...")
    exercise2()
    println("Running exercise 3 solution...")
    exercise3()
    println("Running exercise 4 solution...")
    exercise4()
    println("Running exercise 5 solution...")
    exercise5()
    println("Running exercise 6 solution...")
    exercise6()
    println("Running exercise 7 solution...")
    exercise7()
  }

  /**
   * Exercise 1
   *
   * Create a new list with all the strings from original list converted to 
   * lower case and print them out.
   */
  private def exercise1() {
    List("The", "Quick", "BROWN", "Fox", "Jumped", "Over", "The", "LAZY", "DOG")
      .map(_.toLowerCase).foreach(println)
  }

  /**
   * Exercise 2
   *
   * Modify exercise 1 so that the new list only contains strings that have an
   * odd length
   */
  private def exercise2() {
    List("The", "Quick", "BROWN", "Fox", "Jumped", "Over", "The", "LAZY", "DOG")
      .filter(_.length % 2 != 0).map(_.toLowerCase).foreach(println)
  }

  /**
   * Exercise 3
   *
   * Join the second, third and forth strings of the list into a single string,
   * where each word is separated by a hyphen (-). Print the resulting string.
   */
  private def exercise3() {
    val list = List("The", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog")
    println(list.slice(1, 4).mkString("-"))
  }

  /**
   * Count the number of lines in the file using the BufferedReader provided
   */
  private def exercise4() {
    // hybrid way
//        try {
//          val reader = Files.newBufferedReader(Paths.get("SonnetI.txt"), StandardCharsets.UTF_8)
//          try {
//            println(reader.lines.count) // hybrid
//          } finally {
//            if (reader != null) reader.close()
//          }
//        }
    // more scala way
    println(Source.fromFile("SonnetI.txt").getLines().length)
  }

  /**
   * Using the BufferedReader to access the file, create a list of words with
   * no duplicates contained in the file.  Print the words.
   *
   * HINT: A regular expression, WORD_REGEXP, is already defined for your use.
   */
  private def exercise5() {
    Source.fromFile("SonnetI.txt")
      .getLines()
      .flatMap(line => line.split(WORD_REGEXP))
      .toSeq
      .distinct
      .foreach(println)
  }

  /**
   * Using the BufferedReader to access the file create a list of words from 
   * the file, converted to lower-case and with duplicates removed, which is
   * sorted by natural order.  Print the contents of the list.
   */
  private def exercise6() {
    Source.fromFile("SonnetI.txt")
      .getLines()
      .flatMap(line => line.split(WORD_REGEXP))
      .map(_.toLowerCase)
      .toSeq
      .distinct
      .sorted
      .foreach(println)
  }

  /**
   * Modify exercise6 so that the words are sorted by length
   */
  private def exercise7() {
    Source.fromFile("SonnetI.txt")
      .getLines()
      .flatMap(line => line.split(WORD_REGEXP))
      .map(_.toLowerCase)
      .toSeq
      .distinct
      .sortBy(_.length)
      .foreach(println)
  }

  /**
   * Main entry point for application
   *
   * @param args the command line arguments
   */
  def main( args:Array[String] ): Unit = {
    Lesson2.runExercises()
  }
}
