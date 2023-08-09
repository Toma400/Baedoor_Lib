import std/sequtils
import strutils
import os

# gets mod ID from gradle.properties file of the mod
proc gradlePropertiesParser* (key: string): string =
    let cwd = getCurrentDir().replace(r".langhelper", "")
    for line in lines(cwd & "gradle.properties"):
        if line.contains(key):
            return split(line, "=")[1].replace(" ", "")

# parses languge file of ID provided
proc languageFileParser* (lang_id: string): string =
    let f = getCurrentDir().replace(r".langhelper", "src/main/resources/assets/" & gradlePropertiesParser("mod_id") & "/lang/" & lang_id & ".json")
    return readFile(f)

# lists all language files mod contains
proc languageFiles* (): seq[string] =
    let path = getCurrentDir().replace(r".langhelper", "src/main/resources/assets/" & gradlePropertiesParser("mod_id") & "/lang/")
    var ret = newSeq[string]()
    for kind, name in walkDir(path, relative=true):
        if kind == pcFile:
            if name.contains(".json"):
                ret.add(name.replace(".json"))
    return ret
