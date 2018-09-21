//package testscripts
//
//import org.apache.spark.SparkConf
//import org.apache.spark.streaming.{Seconds, StreamingContext}
//
//object StreamRouterFromFile{
//  def main(args: Array[String]) {
//    val conf = new SparkConf().setMaster("local[1]").setAppName("StreamRouterFromFile")
//    val ssc = new StreamingContext(conf, Seconds(10))
//
//
//    // Create a DStream
//    val lines = ssc.textFileStream("wordout1/")
//    // Transformation
//    val words = lines.flatMap(_.split(" "))
//    val pairs = words.map(word => (word, 1))
//    val wordCounts = pairs.reduceByKey(_ + _)
//
//    wordCounts.print()
//    lines.print()
//    ssc.start() // Start the computation
//    ssc.awaitTermination() // Wait for the computation to terminate
//
//  }}
