#!/bin/bash
AAPT2=/data/data/com.termux/files/usr/bin/aapt2
D8=/data/data/com.termux/files/usr/bin/d8
ECJ=/data/data/com.termux/files/usr/bin/ecj
SDK=$HOME/android-sdk/platforms/android-30/android.jar
RES=/storage/emulated/0/python/RemedyFonts/app/src/main/res
MANIFEST=/storage/emulated/0/python/RemedyFonts/app/src/main/AndroidManifest.xml
OUT=/storage/emulated/0/python/RemedyFonts/RemedyFonts.apk

cd /storage/emulated/0/python/RemedyFonts
rm -rf app/build
mkdir -p app/build/classes app/build/dex app/build/gen

echo "Step 1: Compiling resources..."
$AAPT2 compile --dir $RES -o app/build/res.zip && \
$AAPT2 link app/build/res.zip -I $SDK --manifest $MANIFEST --java app/build/gen -o app/build/base.apk && \
echo "Resources OK" || echo "Resources FAILED"

echo "Step 2: Compiling code..."
if [ -f app/build/gen/com/remedyfonts/app/R.java ]; then
    $ECJ -cp $SDK -d app/build/classes app/build/gen/com/remedyfonts/app/R.java && echo "Compile OK" || echo "Compile FAILED"
else
    echo "R.java missing — check resources"
fi

echo "Step 3: Dex..."
if [ -d app/build/classes ] && [ "$(ls app/build/classes)" ]; then
    $D8 --lib $SDK --output app/build/dex $(find app/build/classes -name "*.class") && echo "Dex OK" || echo "Dex FAILED"
else
    echo "No class files to dex"
fi

echo "Step 4: Packaging..."
if [ -f app/build/base.apk ] && [ -f app/build/dex/classes.dex ]; then
    cp app/build/base.apk $OUT
    cd app/build/dex && zip -r $OUT classes.dex
    echo "APK created: $OUT"
    ls -la $OUT
else
    echo "Missing files — APK not created"
fi
