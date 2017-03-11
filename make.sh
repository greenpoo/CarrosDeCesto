rm -rf gf
mkdir gf
cd gf
ln -s ../resources/images
ln -s ../resources/sounds
ls -1 ../src | while read dir; do \
	if [[ -d ../src/$dir ]]; then
		ln -s ../src/$dir/$file
	else
		ln ../src/$dir
	fi
done
cd -
cp resources/project.greenfoot.mess gf/project.greenfoot
