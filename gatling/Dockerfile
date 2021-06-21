# Gatling is a highly capable load testing tool.
#
# Documentation: https://gatling.io/docs/3.0/
# Cheat sheet: https://gatling.io/docs/3.0/cheat-sheet/
# Reference docker file from Denis Vazhenin <denis.vazhenin@me.com>
#

FROM maven:3-jdk-8

LABEL \
        author="Sumit Nagal" \
        email="sumitnagal@gmail.com" \
        version="1.0.0" \
        description="Gatling test in a docker"

ENV SHELL=/bin/bash

ADD . /gatling/
RUN ["/bin/bash", "-c", "cd /gatling && mvn clean install -Dgatling.skip"]
