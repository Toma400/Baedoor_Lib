import std/sequtils
import strutils
import os

type
    EntryCategory = enum
        ITEM  = "item"
        BLOCK = "block"
        TAB   = "creative_tab"  # requires string structured as: "creative_tab." + MOD_ID + "." + TAB_ID, since it sets three elements, in TranslationLine style
    TranslationLine = object
        category: string
        mod_id:   string
        id:       string
        transl:   string
    TranslationSection = object
        lines:    seq[TranslationLine]
    LanguageFile* = object
        sections: seq[TranslationSection]

# gets mod ID from gradle.properties file of the mod
proc gradlePropertiesParser* (key: string): string =
    let cwd = getCurrentDir().replace(r".helpers", "")
    for line in lines(cwd & "gradle.properties"):
        if line.contains(key):
            return split(line, "=")[1].replace(" ", "")

# parses language file of ID provided
proc languageFileParser* (lang_id: string): LanguageFile =
    let f     = getCurrentDir().replace(r".helpers", "src/main/resources/assets/" & gradlePropertiesParser("mod_id") & "/lang/" & lang_id & ".json")
    var fSq   = newSeq[TranslationSection]()
    var secSq = newSeq[TranslationLine]()
    for line in lines f:
        if not (line.contains("{")):
            if line.len == 0 or line.contains("}"): # indicates sections (gap between text chunks)
                fSq.add(TranslationSection(lines: secSq))
                secSq = @[] # clears up sequence for new section
            else:
                let lineSplit = split(line.replace("\"", ""), ": ")
                let idSplit   = split(lineSplit[0], ".")
                secSq.add(TranslationLine(category: idSplit[0].replace(" ", ""),
                                          mod_id:   idSplit[1],
                                          id:       idSplit[2],
                                          transl:   lineSplit[1].replace(",", "")))
    return LanguageFile(sections: fSq)

# writes JSON of language ID set in 'dest'. Requires LanguageFile object
proc languageFileWriter* (dest: string, file: LanguageFile) =
    var fstr = "{\n"
    var fin  = ","
    for sect in file.sections:
        for line in sect.lines:
            if find(sect.lines, line) == sect.lines.len-1 and find(file.sections, sect) == file.sections.len-1:
                fin = "\n}"
            fstr = fstr & "    \"" & line.category & "." & line.mod_id & "." & line.id & "\": \"" & line.transl & "\"" & fin & "\n"
        if find(file.sections, sect) < file.sections.len-1: # make enter if not finished
            fstr = fstr & "\n"
    writeFile(getCurrentDir().replace(r".helpers", "src/main/resources/assets/" & gradlePropertiesParser("mod_id") & "/lang/" & dest & ".json"), fstr)

# TODO![ temporary ]
# copies 'en_us' JSON into given ID, preserving structure
proc copyFile* (dest: string) =
    languageFileWriter(dest, languageFileParser("en_us"))

# TODO![ temporary ]
# string representation of LanguageFile
proc `$`* (l: LanguageFile): string =
    var ret = ""
    var nid = 1
    for sect in l.sections:
        ret = ret & "[SECTION " & $nid & "]\n"
        for line in sect.lines:
            ret = ret & "[" & line.category & "] " & line.id & " = " & line.transl & "\n"
        nid += 1
    return ret

# string representation of
proc `toSeq`* (l: LanguageFile): seq[string] =
    var ret = newSeq[string]()
    var nid = 1
    for sect in l.sections:
        ret.add("[SECTION " & $nid & "]")
        for line in sect.lines:
            ret.add("[" & line.category & "] " & line.id & " = " & line.transl)
        nid += 1
    return ret

# TODO![ to be updated ]
# destined to create TranslationLine from customly given data (for example, from GUI)
proc createEntry* (cat: string, id: string, transl: string): TranslationLine =
    return TranslationLine(category: cat, mod_id: gradlePropertiesParser("mod_id"), id: id, transl: transl)

# lists all language files mod contains
proc languageFilesList* (): seq[string] =
    let path = getCurrentDir().replace(r".helpers", "src/main/resources/assets/" & gradlePropertiesParser("mod_id") & "/lang/")
    var ret = newSeq[string]()
    for kind, name in walkDir(path, relative=true):
        if kind == pcFile:
            if name.contains(".json"):
                ret.add(name.replace(".json"))
    return ret
