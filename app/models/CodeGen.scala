package models

object CodeGen extends App {
  slick.codegen.SourceCodeGenerator.run(
    "slick.jdbc.PostgresProfile",
    "org.postgresql.Driver",
    "jdbc:postgresql://localhost/restaurant?user=samiul&password=password",
    "/Users/user02/Desktop/shigoto/splay/restaurant-apis/app",
    "models", None, None, true, false
  )
}
