package org.codefeedr.plugins.npm.util

import org.scalatest.FunSuite
import org.codefeedr.plugins.npm.protocol.Protocol.{Bug, PersonObject, Repository, TimeObject}
import scala.io.Source

class AllfieldTest extends FunSuite {
  val rootPath = "codefeedr-plugins/codefeedr-1up/src/test/resources/npm/test-data/"

  test("convertProjectFrom(\"ts2php\" - All fields") {
    // Arrange
    val jsonString = Source.fromFile(rootPath + "ts2php.json").getLines().mkString
    // Act
    val result = NpmService.convertProjectFrom(jsonString).get
    // Assert
    assert(result._id == "ts2php")
    assert(result._rev == Some("84-a99a3e14f1d576d910aafb083cba8673"))
    assert(result.name == "ts2php")
    assert(result.author.get.name == "meixuguang")
    assert(result.author.get.email.isEmpty)
    assert(result.author.get.url.isEmpty)
    assert(result.contributors.get.size == 1)
    assert(result.contributors.get.head.name == "cxtom")
    assert(result.contributors.get.head.email == Some("cxtom2008@gmail.com"))
    assert(result.contributors.get.head.url == None)
    assert(result.description == Some("TypeScript to PHP Transpiler"))
    assert(result.homepage == Some("https://github.com/searchfe/ts2php#readme"))
    assert(result.keywords == None)
    assert(result.license == Some("MIT"))
    assert(result.dependencies.isEmpty) // is intended, since this will be built up in buildExtendedReleaseUsing
    assert(result.maintainers.size == 2)
    assert(result.maintainers.head.name == "cxtom")
    assert(result.maintainers.head.email == Some("cxtom2010@gmail.com"))
    assert(result.maintainers.head.url.isEmpty)
    assert(result.maintainers.last.name == "meixg")
    assert(result.maintainers.last.email == Some("meixg@foxmail.com"))
    assert(result.maintainers.last.url.isEmpty)
    // skipped testing readme itself
    assert(result.readmeFilename == "README.md")
    assert(result.bugs.get.url.get=="https://github.com/searchfe/ts2php/issues")
    assert(result.bugs.get.email.isEmpty)
    assert(result.bugString.isEmpty)
    assert(result.repository.get.`type` == "git")
    assert(result.repository.get.url == "git+https://github.com/searchfe/ts2php.git")
    assert(result.repository.get.directory.isEmpty)
    assert(result.time.created == "2019-02-19T06:00:04.974Z")
    assert(result.time.modified == Some("2019-12-23T14:52:18.893Z"))
  }

  test("convertProjectfrom(\"fitfont\") - All fields") {
    // Arrange
    val jsonString = Source.fromFile(rootPath + "fitfont-haskeywords.json").getLines().mkString
    // Act
    val result = NpmService.convertProjectFrom(jsonString).get
    val bla = 4
    // Assert
    assert(result._id == "fitfont")
    assert(result._rev == Some("9-8893e3db2492f122c3bf2293ec25d2e0"))
    assert(result.name == "fitfont")
    assert(result.author.get.name == "Grégoire Sage")
    assert(result.author.get.email.isEmpty)
    assert(result.author.get.url.isEmpty)
    assert(result.contributors.isEmpty)
    assert(result.description == Some("This library allows you to easily display text with custom fonts."))
    assert(result.homepage == Some("https://github.com/gregoiresage/fitfont#readme"))
    assert(result.keywords.get.size == 1) // BUG
    assert(result.keywords.get.head == "fitbitdev")
    assert(result.license == Some("MIT"))
    assert(result.dependencies.isEmpty) // is intended, since this will be built up in buildExtendedReleaseUsing
    assert(result.maintainers.size == 1)
    assert(result.maintainers.head == PersonObject("gregoire",Some("gregoire.sage@gmail.com"),None))
    // skipped readMe
    assert(result.readmeFilename == "README.md")
    assert(result.bugs.get == Bug(Some("https://github.com/gregoiresage/fitfont/issues"),None))
    assert(result.bugString.isEmpty)
    assert(result.repository.get == Repository("git","git+https://github.com/gregoiresage/fitfont.git",None))
    assert(result.time == TimeObject("2018-09-09T13:24:15.691Z",Some("2020-01-08T22:19:07.288Z")))
  }

}
