from utils import cwd
import std/sequtils
import strutils
import os

let search = cwd & "build/libs"
let dest   = cwd & ".helpers/jars"

for kind, path in walkDir(dest):
    case kind:
      of pcFile: removeFile(path)
      else:      discard

for kind, path in walkDir(search):
    case kind:
      of pcFile: copyFileToDir(path, dest)
      else:      discard