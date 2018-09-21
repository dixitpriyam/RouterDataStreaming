package S3

import java.io.File

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
//import play.api._

object StoreFilesToS3 {
  val bucketName = "test"
  //file to upload
  val fileToUploadPath="/wordout1/part-00000"
  val fileToUpload = new File("/wordout1/part-00000")


  val AWS_ACCESS_KEY = "XX"
  val AWS_SECRET_KEY = "XX"
  val SUFFIX: String  = "/"




  def main(args: Array[String]): Unit = {
    val yourAWSCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY)
    val amazonS3Client = new AmazonS3Client(yourAWSCredentials)
    // This will create a bucket for storage
    //  amazonS3Client.createBucket(bucketName)

    // create folder into bucket
    val folderName = "testfolder"
    createFolder(bucketName, folderName, amazonS3Client)
    fileUpload(bucketName, folderName, amazonS3Client)
//    deleteFolder(bucketName, folderName, amazonS3Client)

//    amazonS3Client.putObject(bucketName, "FileName", fileToUpload)
  }


  def createFolder(bucketName: String, folderName: String, amazonS3Client: AmazonS3Client) {
    import java.io.ByteArrayInputStream

    import com.amazonaws.services.s3.model.{ObjectMetadata, PutObjectRequest}
    // create meta-data for your folder and set content-length to 0// create meta-data for your folder and set content-length to 0

    val metadata = new ObjectMetadata
    metadata.setContentLength(0)
    // create empty content
    val emptyContent = new ByteArrayInputStream(new Array[Byte](0))
    // create a PutObjectRequest passing the folder name suffixed by /
    val putObjectRequest = new PutObjectRequest(bucketName, folderName + SUFFIX, emptyContent, metadata)
    // send request to S3 to create folder
    amazonS3Client.putObject(putObjectRequest)

  }



  def deleteFolder(bucketName: String, folderName: String, amazonS3Client: AmazonS3Client): Unit = {
    val fileList = amazonS3Client.listObjects(bucketName, folderName).getObjectSummaries
    import scala.collection.JavaConversions._
    for (file <- fileList) {
      amazonS3Client.deleteObject(bucketName, file.getKey)
    }
    amazonS3Client.deleteObject(bucketName, folderName)
  }


  def fileUpload(bucketName: String, folderName: String, amazonS3Client: AmazonS3Client): Unit = {
    import com.amazonaws.services.s3.model.CannedAccessControlList
    import com.amazonaws.services.s3.model.PutObjectRequest
    // upload file to folder and set it to public// upload file to folder and set it to public

    val fileName = folderName + SUFFIX + fileToUpload
    amazonS3Client
      .putObject(new PutObjectRequest(bucketName, fileName, new Nothing(fileToUploadPath))
        .withCannedAcl(CannedAccessControlList.PublicRead))
  }
}
