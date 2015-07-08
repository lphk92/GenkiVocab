cp -r src/fonts src/vocab bin
cd bin
jar cfe Vocab.jar GenkiVocabDriver ./*
cd ..
mv bin/Vocab.jar .
chmod +x Vocab.jar
