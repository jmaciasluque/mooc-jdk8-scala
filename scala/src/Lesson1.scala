object Lesson1 {
  /**
   * Run the exercises to ensure we got the right answers
   */
  def runExercises() {
    println("JDK 8 Lambdas and Streams MOOC Lesson 1 - A Scala way")
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
  }

  /** TODO CHANGE THIS DESCRIPTION!
   * All exercises should be completed using Lambda expressions and the new
   * methods added to JDK 8 where appropriate. There is no need to use an
   * explicit loop in any of the code. Use method references rather than full
   * lambda expressions wherever possible.
   */
  /**
   * Exercise 1
   *
   * Create a string that consists of the first letter of each word in the list
   * of Strings provided.
   */
  private def exercise1() {
    val result = new StringBuilder()

    List("alpha", "bravo", "charlie", "delta", "echo", "foxtrot")
      .foreach(word => result.append(word.charAt(0)))

    println(result)
  }

  /**
   * Exercise 2
   *
   * Remove the words that have odd lengths from the list.
   */
  private def exercise2() {
    List("alpha", "bravo", "charlie", "delta", "echo", "foxtrot")
      .filterNot(_.length % 2 != 0).foreach(println)
  }

  /**
   * Exercise 3
   *
   * Replace every word in the list with its upper case equivalent.
   */
  private def exercise3() {
    List("alpha", "bravo", "charlie", "delta", "echo", "foxtrot")
      .map(_.toUpperCase).foreach(println)
  }

  /**
   * Exercise 4
   *
   * Convert every key-value pair of the map into a string and append them all
   * into a single string, in iteration order.
   */
  private def exercise4() {
    val map = Map(("c", 3), ("b", 2), ("a", 1))
    println(map.toSeq.sortBy(_._1).map(entry => entry._1 + entry._2).mkString)
  }

  /**
   * Exercise 5
   *
   * Create a new thread that prints the numbers from the list.
   */
  private def exercise5() {
     new Thread(new Runnable {
      def run() {
        List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).foreach(print)
      }
    }).start()
  }

  /**
   * Main entry point for application
   *
   * @param args the command line arguments
   */
  def main( args:Array[String] ): Unit = {
    Lesson1.runExercises()
  }
}
