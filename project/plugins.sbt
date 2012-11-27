resolvers += "Scales Repo" at "https://scala-scales.googlecode.com/svn/repo"

libraryDependencies ++= Seq(
)

resolvers += Resolver.url("sbt-plugin-releases", new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.jsuereth" % "xsbt-gpg-plugin" % "0.6")
