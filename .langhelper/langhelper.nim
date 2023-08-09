import nigui
import utils

# [lang]

# [spacing category]
# [category][ID][subID] : [translation]
init(app)

var window = newWindow("JSON Language Files Helper")
var ctMain = newLayoutContainer(Layout_Vertical)
var newLangTx = newTextBox()
var newLangBt = newButton("Create language entry")
block settings:
  window.width  = 800
  window.height = 600
block binding:
  window.add(ctMain)
  block newLang:
    ctMain.add(newLangTx)
    ctMain.add(newLangBt)

newLangBt.onClick = proc(event: ClickEvent) =
    copyFile(newLangTx.text)

# running
show(window)
run(app)