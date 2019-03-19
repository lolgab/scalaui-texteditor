package editor

import java.io.{BufferedWriter, File, FileWriter}

import reactify._

import scalaui._

object Editor {
  var path: Var[String] = Var("")
  lazy val s: Var[String] = Var("")
  s.attach { str =>
    textArea.text = str
  }


  def textOnChange(): Unit = s := textArea.text
  lazy val textArea = new NonWrappingTextArea(textOnChange _)

  def writeToFile(s: String, path: String): Unit = {
    val file = new File(path)
    val writer = new BufferedWriter(new FileWriter(file))
    writer.write(s)
    writer.close()
  }

  def readFromFile(path: String): String = io.Source.fromFile(path).getLines().mkString("\n")

  def newFileAction(): Unit = {
    s := ""
    path := ""
  }

  def openFileAction(): Unit = {
    path := window.openFile()
    s := readFromFile(path)
  }

  def saveFileAction(): Unit = {
    if(path() == "") path := window.saveFile()
    writeToFile(s, path())
  }

  val fileName = Val {
    if(path() == "") "untitled" else path().split('/').last
  }
  fileName.attach { group.title = _ }


  lazy val newFile = new MenuItem("New File", newFileAction _)

  lazy val openFile = new MenuItem("Open File", openFileAction _)
  lazy val saveFile = new MenuItem("Save File", saveFileAction _)

  lazy val items = Seq(newFile, openFile, saveFile)
  lazy val menu = new Menu("File", items: _*)

  lazy val group = new Group(fileName, textArea)

  lazy val window = new Window("Text Editor", 700, 500, group, Seq(menu))

  def main(args: Array[String]): Unit = {
    scalaui.render(window)
  }
}