.PHONY: all

all:
	mkdir -p projecto_greenfoot || true
	ln -fs src/* projecto_greenfoot
	ln -fs resources/images projecto_greenfoot
	ln -fs resources/sounds projecto_greenfoot
	cp resources/project.greenfoot.mess projecto_greenfoot/project.greenfoot
