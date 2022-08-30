
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object test extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html lang="en">
    <head>
        """),format.raw/*6.62*/("""
        """),format.raw/*7.9*/("""<title>"""),_display_(/*7.17*/title),format.raw/*7.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*8.54*/routes/*8.60*/.Assets.versioned("stylesheets/main.css")),format.raw/*8.101*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*9.59*/routes/*9.65*/.Assets.versioned("images/favicon.png")),format.raw/*9.104*/("""">

    </head>
    <body>
        """),format.raw/*14.32*/("""
        """),_display_(/*15.10*/content),format.raw/*15.17*/("""
        """),format.raw/*16.9*/("""<h1>TEST SUCCESSFUL</h1>

      <script src=""""),_display_(/*18.21*/routes/*18.27*/.Assets.versioned("javascripts/main.js")),format.raw/*18.67*/("""" type="text/javascript"></script>
    </body>
</html>"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/test.scala.html
                  HASH: 395726deb59045175d79f59636316ac5742072ea
                  MATRIX: 733->1|857->32|884->33|963->138|998->147|1032->155|1057->160|1145->222|1159->228|1221->269|1308->330|1322->336|1382->375|1445->500|1482->510|1510->517|1546->526|1619->572|1634->578|1695->618
                  LINES: 21->1|26->2|27->3|30->6|31->7|31->7|31->7|32->8|32->8|32->8|33->9|33->9|33->9|37->14|38->15|38->15|39->16|41->18|41->18|41->18
                  -- GENERATED --
              */
          