#!/bin/bash

PACKAGE_TYPE="custom"
PLAT_FLAG=""
ARCH_FLAG=""

print_usage ()
{
  printf "[USAGE]: ./build.sh -all | platform arch\nplatform = linux | darwin | windows\narch = x32 | x64\n"
}

if [ "$1" == "-all" ] ; then
  PACKAGE_TYPE="all"
elif [ "$1" == "-default" ] ; then
  PACKAGE_TYPE="default"
elif [ $# -ne 2 ] ; then
  print_usage
  exit 1
else
  for arg in "$@" ; do
    case "$arg" in
      linux)    PLAT_FLAG="linux";;
      darwin)   PLAT_FLAG="darwin";;
      windows)  PLAT_FLAG="win32";;
      x32)      ARCH_FLAG="ia32";;
      x64)      ARCH_FLAG="x64";;
      *)        print_usage
                exit 1;;
    esac
  done
fi

if [ $PACKAGE_TYPE == "default" ] ; then
  electron-packager . LibreDragon
  exit 0
elif [ $PACKAGE_TYPE == "all" ] ; then
  electron-packager . LibreDragon --all
  exit 0
elif [ $PACKAGE_TYPE == "custom" ] ; then
  electron-packager . LibreDragon --platform=$PLAT_FLAG --arch=$ARCH_FLAG
  exit 0
else
  exit 1
fi
