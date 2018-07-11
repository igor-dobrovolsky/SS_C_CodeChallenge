package cisco.scala.challenge

import scala.collection.mutable
import scala.io.Source

object Main extends App {
  println("Hi Scala! At last! :)")

  def parseLine(line: String, wordCounts: mutable.Map[String, Int]): Unit = {
    val cWordCounts: Map[String, Int] = line.split("""[^\w\d-]+""")
      .groupBy(s => s)
      .map { case (k, l) => (k, l.length) }

    for (kv <- cWordCounts) {
      if (wordCounts.contains(kv._1))
        wordCounts.update(kv._1, wordCounts(kv._1) + kv._2)
      else
        wordCounts.put(kv._1, kv._2)
    }
  }

  def parseLines(lines: Iterator[String]): Iterator[(String, Int)] = {
    val wordCounts = mutable.Map.empty[String, Int]

    lines.zipWithIndex.foreach(li => {
      if ((li._2 % 357) == 0) println(s"line #${li._2}...\r")
      parseLine(li._1, wordCounts)
    })

    wordCounts.toIterator
  }

  def words =
    parseLines(
      Source.fromFile(
        if(args.length > 0) args(0) else "sample_text.txt" //if no file name is specified, taking default one
      ).getLines()
    )
      .map(kv => (kv._2, kv._1))
      .toIndexedSeq
      .sortBy(-_._1) //desc order

  println(s"Top 10 words:\n ${words.take(10).mkString("\n ")}")

}
