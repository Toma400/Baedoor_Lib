from utils import cwd
import nigui

init(app)

var window = newWindow("Log Reader")
var ctMain = newLayoutContainer(Layout_Vertical)
var logTx  = newTextArea()
block settings:
  window.width  = 1600
  window.height = 800
block binding:
  window.add(ctMain)
  ctMain.add(logTx)

for line in lines cwd & "run/logs/latest.log":
    logTx.addLine(line)

# running
show(window)
run(app)