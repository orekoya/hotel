
// create definition to be used later in the build.
val gitHash = taskKey[String]("Calculate the current git commit hash")

//create new definition that generates a file based on the git hash
val makeVersionFile = taskKey[Seq[File]]("makes a version property file")


name := "hotel cassandra"
organization := "com.hyperbuffer"
version := "1.0.0-SNAPSHOT"

import scala.sys.process._

//define sub-projects
lazy val common = (

    Project("common", file("common"))

    settings(
        gitHash := Process("git rev-parse HEAD").lineStream.head,

        makeVersionFile := {
            val file = new File((resourceManaged in Compile).value , "version.properties")
            println(file.toString)
            IO.write(file, s"version=${gitHash.value}")
            Seq(file)
})
)

lazy val datagen = (
    Project("datagen", file("datagen"))
    dependsOn(common)
    settings(
        version:= "1.1"
    )
)

