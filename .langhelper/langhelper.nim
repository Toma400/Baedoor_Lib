import questionable
import strutils
import nigui
import utils

# [lang]

# [spacing category]
# [category][ID][subID] : [translation]
init(app)

var lang_id  = ""
var lang_obj = newSeq[LanguageFile]()

var window = newWindow("JSON Language Files Helper")
var ctMain = newLayoutContainer(Layout_Vertical)
var ctUp   = newLayoutContainer(Layout_Horizontal)
var ctMid  = newLayoutContainer(Layout_Horizontal)
var newLangTx = newTextBox()
var newLangBt = newButton("Create language entry")
var loadLangLs = newComboBox(languageFilesList())
var loadLangBt = newButton("Load language entry")
var loadLangTx = newTextArea()
block settings:
  window.width  = 800
  window.height = 600
  ctUp.width = 700
block align:
  ctUp.xAlign = XAlign_Center
block binding:
  window.add(ctMain)
  block newLang:
    ctUp.add(newLangTx)
    ctUp.add(newLangBt)
    ctMain.add(ctUp)
  block loadLang:
    ctMid.add(loadLangLs)
    ctMid.add(loadLangBt)
    ctMain.add(ctMid)
    ctMain.add(loadLangTx)

newLangBt.onClick = proc(event: ClickEvent) =
    copyFile(newLangTx.text)
loadLangBt.onClick = proc(event: ClickEvent) =
    lang_id  = loadLangLs.value
    lang_obj = @[languageFileParser(lang_id)]
    for line in toSeq(lang_obj[0]):
        var sp = "    "
        if line.contains("SECTION"):
            sp = ""
        loadLangTx.addLine(sp & line)

# running
show(window)
run(app)