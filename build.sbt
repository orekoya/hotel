import scala.sys.process.Process

gitHash := Process("git re-parse HEAD").lines.head

libraryDependencies +=
    "org.specs2" %% "specs2" % "1.14" % "test"