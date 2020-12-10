#!/usr/bin/env bash

echo Create prefs directory
xvfb-run --auto-servernum ./marathon/marathon &
sleep 10
kill %1
echo Run Tests
xvfb-run --auto-servernum ./marathon/marathon -batch ./marathonTests/