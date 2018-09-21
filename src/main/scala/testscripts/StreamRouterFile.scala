//package testscripts
//
//
//import org.apache.spark.rdd.RDD.rddToPairRDDFunctions
//import org.apache.spark.{SparkConf, SparkContext}
//object Wordcount {
//  def main(args: Array[String]){
//    val conf1=new SparkConf()
//      .setAppName("WordCount").setMaster("local[*]")
//    val sc=new SparkContext(conf1)
//    val test=sc.textFile("file:///C:/Users/priyamdixit/Desktop/SparkData/filePath.txt")
//    test.flatMap{line=>line.split(" ")}.map{word=>(word,1)}.reduceByKey(_+_).saveAsTextFile("./wordout1")
//    sc.stop
//  }
//}