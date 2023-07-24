@echo off
set "source=target/site/allure-maven-plugin/history"
set "destination=target/allure-results/history"

if exist "%destination%" (
    rmdir /s /q "%destination%"
)

xcopy /s /i "%source%" "%destination%"