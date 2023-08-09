import nigui
import utils

# [lang]

# [spacing category]
# [category][ID][subID] : [translation]
init(app)

var window = newWindow("JSON Language Files Helper")
var main   = newLayoutContainer(Layout_Vertical)
block settings:
  window.width  = 800
  window.height = 600
block binding:
  window.add(main)

echo languageFileParser("en_us")
echo languageFiles()

# running
show(window)
run(app)