#!/bin/bash


# create /usr/local/lib folder doesn't exist
if [[ -d /usr/local/lib ]]; then
	echo "found /usr/local/lib";
else
	echo "no /usr/local/lib folder";
	echo "making /usr/local/lib";
	sudo mkdir /usr/local/lib;
fi	

# copy libftd2xx.1.2.2.dylib if it doesn't exist
if [[ -f /usr/local/lib/libftd2xx.1.2.2.dylib ]]; then
	echo "found /usr/local/lib/libftd2xx.1.2.2.dylib";
else
	echo "copying /usr/local/lib/libftd2xx.1.2.2.dylib";
	sudo cp libftd2xx.1.2.2.dylib /usr/local/lib/libftd2xx.1.2.2.dylib
fi

# make a symlink to libftd2xx.1.2.2.dylib if it doesn't exist
if [[ -f /usr/local/lib/libftd2xx.dylib ]]; then
	echo "found symlink /usr/local/lib/libftd2xx.dylib";
else
	echo "creating symlink /usr/local/lib/libftd2xx.dylib";
	sudo ln -sf /usr/local/lib/libftd2xx.1.2.2.dylib /usr/local/lib/libftd2xx.dylib
fi

# set permissions for dylib and symlink
sudo chmod a+r /usr/local/lib/libftd2xx.1.2.2.dylib
sudo chmod a+r /usr/local/lib/libftd2xx.dylib

# Disable AppleFTDI driver if it has been enabled
foundAppleFTDI=`kextstat | grep FTDI | wc -l | xargs`
if [ "$foundAppleFTDI" == "0" ]; then
		echo "correct FTDI driver found"
else
	echo "removing AppleFTDI driver"
	sudo kextunload -b com.apple.driver.AppleUSBFTDI
fi

echo "finished"

