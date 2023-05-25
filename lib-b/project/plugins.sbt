resolvers ++= Seq(
  "agoda-maven-hkg" at "http://repo.hkg.sdlc.agoda.local/artifactory/agoda-maven" withAllowInsecureProtocol true,
  "cloudera-repo-releases" at "https://repository.cloudera.com/artifactory/repo",
  "Agoda maven local release" at "https://repo-hkg.agodadev.io/agoda-maven-local-release",
  "Agoda maven local snapshot" at "https://repo-hkg.agodadev.io/agoda-maven-local-snapshot"
)

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.2")


addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "1.1.0")
