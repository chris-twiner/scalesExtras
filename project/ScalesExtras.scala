import sbt._
import Keys._
import sbt.Package._
import java.util.jar.Attributes.Name._
import Defaults._

object ScalesExtras extends Build {

  object sonatype extends org.improving.PublishToSonatype(ScalesExtras) {
    def projectUrl    = "https://www.github.com/chris-twiner/scalesExtras"
    def developerId   = "chris.twiner"
    def developerName = "Chris Twiner" // TODO - take from the userprops
    override def licenseUrl    = "http://www.opensource.org/licenses/Apache-2.0"
    override def licenseName   = "Apache-2.0"
    // Override more to taste
  }

  lazy val root = Project("extras", file("."), settings = standardSettings)


  lazy val standardSettings = Defaults.defaultSettings ++ Seq(
    shellPrompt := { state =>
      "%s> ".format(Project.extract(state).currentProject.id)
		  },
    organization := "org.scalesxml",
    offline := true,
    version := "0.0.1",
    scalaVersion := "2.10.0-RC3",
    crossScalaVersions := Seq("2.8.1", "2.8.2", "2.9.1", "2.9.2", "2.10.0-RC2", "2.10.0-RC3"),
    scalacOptions ++= Seq("-optimise"),
    packageOptions ++= Seq[PackageOption](ManifestAttributes(
      (IMPLEMENTATION_TITLE, "Scales Extras"),
      (IMPLEMENTATION_URL, "https://github.com/chris-twiner/scalesExtras"),
      (IMPLEMENTATION_VENDOR, "Scales Extras")
      )
    ),
    /* requires many other command line options and installations for windows:
      -Ddot_exe=%dot_exe%
      * against - no spaces
      set dot_exe=c:/PROGRA~2/GRAPHV~1.28/bin/dot.exe
    (scaladocOptions in Compile in doc) <++= (scalaVersion).map{(v: String) => 
      if (v.startsWith("2.10"))
	Seq("-diagrams")
      else
	Seq()
    },
     */ 
    autoCompilerPlugins := false,
    fork in run := true,
    libraryDependencies ++= Seq(
      "commons-codec" % "commons-codec" % "1.4",
      "org.slf4j" % "slf4j-api" % "1.6.1",
      "org.slf4j" % "slf4j-log4j12" % "1.6.1" % "test",	
      "com.novocode" % "junit-interface" % "0.8" % "test"
    )
  ) ++ sonatype.settings

}
