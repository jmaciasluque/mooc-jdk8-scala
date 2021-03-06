import scala.io.Source
import scala.util.Random


class RandomWords {

  val sourceWords: Array[String] =
    Source.fromFile("words.txt").getLines().toArray

  def createList(listSize: Int): List[String] = {
    Seq.fill[Int](listSize)(Random.nextInt(sourceWords.size))
      .map(sourceWords(_))
      .toList
  }

  def allWords: List[String] = {
    sourceWords.toList
  }
}