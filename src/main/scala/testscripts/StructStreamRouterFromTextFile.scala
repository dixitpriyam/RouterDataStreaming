//package testscripts
//
//import org.apache.spark.sql.SparkSession
//import org.apache.spark.sql.types.StructType
//
//object StructStreamRouterFromTextFile{
//  def main (args:Array[String]){
//
//
//    val spark = SparkSession
//      .builder
//      .appName("StructuredNetworkWordCount")
//      .config("spark.master", "local")
//      .config("spark.sql.warehouse.dir", "file:///C:/Users/priyamdixit/Desktop/RDA_01/spark-warehouse")
//      .getOrCreate()
//
//    val userSchema = new StructType().add("name", "string")
//    // Create DataFrame representing the stream of input lines from connection to host:port
//
//    //    val df = sqlContext.read.
//    //      format("com.databricks.spark.csv").
//    //      option("header","true").
//    //      option("inferSchema","true").
//    //      load("employee.csv") //load data from a file
//
//
//
//    //    val lines = spark.readStream
//    //      .format("com.databricks.spark.csv")
//    //      .schema(userSchema)
//    //      .load("file:///C:/Users/priyamdixit/Desktop/SparkData/C2ImportCalEventSample.csv")
//
//    val lines = spark.readStream
//      .option("path","file:///C:/Users/priyamdixit/Desktop/SparkData/userdata1.parquet")
//      .schema(userSchema)
//      .load()
//
//    import spark.implicits._
//    // Split the lines into words
//    val words = lines.as[String]
//
//    // Generate running word count
//    val wordCounts = words.groupBy("name").count()
//
//    // Start running the query that prints the running counts to the console
//    val query = wordCounts.writeStream
//      .outputMode("complete")
//      .format("console")
//      .start()
//
//    query.awaitTermination()
//  }
//}
