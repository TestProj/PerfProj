# Makefile for building Containers for Storage Testing
# Reference Guide - https://www.gnu.org/software/make/manual/make.html

# Internal variables or constants.
# NOTE - These will be executed when any make target is invoked.
IS_DOCKER_INSTALLED       := $(shell which docker >> /dev/null 2>&1; echo $$?)

help:
	@echo ""
	@echo "Usage:-"
	@echo "\tmake deps              -- will verify build dependencies are installed"
	@echo "\tmake perfproj      -- will build and push python experiment images
	@echo ""

_build_check_docker:
	@if [ $(IS_DOCKER_INSTALLED) -eq 1 ]; \
		then echo "" \
		&& echo "ERROR:\tdocker is not installed. Please install it before build." \
		&& echo "" \
		&& exit 1; \
		fi;

deps: _build_check_docker
	@echo ""
	@echo "INFO:\tverifying dependencies for perfProj ..."

_build_tests_perfProj_gatling_image:
	@echo "INFO: Building container image for performing perf tests"
	cd gatling && docker build -f Dockerfile -t perfproj/gatling .
	cd gatling && docker build -f Dockerfile.merge -t perfproj/gatlingmerge .

_push_tests_perfProj_gatling_image:
	@echo "INFO: Publish container perfproj"
	cd buildscripts && ./push

perfProj: deps _build_tests_perfProj_gatling_image _push_tests_perfProj_gatling_image
