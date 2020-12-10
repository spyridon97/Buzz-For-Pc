#!/usr/bin/env bash

echo Create prefs directory
xvfb-run --auto-servernum ./marathon/marathon &
echo Waiting for prefs creation
while [ ! -f ~/.java/.userPrefs/net/prefs.xml ]; do sleep 1; done
if [ -f ~/.java/.userPrefs/net/prefs.xml ]; then
  echo true
else
  echo false
fi
kill %1
echo Run Tests
xvfb-run --auto-servernum ./marathon/marathon -batch ./marathonTests/